package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WhiteHouseVisitorVisiteeMeetingPlaceMapper extends
        Mapper<LongWritable, Text, Text, IntWritable> {
    private WhiteHouseRecordParser parser = new WhiteHouseRecordParser();
    private static final IntWritable one = new IntWritable(1);

    @Override
    /**
     * (K1, V1) = (line#, line)
     * (K2, V2) = (visitor, visitee, and meeting place combination, 1)
     */
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        parser.parse(value);
        if (parser.isValidRecord()) {
            context.write(new Text(parser.getVisitorVisiteeMeetingPlace()), one);
        }
    }
}

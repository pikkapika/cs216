package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;

public class AverageTemperatureMapper extends
        Mapper<LongWritable, Text, Text, Text> {
    private static final String DELIMITER = ",";
    private NcdcRecordParser parser = new NcdcRecordParser();

    @Override
    /**
     * (K1, V1) = (line#, line)
     * (K2, V2) = (year, temperature, 1)
     */
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        parser.parse(value);
        if(parser.isValidTemperature())
        {
            context.write(
                    new Text(parser.getYear()), 
                    new Text(parser.getAirTemperature() + DELIMITER + 1));
        }        
    }
}

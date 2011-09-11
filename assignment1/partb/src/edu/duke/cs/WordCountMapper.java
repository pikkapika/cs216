package edu.duke.cs;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends
Mapper<LongWritable, Text, Text, IntWritable> {
    private static final String DELIMITER = "|";
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    @Override
    /**
     * (K1, V1) = (line#, line)
     * (K2, V2) = (word, 1)
     */
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString();
        StringTokenizer tokenizer = new StringTokenizer(line, DELIMITER);
        
        while (tokenizer.hasMoreTokens()) {
            word.set(tokenizer.nextToken());
            context.write(word, one);
        }
    }
}

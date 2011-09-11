package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class SortMapper extends
Mapper<LongWritable, Text, IntWritable, Text> {
    private static final String DELIMITER = "\t";
    private Text word = new Text();
    private IntWritable numOfWords = new IntWritable();

    @Override
    /**
     * (K1, V1) = (line#, line)
     * (K2, V2) = (# of the word, word)
     */
    public void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        System.err.println("[USER] map: " + value);

        String line = value.toString();
        String result[] = line.split(DELIMITER);
        
        System.err.println(result[0] + result[1]);
        
        if(result.length == 2)
        {
            word.set(result[0]);
            numOfWords.set(Integer.parseInt(result[1]));
            context.write(numOfWords, word);
        }
    }
}
package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;

public class WhiteHouseVisitorReducer extends
Reducer<Text, IntWritable, Text, IntWritable> {
    
    /**
     * (K2, V2) = (year, average temperature, # of samples)
     * (K3, V3) = (year, average temperature, # of samples)
     * 
     * It can be used as reducer
     */
    @Override
    public void reduce(Text key, Iterable<IntWritable> values,
            Context context) throws IOException, InterruptedException {
        
        int     numOfValues = 0;
        for (IntWritable value : values) {
            numOfValues += Integer.parseInt(value.toString());
        }
        context.write(key, new IntWritable(numOfValues));
    }
    
}

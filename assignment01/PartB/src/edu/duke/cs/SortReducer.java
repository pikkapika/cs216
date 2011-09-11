package edu.duke.cs;

import java.io.IOException;

import org.apache.commons.lang.*;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class SortReducer extends Reducer<IntWritable, Text, IntWritable, Text> {
    private static final String DELIMITER = ",";

    @Override
    /**
     * (K2, V2) = (# of the word, word list)
     * (K3, V3) = (# of the word, word list)
     *
     * It can be used as reducer
     */
    public void reduce(IntWritable key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        String word = StringUtils.join(values.iterator(), DELIMITER);
        System.err.println("[USER] reduce: " + key + "|" + word);
        context.write(key, new Text(word));
    }
}
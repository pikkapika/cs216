package edu.duke.cs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SortDriver extends Configured implements Tool  {
    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.err
                    .println("Usage: SortDriver <input path> <output path>");
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }
        
        Configuration conf = new Configuration();
        Job job = new Job(conf);
        job.setJarByClass(SortDriver.class);
        
        // input file format
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // map/combine/reduce class definition
        job.setMapperClass(SortMapper.class);
        job.setReducerClass(SortReducer.class);
        
        // Key, Value set type definition
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        return job.waitForCompletion(true) ? 0 : 1;
    }
    
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new SortDriver(), args);
        System.exit(exitCode);
    }

}

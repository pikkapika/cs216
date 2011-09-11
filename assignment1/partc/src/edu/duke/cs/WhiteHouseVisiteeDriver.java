package edu.duke.cs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.reduce.IntSumReducer;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WhiteHouseVisiteeDriver extends Configured implements Tool {
    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.err
                    .println("Usage: WhiteHouseVisiteeDriver <input path> <output path>");
            ToolRunner.printGenericCommandUsage(System.err);
            return -1;
        }
        
        Configuration conf = new Configuration();
        Job job = new Job(conf);
        job.setJarByClass(WhiteHouseVisiteeDriver.class);
        
        // input file format
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // map/combine/reduce class definition
        job.setMapperClass(WhiteHouseVisiteeMapper.class);
        job.setCombinerClass(IntSumReducer.class);
        job.setReducerClass(IntSumReducer.class);

        // Key, Value set type definition
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        return job.waitForCompletion(true) ? 0 : 1;
        
    }
    
    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new WhiteHouseVisiteeDriver(), args);
        System.exit(exitCode);
    }
}
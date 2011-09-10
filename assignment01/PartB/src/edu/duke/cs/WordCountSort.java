package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;


public class WordCountSort {
	
	
	static class WordCountSortMapper extends
			Mapper<LongWritable, Text, IntWritable, Text> {
		private static final String DELIMITER = "\t";

		@Override
		/**
		 * (K1, V1) = (line#, line) - line contains (word + \t + # of words)
		 * (K2, V2) = (# of the word, word)
		 */
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String line = value.toString();
			String result[] = line.split(DELIMITER);
			
			context.write(new IntWritable(Integer.parseInt(result[1])), new Text(result[0]));
		}
	}

	static class WordCountSortReducer extends
			Reducer<IntWritable, Text, IntWritable, Text> {
		private static final String DELIMITER = "\t";

		@Override
		/**
		 * (K2, V2) = (# of the word, word list)
		 * (K3, V3) = (# of the word, word list)
		 * 
		 * It can be used as reducer
		 */
		public void reduce(IntWritable key, Iterable<Text> values,
				Context context) throws IOException, InterruptedException {

			String words = "";
			for (Text value : values) {
				words += value.toString() + DELIMITER;
			}
			context.write(key, new Text(words));		
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err
					.println("Usage: WordCount <input path> <output path>");
			System.exit(-1);
		}

		Configuration conf = new Configuration();
		Job job = new Job(conf);
		job.setJarByClass(WordCount.class);

		// input file format
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// map/combine/reduce class definition
		job.setMapperClass(WordCountSortMapper.class);
		job.setReducerClass(WordCountSortReducer.class);

		// Key, Value set type definition
		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(Text.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(Text.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}
}

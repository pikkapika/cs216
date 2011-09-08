package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class AverageTemperature {

	static class AverageTemperatureMapper extends
			Mapper<LongWritable, Text, Text, IntWritable> {

		private static final int MISSING = 9999;

		@Override
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String line = value.toString();
			String year = line.substring(15, 19);
			int airTemperature;
			if (line.charAt(87) == '+') { // parseInt doesn't like leading plus
											// signs
				airTemperature = Integer.parseInt(line.substring(88, 92));
			} else {
				airTemperature = Integer.parseInt(line.substring(87, 92));
			}
			String quality = line.substring(92, 93);
			if (airTemperature != MISSING && quality.matches("[01459]")) {
				context.write(new Text(year), new IntWritable(airTemperature));
			}
		}
	}

	static class AverageTemperatureReducer extends
			Reducer<Text, IntWritable, Text, FloatWritable> {

		@Override
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {
			
			int 	numOfValues = 0;
			float 	sumOfValues = 0;
			for (IntWritable value : values) {
				sumOfValues += value.get();
				numOfValues++;
			}
			context.write(key, new FloatWritable(sumOfValues/numOfValues));
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err
					.println("Usage: AverageTemperature <input path> <output path>");
			System.exit(-1);
		}

		Configuration conf = new Configuration();
		Job job = new Job(conf);
		job.setJarByClass(AverageTemperature.class);

		// input file format
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// map/combine/reduce class definition
		job.setMapperClass(AverageTemperatureMapper.class);
		job.setReducerClass(AverageTemperatureReducer.class);

		// Key, Value set type definition
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}
}

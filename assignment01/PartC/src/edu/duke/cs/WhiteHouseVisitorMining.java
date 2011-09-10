package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class WhiteHouseVisitorMining {
	static class WhiteHouseVisitorMiningMapper extends
			Mapper<LongWritable, Text, Text, IntWritable> {

		private static final String DELIMITER = ",";
		private static final String DELIMITER_FOR_REDUCE = ",";
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		@Override
		/**
		 * (K1, V1) = (line#, line)
		 * (K2, V2) = ({NAMELAST, NAMEFIRST, NAMEMID, visitee_namelast visitee_namefirst,MEETING_LOC,MEETING_ROOM}, 1)
		 */
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String line = value.toString();
			String result[] = line.split(DELIMITER);

			/**
			 * @TODO: error format check
			 */
			word.set(result[0] + DELIMITER_FOR_REDUCE + result[1]
					+ DELIMITER_FOR_REDUCE + result[2] + DELIMITER_FOR_REDUCE);
			context.write(word, one);
		}
	}

	static class WhiteHouseVisitorMiningVisitorsMapper extends
			Mapper<LongWritable, Text, Text, IntWritable> {

		private static final String DELIMITER = ",";
		private static final String DELIMITER_FOR_REDUCE = ",";
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		@Override
		/**
		 * (K1, V1) = (line#, line)
		 * (K2, V2) = ({NAMELAST, NAMEFIRST, NAMEMID, visitee_namelast visitee_namefirst,MEETING_LOC,MEETING_ROOM}, 1)
		 */
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String line = value.toString();
			String result[] = line.split(DELIMITER);

			/**
			 * @TODO: error format check
			 */
			word.set(result[0] + DELIMITER_FOR_REDUCE + result[1]
					+ DELIMITER_FOR_REDUCE + result[2] + DELIMITER_FOR_REDUCE);
			context.write(word, one);
		}
	}

	static class WhiteHouseVisitorMiningVisiteesMapper extends
			Mapper<LongWritable, Text, Text, IntWritable> {

		private static final String DELIMITER = ",";
		private static final String DELIMITER_FOR_REDUCE = ",";
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		@Override
		/**
		 * (K1, V1) = (line#, line)
		 * (K2, V2) = ({NAMELAST, NAMEFIRST, NAMEMID, visitee_namelast visitee_namefirst,MEETING_LOC,MEETING_ROOM}, 1)
		 */
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String line = value.toString();
			String result[] = line.split(DELIMITER);

			/**
			 * @TODO: error format check
			 */
			word.set(result[19] + DELIMITER_FOR_REDUCE + result[20]
					+ DELIMITER_FOR_REDUCE);
			context.write(word, one);
		}
	}

	static class WhiteHouseVisitorMiningVisitorsAndVisiteesMapper extends
			Mapper<LongWritable, Text, Text, IntWritable> {

		private static final String DELIMITER = ",";
		private static final String DELIMITER_FOR_REDUCE = ",";
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		@Override
		/**
		 * (K1, V1) = (line#, line)
		 * (K2, V2) = ({NAMELAST, NAMEFIRST, NAMEMID, visitee_namelast visitee_namefirst,MEETING_LOC,MEETING_ROOM}, 1)
		 */
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String line = value.toString();
			String result[] = line.split(DELIMITER);

			/**
			 * @TODO: error format check
			 */
			word.set(
					result[0] + DELIMITER_FOR_REDUCE + 
					result[1] + DELIMITER_FOR_REDUCE + 
					result[2] + DELIMITER_FOR_REDUCE + 
					result[19] + DELIMITER_FOR_REDUCE + 
					result[20] + DELIMITER_FOR_REDUCE
			);
			context.write(word, one);
		}
	}

	static class WhiteHouseVisitorMiningRoomsMapper extends
			Mapper<LongWritable, Text, Text, IntWritable> {

		private static final String DELIMITER = ",";
		private static final String DELIMITER_FOR_REDUCE = ",";
		private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();

		@Override
		/**
		 * (K1, V1) = (line#, line)
		 * (K2, V2) = ({NAMELAST, NAMEFIRST, NAMEMID, visitee_namelast visitee_namefirst,MEETING_LOC,MEETING_ROOM}, 1)
		 */
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {

			String line = value.toString();
			String result[] = line.split(DELIMITER);

			/**
			 * @TODO: error format check
			 */
			word.set(result[21] + DELIMITER_FOR_REDUCE + result[22]
					+ DELIMITER_FOR_REDUCE);
			context.write(word, one);
		}
	}

	static class WhiteHouseVisitorMiningReducer extends
			Reducer<Text, IntWritable, Text, IntWritable> {

		@Override
		public void reduce(Text key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException {

			int sum = 0;
			for (IntWritable value : values) {
				sum += value.get();
			}
			context.write(key, new IntWritable(sum));
		}
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err
					.println("Usage: WhiteHouseVisitorMining <input path> <output path>");
			System.exit(-1);
		}

		Configuration conf = new Configuration();
		Job job = new Job(conf);
		job.setJarByClass(WhiteHouseVisitorMining.class);

		// input file format
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		// map/combine/reduce class definition
		job.setMapperClass(WhiteHouseVisitorMiningVisitorsAndVisiteesMapper.class);
		job.setReducerClass(WhiteHouseVisitorMiningReducer.class);

		// Key, Value set type definition
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}

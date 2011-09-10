javac -classpath $HADOOP_HOME/hadoop-core-0.20.203.0.jar -d classes src/edu/duke/cs/*

jar -cvf jar/WordCount.jar -C classes/ .

hadoop dfs -cp ~/PartB_output/part-* ~/PartB_input/
hadoop fs -rmr ~/PartB_output

hadoop jar jar/WordCount.jar edu/duke/cs/WordCountSort  ~/PartB_input/part-* ~/PartB_output
  
hadoop fs -lsr ~/PartB_output
	  
hadoop fs -cat ~/PartB_output/part-*


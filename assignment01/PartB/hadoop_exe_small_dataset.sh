javac -classpath $HADOOP_HOME/hadoop-core-0.20.203.0.jar -d classes src/edu/duke/cs/WordCount.java 

jar -cvf jar/WordCount.jar -C classes/ .

hadoop fs -rmr ~/PartB_input
hadoop fs -mkdir ~/PartB_input
hadoop dfs -copyFromLocal data/sample.txt ~/PartB_input/

hadoop fs -rmr ~/PartB_output

hadoop jar jar/WordCount.jar edu/duke/cs/WordCount  ~/PartB_input/sample.txt ~/PartB_output
  
hadoop fs -lsr ~/PartB_output
	  
hadoop fs -cat ~/PartB_output/part-r-00000


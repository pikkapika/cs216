javac -classpath $HADOOP_HOME/hadoop-core-0.20.203.0.jar -d classes src/edu/duke/cs/AverageTemperature.java 

jar -cvf jar/AverageTemperature.jar -C classes/ .

hadoop fs -rmr ~/PartA_input
hadoop fs -mkdir ~/PartA_input
hadoop dfs -copyFromLocal data/file01 ~/PartA_input/
hadoop dfs -copyFromLocal data/file02 ~/PartA_input/

hadoop fs -rmr ~/PartA_output

hadoop jar jar/AverageTemperature.jar edu/duke/cs/AverageTemperature  ~/PartA_input/file* ~/PartA_output
  
hadoop fs -lsr ~/PartA_output
	  
hadoop fs -cat ~/PartA_output/part-r-00000


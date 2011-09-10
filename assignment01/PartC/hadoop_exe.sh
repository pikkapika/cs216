javac -classpath $HADOOP_HOME/hadoop-core-0.20.203.0.jar -d classes src/edu/duke/cs/WhiteHouseVisitorMining.java 

jar -cvf jar/WhiteHouseVisitorMining.jar -C classes/ .

hadoop fs -rmr ~/PartC_output

hadoop jar jar/WhiteHouseVisitorMining.jar edu/duke/cs/WhiteHouseVisitorMining  ~/PartC_input/WhiteHouse-WAVES-Released-0827.csv ~/PartC_output
  
hadoop fs -lsr ~/PartC_output
	  
hadoop fs -cat ~/PartC_output/part-r-00000


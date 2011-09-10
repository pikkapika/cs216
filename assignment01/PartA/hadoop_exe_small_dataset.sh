jar -cvf jar/AverageTemperature.jar -C bin/ .

hadoop fs -rmr ~/PartA_output

hadoop jar jar/AverageTemperature.jar edu/duke/cs/AverageTemperatureDriver  ~/PartA_input/sample.txt ~/PartA_output
  
hadoop fs -lsr ~/PartA_output
	  
hadoop fs -cat ~/PartA_output/part-r-00000


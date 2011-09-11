if [ $# -ne 2 ] ; then
	echo "command input_filename output_dir"
	echo "e.g.) hadoop_exe.sh sample.txt PartA_output"
	exit
fi

jar -cvf jar/AverageTemperature.jar -C bin/ .

hadoop fs -rmr ~/$2

hadoop jar jar/AverageTemperature.jar edu/duke/cs/AverageTemperatureDriver  ~/PartA_input/$1 ~/$2
  
hadoop fs -lsr ~/PartA_output
	  
hadoop fs -cat ~/PartA_output/part-r-00000


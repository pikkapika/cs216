if [ $# -ne 2 ] ; then
	echo "command input_filename output_dir"
	echo "e.g.) hadoop_exe.sh sample.txt PartB_output"
	exit
fi

jar -cvf jar/WordCount.jar -C bin/ .

hadoop fs -rmr ~/$2

hadoop jar jar/WordCount.jar edu/duke/cs/WordCountDriver  ~/PartB_input/$1 ~/$2
  
hadoop fs -lsr ~/$2
	  
hadoop fs -cat ~/$2/part-*


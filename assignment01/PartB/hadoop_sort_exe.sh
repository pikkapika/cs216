if [ $# -ne 2 ] ; then
	echo "command input_filename output_dir"
	echo "e.g.) hadoop_exe.sh sample.txt PartB_output"
	exit
fi

hadoop fs -cp ~/$2/part-* ~/PartB_input/

jar -cvf jar/WordCount.jar -C bin/ .

hadoop dfs -cat  /Users/pika/PartA_input/part-*

hadoop fs -rmr ~/$2

hadoop jar jar/WordCount.jar edu/duke/cs/WordCountSortDriver  ~/PartB_input/part-r-00000 ~/$2
  
hadoop fs -lsr ~/$2
	  
hadoop fs -cat ~/$2/part-*


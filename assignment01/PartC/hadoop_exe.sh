if [ $# -ne 3 ] ; then
	echo "command input_filename output_dir classname"
	echo "e.g.) hadoop_exe.sh sample.txt PartC_output"
	exit
fi

jar -cvf jar/WhiteHouse.jar -C bin/ .

hadoop fs -rmr ~/$2

hadoop jar jar/WhiteHouse.jar edu/duke/cs/$3  ~/PartC_input/$1 ~/$2
  
hadoop fs -lsr ~/$2
	  
hadoop fs -cat ~/$2/part-*


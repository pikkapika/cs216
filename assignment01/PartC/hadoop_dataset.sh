hadoop fs -rmr ~/PartC_input
hadoop fs -mkdir ~/PartC_input
hadoop dfs -copyFromLocal data/* ~/PartC_input/

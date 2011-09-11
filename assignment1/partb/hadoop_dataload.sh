hadoop fs -rmr ~/PartB_input
hadoop fs -mkdir ~/PartB_input
hadoop dfs -copyFromLocal data/* ~/PartB_input/


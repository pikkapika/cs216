hadoop fs -rmr ~/PartA_input
hadoop fs -mkdir ~/PartA_input
hadoop dfs -copyFromLocal data/* ~/PartA_input/


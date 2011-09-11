# CS216 Assignment 1 - Part B

## Author
Koichi Ishida: pikkapika@gmail.com

## Directory
* bin - java class file 
* data - input data
* jar - jar file
* src - java source code

## Explanation
The purpose of this application is to count words of input file.
The main program is called by WordCountDriver using WordCountMapper for mapper and WordCountReducer for combiner and reducer. 

The following is key and value pair for mapper, combiner and reducer.

* (K1, V1) = (line#, line value)
* (K2, V2) = (word, 1)

* (K2, V2) = (word, the number of samples)
* (K3, V3) = (word, the number of samples)

## Execution
  ./hadoop_dataload.sh
  ./hadoop_suit_exe.sh

Please take a look at the inside of shell for more detail

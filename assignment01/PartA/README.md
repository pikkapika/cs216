# CS216 Assignment 1 - Part A

## Author
Koichi Ishida: pikkapika@gmail.com

## Directory
* bin - java class file 
* data - input data
* jar - jar file
* src - java source code

## Explanation
The purpose of this application is to calculate average temperature of NCDC data per each year.
The main program is called by AverageTemperatureDriver using AverageTemperatureMapper for mapper and AverageTemperatureReducer for combiner and reducer.

The mapper read each line of input file and generate key and value pair for combiner. The key is year and the value is temperature and number of samples. Since mapper only read one temperature sample per one time, number of samples is always one. Thus, the following is sample input and output. To get temperature from a line, AverageTemperatureMapper uses parser called NcdcRecordParser. 

* (K1, V1) = (line#, line value)
* (K2, V2) = (year, temperature of the line, 1)

After the mapper finish its job, combiner which is AverageTemperatureReducer is called. The combiner gets (K2, V2) as above shows, and then calculate average temperature and number of samples used to calculate the average temperature per year. Then generate key and value pair for reducer. The reason why the program needs the number of samples is to use reducer as combiner. If a value only shows average temperature, reducer do not know what the weight of average temperature. 23 degree with 10 sample and 23 degree with 1 sample are not the same. There are possibility for reducer to have multiple average temperature from combiner, the program need the number of samples. If the program does not need to use combiner, the number of sample is not necessarary. The key and pair for combiner and reducer is the following.

* (K2, V2) = (year, temperature of the line, the number of samples)
* (K3, V3) = (year, temperature of the line, the number of samples)

## Execution
./hadoop_dataload.sh
./hadoop_exe.sh 1901-1902 PartA_output

Please take a look at the inside of shell for more detail

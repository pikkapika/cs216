package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;

public class AverageTemperatureReducer extends
Reducer<Text, Text, Text, Text> {
    private static final String DELIMITER = ",";

    /**
     * (K2, V2) = (year, average temperature, # of samples)
     * (K3, V3) = (year, average temperature, # of samples)
     * 
     * It can be used as reducer
     */
    @Override
    public void reduce(Text key, Iterable<Text> values,
            Context context) throws IOException, InterruptedException {
        
        int     numOfValues = 0;
        float   sumOfValues = 0;
        for (Text value : values) {
            String line = value.toString();
            String result[] = line.split(DELIMITER);
            sumOfValues += Float.parseFloat(result[0]) * Integer.parseInt(result[1]);
            numOfValues += Integer.parseInt(result[1]);
        }
        context.write(key, new Text(sumOfValues/numOfValues + "," + numOfValues));
    }

}

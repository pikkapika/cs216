package edu.duke.cs;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.*;

import static org.mockito.Mockito.*;

public class AverageTemperatureReducerTest {
    @SuppressWarnings("rawtypes")
    private Reducer.Context context;
    private AverageTemperatureReducer reducer;
    private static final String DELIMITER = ",";
    
    @Before
    public void setUp() throws Exception {
        context = mock(Reducer.Context.class);
        reducer = new AverageTemperatureReducer();
    }
    
    @After
    public void tearDown() throws Exception {
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void returnsMaximumIntegerInvalues() throws IOException, InterruptedException {
        Text key = new Text("1901");
        Iterable<Text> values = Arrays.asList(new Text("22.2,5"), new Text("11.1,5"));

        reducer.reduce(key, values, context);
        verify(context).write(new Text("1901"), new Text("16.65" + DELIMITER + "10"));
    }
}
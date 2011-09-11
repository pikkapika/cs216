package edu.duke.cs;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.*;

import static org.mockito.Mockito.*;

public class SortReducerTest {
    @SuppressWarnings("rawtypes")
    private Reducer.Context context;
    private SortReducer reducer;
    private static final String DELIMITER = ",";
    
    @Before
    public void setUp() throws Exception {
        context = mock(Reducer.Context.class);
        reducer = new SortReducer();
    }
    
    @After
    public void tearDown() throws Exception {
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void returnsMaximumIntegerInvalues() throws IOException, InterruptedException {
        IntWritable key = new IntWritable(1);
        Iterable<Text> values = Arrays.asList(new Text("word"), new Text("doc"));

        reducer.reduce(key, values, context);
        verify(context).write(new IntWritable(1), new Text("word" + DELIMITER + "doc"));
    }
}
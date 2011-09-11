package edu.duke.cs;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.*;

import static org.mockito.Mockito.*;

public class WordCountReducerTest {
    @SuppressWarnings("rawtypes")
    private Reducer.Context context;
    private WordCountReducer reducer;
    
    @Before
    public void setUp() throws Exception {
        context = mock(Reducer.Context.class);
        reducer = new WordCountReducer();
    }
    
    @After
    public void tearDown() throws Exception {
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void returnsMaximumIntegerInvalues() throws IOException, InterruptedException {
        Text key = new Text("word");
        Iterable<IntWritable> values = Arrays.asList(new IntWritable(5), new IntWritable(10));

        reducer.reduce(key, values, context);
        verify(context).write(new Text("word"), new IntWritable(15));
    }
}
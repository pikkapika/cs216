package edu.duke.cs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Arrays;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Reducer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WhiteHouseVisitorReducerTest {
    @SuppressWarnings("rawtypes")
    private Reducer.Context context;
    private WhiteHouseVisitorReducer reducer;

    @Before
    public void setUp() throws Exception {
        context = mock(Reducer.Context.class);
        reducer = new WhiteHouseVisitorReducer();
    }
    
    @After
    public void tearDown() throws Exception {
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void returnsVisitorFullName() throws IOException, InterruptedException {
        Text key = new Text("CRUMBLY,ANGELIQUE,");
        Iterable<IntWritable> values = Arrays.asList(new IntWritable(3), new IntWritable(5));

        reducer.reduce(key, values, context);
        verify(context).write(new Text("CRUMBLY,ANGELIQUE,"), new IntWritable(8));
    }
}

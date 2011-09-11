package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.*;

import static org.mockito.Mockito.*;

public class SortMapperTest {

    @SuppressWarnings("rawtypes")
    private Mapper.Context context;
    private SortMapper mapper;
    
    @Before
    public void setUp() throws Exception {
        context = mock(Mapper.Context.class);
        mapper = new SortMapper();
    }
    
    @After
    public void tearDown() throws Exception {
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void processesValidRecord() throws IOException, InterruptedException {
        Text value = new Text("word\t131");
                
        mapper.map(null, value, context);
        verify(context).write(new IntWritable(13), new Text("word"));
    }
    
    /**
     * @TODO: check how to verify not called for invalid record value
     */

}

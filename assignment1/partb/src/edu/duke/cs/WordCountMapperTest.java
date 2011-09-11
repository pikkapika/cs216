package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.*;

import static org.mockito.Mockito.*;

public class WordCountMapperTest {
    @SuppressWarnings("rawtypes")
    private Mapper.Context context;
    private WordCountMapper mapper;
    private final static IntWritable one = new IntWritable(1);
    
    @Before
    public void setUp() throws Exception {
        context = mock(Mapper.Context.class);
        mapper = new WordCountMapper();
    }
    
    @After
    public void tearDown() throws Exception {
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void processesValidRecord() throws IOException, InterruptedException {
        Text value = new Text("1|AAAAAAAABAAAAAAA|980124|7135|32946|2452238|2452208|Mr.|" +
        		"Javier|Lewis|Y|9|12|1936|CHILE||Javier.Lewis@VFAxlnZEvOx.org|2452508|");
                
        mapper.map(null, value, context);
        /* @TODO: how to test the following? */
        /*
        verify(context, new Times(1)).write(new Text("1"), one);
        verify(context, new Times(1)).write(new Text("AAAAAAAABAAAAAAA"), one);
        verify(context, new Times(1)).write(new Text("980124"), one);
        verify(context, new Times(1)).write(new Text("7135"), one);
        verify(context, new Times(1)).write(new Text("32946"), one);
        verify(context, new Times(1)).write(new Text("2452238"), one);
        verify(context, new Times(1)).write(new Text("2452208"), one);
        verify(context, new Times(1)).write(new Text("Mr."), one);
        verify(context, new Times(1)).write(new Text("Javier"), one);
        verify(context, new Times(1)).write(new Text("Lewis"), one);
        verify(context, new Times(1)).write(new Text("Y"), one);
        verify(context, new Times(1)).write(new Text("9"), one);
        verify(context, new Times(1)).write(new Text("12"), one);
        verify(context, new Times(1)).write(new Text("1936"), one);
        verify(context, new Times(1)).write(new Text("CHILE"), one);
        verify(context, new Times(1)).write(new Text(""), one);
        verify(context, new Times(1)).write(new Text("Javier.Lewis@VFAxlnZEvOx.org"), one);
        */
        //verify(context, new Times(17)).write(new Text("2452508"), one);
    }
}

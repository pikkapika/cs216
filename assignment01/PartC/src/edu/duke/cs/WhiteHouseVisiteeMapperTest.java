package edu.duke.cs;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WhiteHouseVisiteeMapperTest {

    @SuppressWarnings("rawtypes")
    private Mapper.Context context;
    private WhiteHouseVisiteeMapper mapper;
    
    @Before
    public void setUp() throws Exception {
        context = mock(Mapper.Context.class);
        mapper = new WhiteHouseVisiteeMapper();
    }
    
    @After
    public void tearDown() throws Exception {
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void processesValidRecord() throws IOException, InterruptedException {
        Text value = new Text("CRUMBLY,ANGELIQUE,,U07467,,VA,,,,,5/18/2010 9:15,5/18/2010 9:10,5/18/2010 23:59,,1,B9,WIN,5/18/2010 9:15,B9,AABY,KATHERINE,OEOB,218,AABY,KATHERINE,,,8/27/2010");
        mapper.map(null, value, context);
        verify(context).write(new Text("AABY,KATHERINE"), new IntWritable(1));
    }

}

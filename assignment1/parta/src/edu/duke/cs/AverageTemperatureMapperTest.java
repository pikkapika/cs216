package edu.duke.cs;

import java.io.IOException;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.junit.*;

import static org.mockito.Mockito.*;

public class AverageTemperatureMapperTest {
    @SuppressWarnings("rawtypes")
    private Mapper.Context context;
    private AverageTemperatureMapper mapper;
    private static final String DELIMITER = ",";
    
    @Before
    public void setUp() throws Exception {
        context = mock(Mapper.Context.class);
        mapper = new AverageTemperatureMapper();
    }
    
    @After
    public void tearDown() throws Exception {
        
    }
    
    @SuppressWarnings("unchecked")
    @Test
    public void processesValidRecord() throws IOException, InterruptedException {
        Text value = new Text("0029029070999991901010106004+64333+023450FM-12+000599999V0202701N015919999999N0000001N9-00781+99999102001ADDGF108991999999999999999999");
        //                                    ^^^^                                                                    ^^^^^
        //                                    year                                                                    temperature
        mapper.map(null, value, context);
        verify(context).write(new Text("1901"), new Text("-78" + DELIMITER + "1"));
        
    }
    
}
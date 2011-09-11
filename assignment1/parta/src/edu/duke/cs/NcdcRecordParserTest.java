package edu.duke.cs;

import static org.junit.Assert.*;

import org.apache.hadoop.io.Text;
import org.junit.Test;

public class NcdcRecordParserTest {
    private NcdcRecordParser parser = new NcdcRecordParser();

        
    @Test
    public void testPositiveTemperature() {
        Text value = new Text("0029029070999991901010106004+64333+023450FM-12+000599999V0202701N015919999999N0000001N9+00781+99999102001ADDGF108991999999999999999999");
        //                                    ^^^^                                                                    ^^^^^
        //                                    year                                                                    temperature
        parser.parse(value);
        assertEquals(parser.getAirTemperature(), 78);
        assertEquals(parser.getYear(), "1901");
        assertEquals(parser.isValidTemperature(), true);
    }
    
    @Test
    public void testNevativeTemperature() {
        Text value = new Text("0029029070999991901010106004+64333+023450FM-12+000599999V0202701N015919999999N0000001N9-00781+99999102001ADDGF108991999999999999999999");
        //                                    ^^^^                                                                    ^^^^^
        //                                    year                                                                    temperature
        parser.parse(value);
        assertEquals(parser.getAirTemperature(), -78);
        assertEquals(parser.getYear(), "1901");
        assertEquals(parser.isValidTemperature(), true);
    }

    @Test
    public void testMalformatTemperature() {
        Text value = new Text("0029029070999991901010106004+64333+023450FM-12+000599999V0202701N015919999999N0000001N9111781+99999102001ADDGF108991999999999999999999");
        //                                    ^^^^                                                                    ^^^^^
        //                                    year                                                                    temperature
        parser.parse(value);
        assertEquals(parser.getYear(), "1901");
        assertEquals(parser.isValidTemperature(), false);
    }

}

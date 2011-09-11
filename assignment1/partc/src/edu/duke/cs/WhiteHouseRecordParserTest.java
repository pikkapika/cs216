package edu.duke.cs;

import static org.junit.Assert.*;

import org.apache.hadoop.io.*;
import org.junit.Test;

public class WhiteHouseRecordParserTest {
    private WhiteHouseRecordParser parser = new WhiteHouseRecordParser();
    
    @Test
    public void testProcessingValidRecord() {
        Text value = new Text("CRUMBLY,ANGELIQUE,,U07467,,VA,,,,,5/18/2010 9:15,5/18/2010 9:10,5/18/2010 23:59,,1,B9,WIN,5/18/2010 9:15,B9,AABY,KATHERINE,OEOB,218,AABY,KATHERINE,,,8/27/2010");
       
        parser.parse(value);
        assertEquals(parser.getVisitorFullname(), "CRUMBLY,ANGELIQUE,");
        assertEquals(parser.getVisiteeFullname(), "AABY,KATHERINE");
        assertEquals(parser.getVisitorAndVisiteeNames(), "CRUMBLY,ANGELIQUE,,AABY,KATHERINE");
        assertEquals(parser.getVisitorVisiteeMeetingPlace(), "CRUMBLY,ANGELIQUE,,AABY,KATHERINE,OEOB,218");
        assertEquals(parser.getMeetingPlace(), "OEOB,218");
    }
    
    @Test
    public void testProcessingInValidRecord() {
        Text value = new Text("CRUMBLY,ANGELIQUE,,U07467,,VA,,,5/18/2010 9:15,5/18/2010 9:10,5/18/2010 23:59,,1,B9,WIN,5/18/2010 9:15,B9,AABY,KATHERINE,OEOB,218,AABY,KATHERINE,,,8/27/2010");
       
        parser.parse(value);
        assertEquals(parser.isValidRecord(), false);
        assertEquals(parser.getVisitorFullname(), null);
    }
    

}

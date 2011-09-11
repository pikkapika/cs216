package edu.duke.cs;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.Text;

public class WhiteHouseRecordParser {
    private static final String DELIMITER = ",";
    private static final int NUM_FIELDS = 28;

    private String visitorFullname;
    private String visiteeFullname;
    private String meetingPlace;
    private boolean malformed;

    public void parse(String record) {
        if(StringUtils.countMatches(record, DELIMITER) != NUM_FIELDS - 1)
        {
            malformed = true;
            return;
        }
        
        String result[] = record.split(DELIMITER, NUM_FIELDS);
        
        visitorFullname = result[0] + DELIMITER + result[1] + DELIMITER + result[2];
        visiteeFullname = result[19] +  DELIMITER + result[20];
        meetingPlace = result[21] + DELIMITER + result[22];
        
        malformed = false;
    }
    
    public void parse(Text record) {
        parse(record.toString());
    }
    
    public String getVisitorFullname() {
        return visitorFullname;
    }
    
    public String getVisiteeFullname() {
        return visiteeFullname;
    }
    
    public String getVisitorAndVisiteeNames() {
        return visitorFullname + DELIMITER + visiteeFullname;
    }
    
    public String getVisitorVisiteeMeetingPlace() {
        return getVisitorAndVisiteeNames() + DELIMITER + getMeetingPlace();
    }
    
    public String getMeetingPlace() {
        return meetingPlace;
    }
    
    public boolean isValidRecord() {
        return !malformed;
    }
}

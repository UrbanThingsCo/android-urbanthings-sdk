package io.urbanthings.datamodel;

import java.util.ArrayList;
import java.util.Date;

public class TransitStopRTIReport {
    public String reportName;
    public String platformID;
    public ArrayList<MonitoredStopCall> upcomingCalls;
    public ArrayList<Disruption> disruptions;
    public String attributionLabel;
    public String attributionImageURL;
    public String noDataLabel;
    public String sourceName;
    public Date timeStamp;
}
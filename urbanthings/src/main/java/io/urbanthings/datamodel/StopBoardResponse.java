package io.urbanthings.datamodel;

import java.util.ArrayList;

public class StopBoardResponse {
    public boolean enableAutoRefresh;
    public Integer autoRefreshDuration;
    public String sourceName;
    public String stopID;
    public String textBackgroundColor;
    public String textColor;
    public String textDimColor;
    public String sourceIconURL;
    public String noDataLabel;
    public ArrayList<StopBoard> stopBoards;
}
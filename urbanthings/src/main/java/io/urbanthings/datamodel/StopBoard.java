package io.urbanthings.datamodel;

import java.util.ArrayList;

public class StopBoard {
    public boolean enableGroupFiltering;
    public String headerLabel;
    public boolean hidePlatform;
    public boolean hideSecondary;
    public String idHeader;
    public Integer idLabelWidth;
    public String mainHeader;
    public String platformHeader;
    public Integer platformLabelWidth;
    public String secondaryHeader;
    public String timeHeader;
    public Integer timeLabelWidth;
    public boolean interstitialPermitted;
    public String noDataLabel;
    public String attributionLabel;
    public String attributionImageURL;
    public String messagesIconURL;
    public ArrayList<StopBoardRow> rows;
    public ArrayList<StopBoardGroup> groups;
    public ArrayList<StopBoardMessage> messages;
    private String color;
    private String colorCompliment;
}

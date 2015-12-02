package io.urbanthings.datamodel;

import java.util.ArrayList;

public class DirectionsResponse {
    public String responseID;
    public DirectionsResponseStatus status;
    public ArrayList<Journey> journeys;
    public ArrayList<PlacePoint> placePoints;
    public String sourceName;
    public String warningsHTML;
    public String attributionsHTML;
}
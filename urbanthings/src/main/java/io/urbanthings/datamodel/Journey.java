package io.urbanthings.datamodel;

import io.urbanthings.helpers.PlacePointHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Journey {
    public Date arrivalTime;
    public Date departureTime;
    public String summaryHTML;
    public Long duration;
    public Integer changeCount;
    public ArrayList<JourneyLeg> legs;
    // not serialized, derived from originPlacePointID
    public transient PlacePoint originPlacePoint;
    // not serialized, derived from destinationPlacePointID
    public transient PlacePoint destinationPlacePoint;
    private String originPlacePointID;
    private String destinationPlacePointID;

    public void fixupReferences(List<PlacePoint> placePoints) {
        // find originPlacePoint from originPlacePointID
        originPlacePoint = PlacePointHelper.findPlacePoint(originPlacePointID, placePoints);
        // find destinationPlacePoint from destinationPlacePointID
        destinationPlacePoint = PlacePointHelper.findPlacePoint(destinationPlacePointID, placePoints);
        // JourneyLegs also retrieve PlacePoints from top level collection
        for (JourneyLeg leg : legs) {
            leg.fixupReferences(placePoints);
        }
    }
}

package io.urbanthings.datamodel;

import io.urbanthings.helpers.PlacePointHelper;

import java.util.Date;
import java.util.List;

public class JourneyLeg {
    public String summaryHTML;
    public String originPlacePointID;
    public String destinationPlacePointID;
    public Date arrivalTime;
    public Date departureTime;
    @VehicleType.Enum
    public int vehicleType = VehicleType.Tram;
    public Long duration;
    public Long distance;
    public String polyline;


    // not serialized, derived from IDs above
    public transient PlacePoint originPlacePoint;
    public transient PlacePoint destinationPlacePoint;


    public void fixupReferences(List<PlacePoint> placePoints) {
        originPlacePoint = PlacePointHelper.findPlacePoint(originPlacePointID, placePoints);
        destinationPlacePoint = PlacePointHelper.findPlacePoint(destinationPlacePointID, placePoints);
    }
}
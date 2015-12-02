package io.urbanthings.datamodel;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

public class DirectionsRequest {
    public static final int JOURNEY_TIME_MODE_DEPART_AFTER = 0;
    public static final int JOURNEY_TIME_MODE_ARRIVE_BY = 1;

    public PlacePoint origin;
    public PlacePoint destination;
    public Date departureTime;
    public Date arrivalTime;
    @DirectionsRequestJourneyTimeModes public int journeyTimeMode;
    public DirectionsRequestOptions options;
    public boolean enableRouteGeometry = true;
    public boolean enableRealtimeResults = true;
    public boolean enableFareResults;

    @IntDef({JOURNEY_TIME_MODE_DEPART_AFTER, JOURNEY_TIME_MODE_ARRIVE_BY})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DirectionsRequestJourneyTimeModes {
    }
}



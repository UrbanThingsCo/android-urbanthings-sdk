package io.urbanthings.datamodel;

import java.util.Date;

public class MonitoredStopCall {
    public Date expectedArrivalTime;
    public Date expectedDepartureTime;
    public Integer distanceMetres;
    public Integer masterDisplayFormat;
    public VehicleRTI vehicleRTI;
    public String platform;
    public Boolean isCancelled;
    public String stopCallID;
    public String agencyCode;
    public String stopID;
    public TransitTripInfo tripInfo;
    public TransitRouteInfo routeInfo;
    public TransitScheduledCall scheduledCall;
}

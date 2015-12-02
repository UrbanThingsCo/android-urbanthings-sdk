package io.urbanthings.datamodel;

public class TransitRouteInfo {
    public String agencyCode;
    public Double centrePointLat;
    public Double centrePointLng;
    public String lineColor;
    public String lineName;
    public String lineTextColor;
    public String operatorID;
    public String operatorName;
    public String operatorRegion;
    public String routeID;
    @VehicleType.Enum
    public int routeType;
}

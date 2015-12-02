package io.urbanthings.datamodel;

public class PlacePoint {
    public String primaryCode;
    public String name;
    public double lat;
    public double lng;
    public String localityName;
    @PlacePointType.Enum
    public int placePointType = PlacePointType.LatLng;
    public String iconURL;

    public PlacePoint() {
    }


    public PlacePoint(String name, double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
        this.name = name;
    }
}
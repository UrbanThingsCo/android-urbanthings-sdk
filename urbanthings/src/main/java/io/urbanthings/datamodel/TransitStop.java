package io.urbanthings.datamodel;

import java.util.ArrayList;

public class TransitStop extends PlacePoint {
    public String additionalCode;
    public String smsCode;
    public String directionName;
    public String stopIndicator;
    public boolean isClosed;
    public ArrayList<TransitStopRouteHint> routeHints;
    public Integer bearing;
    @VehicleType.Enum
    public int stopMode = VehicleType.Bus;

    public TransitStop() {
        super();
    }

    public TransitStop(String name, double lat, double lng) {
        super(name, lat, lng);
    }
}

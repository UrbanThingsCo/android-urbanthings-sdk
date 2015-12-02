package io.urbanthings.datamodel;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class PlacePointType {
    public final static int Place = 0;
    public final static int Road = 1;
    public final static int StopPoint = 2;
    public final static int Postcode = 3;
    public final static int LatLng = 4;
    public final static int Locality = 5;
    public final static int POI = 6;
    public final static int TransitStopTram = 100;
    public final static int TransitStopSubway = 101;
    public final static int TransitStopRail = 102;
    public final static int TransitStopBus = 103;
    public final static int TransitStopFerry = 104;
    public final static int TransitStopCableCar = 105;
    public final static int TransitStopGondola = 106;
    public final static int TransitStopFunicular = 107;
    public final static int TransitStopAir = 108;
    public final static int CycleHireDock = 111;
    public final static int CarParkingSpace = 112;

    @IntDef({Place, Road, StopPoint, Postcode, LatLng, Locality, POI,
            TransitStopTram, TransitStopSubway, TransitStopRail, TransitStopBus,
            TransitStopFerry, TransitStopCableCar, TransitStopGondola, TransitStopFunicular,
            TransitStopAir, CycleHireDock, CarParkingSpace})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Enum {
    }
}

package io.urbanthings.datamodel;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class VehicleType {
    public static final int Tram = 0;
    public static final int Subway = 1;
    public static final int Train = 2;
    public static final int Bus = 3;
    public static final int Ferry = 4;
    public static final int CableCar = 5;
    public static final int Gondola = 6;
    public static final int Funicular = 7;
    public static final int Plane = 8;
    public static final int Walk = 9;
    public static final int CycleOwned = 10;
    public static final int CycleHired = 11;
    public static final int Car = 12;
    public static final int Coach = 13;

    @IntDef({Tram, Subway, Train, Bus, Ferry, CableCar, Gondola,
            Funicular, Plane, Walk, CycleOwned,
            CycleHired, Car, Coach})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Enum {
    }
}

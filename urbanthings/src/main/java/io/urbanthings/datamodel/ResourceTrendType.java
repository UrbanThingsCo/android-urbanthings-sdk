package io.urbanthings.datamodel;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ResourceTrendType {
    public static final int Static = 0;
    public static final int Filling = 1;
    public static final int Emptying = 2;

    @IntDef({Static, Filling, Emptying})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Enum {
    }
}

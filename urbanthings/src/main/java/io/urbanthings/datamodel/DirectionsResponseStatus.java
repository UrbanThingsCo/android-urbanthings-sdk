package io.urbanthings.datamodel;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class DirectionsResponseStatus {
    public static final int UNSET = 0;
    public static final int STATUS_OK = 1;
    public static final int STATUS_WARNING = 2;
    public static final int STATUS_ERROR = 3;
    public static final int ERROR_SERVER_UNAVAILABLE = 1;
    public static final int ERROR_SERVER_ERROR = 2;

    @DirectionsResponseStatusCodes
    public int statusCode;
    @DirectionsResponseStatusErrorCodes
    public int errorCode;
    public String errorMessage;

    @IntDef({UNSET, STATUS_OK, STATUS_WARNING, STATUS_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DirectionsResponseStatusCodes {
    }

    @IntDef({UNSET, ERROR_SERVER_UNAVAILABLE, ERROR_SERVER_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DirectionsResponseStatusErrorCodes {
    }
}

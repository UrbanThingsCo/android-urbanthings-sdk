package io.urbanthings.helpers;

import android.text.TextUtils;

import io.urbanthings.datamodel.PlacePoint;

import java.util.List;

public class PlacePointHelper {
    public static PlacePoint findPlacePoint(final String uniqueId, final List<PlacePoint> placePoints) {
        PlacePoint retVal = null;
        if (!TextUtils.isEmpty(uniqueId) && (null != placePoints)) {
            int size = placePoints.size();
            for (int idx = 0; idx < size; idx++) {
                PlacePoint placePoint = placePoints.get(idx);
                if ((null != placePoint) && uniqueId.equals(placePoint.primaryCode)) {
                    retVal = placePoint;
                    break;
                }
            }
        }
        return retVal;
    }
}

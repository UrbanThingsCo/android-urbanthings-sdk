package io.urbanthings.datamodel;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class TransitJourneyLeg extends JourneyLeg {
    public TransitRouteInfo linkedTransitRouteInfo;
    public TransitTripInfo linkedTransitTripInfo;
    public ArrayList<TransitStopScheduledCall> scheduledStopCalls;
    public String referenceStopID;
    public boolean scheduledStopCallTimesAreAccurate;
    // not serialized, derived from referenceStopID above
    public transient TransitStop referenceStop;

    public static PlacePoint findPlacePoint(final String uniqueId, final List<TransitStopScheduledCall> placePoints) {
        PlacePoint retVal = null;
        if (!TextUtils.isEmpty(uniqueId) && (null != placePoints)) {
            int size = placePoints.size();
            for (int idx = 0; idx < size; idx++) {
                TransitStopScheduledCall stopCall = placePoints.get(idx);
                if ((null != stopCall) && (null != stopCall.stop) && uniqueId.equals(stopCall.stop.primaryCode)) {
                    retVal = stopCall.stop;
                    break;
                }
            }
        }
        return retVal;
    }

    @Override
    public void fixupReferences(List<PlacePoint> placePoints) {
        super.fixupReferences(placePoints);
        // for TransitJourneyLeg, the referenceStopID is a lookup into scheduledStopCalls
        PlacePoint placePoint = findPlacePoint(referenceStopID, scheduledStopCalls);
        if ((null != placePoint) && (placePoint instanceof TransitStop)) {
            referenceStop = (TransitStop) placePoint;
        }
    }
}

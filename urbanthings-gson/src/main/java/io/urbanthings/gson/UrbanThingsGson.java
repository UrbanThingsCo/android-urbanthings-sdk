package io.urbanthings.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import io.urbanthings.datamodel.DirectionsResponse;
import io.urbanthings.datamodel.Journey;
import io.urbanthings.datamodel.JourneyLeg;
import io.urbanthings.datamodel.PlacePoint;
import io.urbanthings.datamodel.TransitJourneyLeg;
import io.urbanthings.datamodel.TransitStop;
import io.urbanthings.helpers.DateHelper;

import java.util.ArrayList;

// If you use an alternate JSON library in your code, you won't need urbanthings-gson
public class UrbanThingsGson {
    // all dates are exchanged as strings with specific format; this helper configures
    // a new Gson instance appropriately.
    public static Gson getGson() {
        return new GsonBuilder()
                .setDateFormat(DateHelper.FAT_UTC_DATE_TIME_FORMAT)
                .create();
    }

    // DirectionsResponse may include polymorphic
    //    PlacePoint (TransitStop), JourneyLeg (TransitJourneyLeg) instances,
    //    as well as dates. This helper uses a suitably configured Gson instance.
    public static DirectionsResponse directionsResponseFromJson(String json) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(getPlacePointAdapterFactory())
                .registerTypeAdapterFactory(getJourneyLegAdapterFactory())
                .setDateFormat(DateHelper.FAT_UTC_DATE_TIME_FORMAT).create();

        DirectionsResponse retVal = gson.fromJson(json, DirectionsResponse.class);
        if (null != retVal) {
            // now need to fix up references within data structure:
            // PlacePoints passed in placePoints collection and referred to by serialized
            // Journey(s) (& contained JourneyLeg(s)) by uniqueIdentifier
            // Establish object references instead
            for (Journey journey : retVal.journeys) {
                journey.fixupReferences(retVal.placePoints);
            }
            // we no longer need collection of placePoints
            retVal.placePoints.clear();
        }
        return retVal;
    }

    public static ArrayList<PlacePoint> placePointArrayListFromJson(String json) {
        return getPlacePointGson().fromJson(
                json,
                new TypeToken<ArrayList<PlacePoint>>() {
                }.getType());
    }

    public static Gson getPlacePointGson() {
        // when serializing or deserializing a PlacePoint, use Gson instance that understands
        // polymorphic serialization of PlacePoints...!
        return new GsonBuilder()
                .registerTypeAdapterFactory(getPlacePointAdapterFactory())
                .create();
    }

    public static RuntimeTypeAdapterFactory<PlacePoint> getPlacePointAdapterFactory() {
        // NOTES: TransitStop is derived from PlacePoint.
        // API returns list of PlacePoint for GET static/stops where each can be PlacePoint or TransitStop
        // For each item in the list, field "subClassType" is used to discriminate between these types.
        // RuntimeTypeAdapterFactory<PlacePoint> provides mechanism for polymorphic deserialization.
        RuntimeTypeAdapterFactory<PlacePoint> retVal = RuntimeTypeAdapterFactory.of(
                PlacePoint.class,
                "subClassType");
        retVal.registerSubtype(TransitStop.class, "TransitStop");
        return retVal;
    }

    public static RuntimeTypeAdapterFactory<JourneyLeg> getJourneyLegAdapterFactory() {
        // NOTES: TransitJourneyLeg is derived from JourneyLeg.
        // Journey contains ArrayList<JourneyLeg> where each leg might be JourneyLeg or TransitJourneyLeg
        // For each item in the list, (default) field "type" is used to discriminate between these types.
        // RuntimeTypeAdapterFactory<JourneyLeg> provides mechanism for polymorphic deserialization.
        RuntimeTypeAdapterFactory<JourneyLeg> retVal = RuntimeTypeAdapterFactory.of(
                JourneyLeg.class);
        retVal.registerSubtype(TransitJourneyLeg.class, "TransitJourneyLeg");
        return retVal;
    }
}
package com.example.urbanthings.network;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import io.urbanthings.datamodel.PlacePoint;
import io.urbanthings.datamodel.VehicleType;
import io.urbanthings.helpers.CoreUtils;

// This sample application uses async-http for asynchronous HTTP requests.
// Documentation at: https://loopj.com/android-async-http/
// In particular, please see section titled:  'Recommended Usage: Make a Static Http Client'
// You are of course free to use whatever library you may prefer in your own code!
public class UrbanThingsApiClient {
    // Base URL for all UrbanThings API calls
    private static final String BASE_URL = "https://bristol.api.urbanthings.io/api/1.0/";

    // You will need to register to use the API and put your own API key here...!
    // Please visit: https://portal-bristol.api.urbanthings.io
    public static final String API_KEY = "";

    // example uses 2 endpoints
    public static final String GET_STOPS = "static/stops";
    public static final String GET_STATUS = "rti/resources/status";

    // set a 20s timeout for testing purposes
    private static final int TIMEOUT_MS = 20000;

    // single instance of AsyncHttpClient
    private static final AsyncHttpClient sClient;

    static {
        sClient = new AsyncHttpClient();
        // NOTE: all UrbanThings API calls require language to be specified in this form
        sClient.addHeader("Accept-Language", CoreUtils.getLanguageCountryCodeCombo());
        // set timeout
        sClient.setTimeout(TIMEOUT_MS);
    }

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        sClient.get(getAbsoluteUrl(url), params, responseHandler);
    }

    // HELPER: prepare RequestParams for GET_STOPS to return all CarParks within specified LatLng bounds
    public static RequestParams getCarParkRequestParams(double minLat, double maxLat, double minLng, double maxLng) {
        RequestParams retVal = new RequestParams();
        retVal.add("apikey", UrbanThingsApiClient.API_KEY);
        retVal.add("stopmodes", String.valueOf(VehicleType.Car));
        //retVal.add("stopmodes", "WANT_ERROR");
        retVal.add("minlat", String.valueOf(minLat));
        retVal.add("maxlat", String.valueOf(maxLat));
        retVal.add("minlng", String.valueOf(minLng));
        retVal.add("maxlng", String.valueOf(maxLng));
        return retVal;
    }

    // HELPER: prepare RequestParams to call GET_STATUS for a particular PlacePoint
    public static RequestParams getPlacePointStatusRequestParams(PlacePoint placePoint) {
        RequestParams retVal = new RequestParams();
        retVal.add("apikey", UrbanThingsApiClient.API_KEY);
        retVal.add("stopids", placePoint.primaryCode);
        return retVal;
    }

    // URL consists of our BASE_URL + specific endpoint e.g.: GET_STATUS
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}
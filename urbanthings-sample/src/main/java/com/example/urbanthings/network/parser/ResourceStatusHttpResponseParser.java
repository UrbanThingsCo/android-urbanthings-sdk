package com.example.urbanthings.network.parser;

import android.text.TextUtils;
import android.util.Log;

import com.example.urbanthings.network.UrbanThingsApiException;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.BaseJsonHttpResponseHandler;
import io.urbanthings.datamodel.ResourceStatus;
import io.urbanthings.gson.UrbanThingsGson;
import io.urbanthings.helpers.UrbanThingsApiResponse;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

// This sample code extends BaseJsonHttpResponseHandler<T> so that all deserialization is handled
//   on a background thread.
// Note that the error handling included is unlikely to be sufficient for a production app!
public abstract class ResourceStatusHttpResponseParser extends BaseJsonHttpResponseHandler<List<ResourceStatus>> {
    private static final String LOG_TAG = ResourceStatusHttpResponseParser.class.getSimpleName();

    // NOTE: if an exception is thrown within parseResponse, passed to user via:
    // onFailure(..., Throwable throwable, ...)
    @Override
    protected List<ResourceStatus> parseResponse(String rawJsonData, boolean isFailure) throws Throwable {
        List<ResourceStatus> retVal = null;
        try {
            UrbanThingsApiResponse response = UrbanThingsApiResponse.fromRawJson(rawJsonData);
            if (null == response) {
                throw new UrbanThingsApiException("Invalid response from server");
            } else if (!response.success) {
                StringBuilder sb = new StringBuilder("Unsuccessful");
                if (!TextUtils.isEmpty(response.localizedErrorMessage)) {
                    sb.append(": ");
                    sb.append(response.localizedErrorMessage);
                }
                throw new UrbanThingsApiException(sb.toString());
            } else if (null != response.data) {
                // we have a successful UrbanThingsApiResponse with data...
                retVal = UrbanThingsGson.getGson().fromJson(
                        response.data.toString(),
                        new TypeToken<ArrayList<ResourceStatus>>() {
                        }.getType());
            }
        } catch (JSONException | JsonParseException ex) {
            Log.e(LOG_TAG, "Failed to parse response", ex);
            throw ex;
        }
        return retVal;
    }
}

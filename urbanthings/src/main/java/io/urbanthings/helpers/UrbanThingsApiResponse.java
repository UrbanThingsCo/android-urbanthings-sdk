package io.urbanthings.helpers;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class UrbanThingsApiResponse {
    public static final String KEY_SUCCESS = "success";
    public static final String KEY_DATA = "data";
    public static final String KEY_LOCALIZED_ERROR_MESSAGE = "localizedErrorMessage";

    public final boolean success;
    public final String localizedErrorMessage;
    public final Object data;

    public UrbanThingsApiResponse(boolean success, Object data, String localizedErrorMessage) {
        this.success = success;
        this.data = data;
        this.localizedErrorMessage = localizedErrorMessage;
    }

    public static UrbanThingsApiResponse fromRawJson(String rawJson) throws JSONException {
        UrbanThingsApiResponse retVal = null;
        if (!TextUtils.isEmpty(rawJson)) {
            rawJson = rawJson.trim();
            // we need to Parse the top level response object
            if (rawJson.startsWith("{")) {
                // OK, parse the string...
                Object obj = new JSONTokener(rawJson).nextValue();
                // we are expecting a JSONObject, so let's make sure
                JSONObject response = CoreUtils.safeCast(obj, JSONObject.class);
                if (null != response) {
                    // we have a JSONObject from our response
                    // KEY_SUCCESS, KEY_DATA are *required* fields (hence JSONException if missing)
                    // KEY_LOCALIZED_ERROR_MESSAGE *optional*
                    retVal = new UrbanThingsApiResponse(
                            response.getBoolean(KEY_SUCCESS),
                            response.get(KEY_DATA),
                            response.has(KEY_LOCALIZED_ERROR_MESSAGE) ?
                                    response.getString(KEY_LOCALIZED_ERROR_MESSAGE) : null);
                }
            }
        }
        return retVal;
    }
}

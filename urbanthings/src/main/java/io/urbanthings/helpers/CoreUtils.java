package io.urbanthings.helpers;

import android.text.TextUtils;

import java.util.Locale;
import java.util.MissingResourceException;

public class CoreUtils {
    public static String getLanguageCountryCodeCombo() {
        return getLanguageCode() + "-" + getCountryCode();
    }

    public static String getLanguageCode() {
        try {
            return Locale.getDefault().getLanguage();
        } catch (MissingResourceException miex) {
            return "en";
        }
    }

    public static String getCountryCode() {
        try {
            return Locale.getDefault().getCountry();
        } catch (MissingResourceException miex) {
            return "gb";
        }
    }

    public static <T> T safeCast(Object obj, Class<T> type) {
        if (type.isInstance(obj)) {
            return (T) obj;
        }
        return null;
    }

    public static String emptyStringIfNull(String value) {
        return TextUtils.isEmpty(value) ? "" : value;
    }
}

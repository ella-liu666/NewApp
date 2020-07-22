package com.haixiajiemei.app.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class PrefsHelper {
    private static final String PREF_LOGIN_INFO = "LoginInfo";
    private static final String KEY_ACCESS_TOKEN = "accessToken";

    private static final String PREF_USER_INFO = "userInfo";

    public static void clearData(@NonNull Context context) {
        String[] prefs;

        prefs = new String[]{PREF_LOGIN_INFO, PREF_USER_INFO};
        for (String pref : prefs) {
            SharedPreferences preferences = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();

            editor.clear();
            editor.apply();
        }
    }

    public static String getAccessToken(@NonNull Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_LOGIN_INFO, Context.MODE_PRIVATE);

        return preferences.getString(KEY_ACCESS_TOKEN, "");
    }

    public static void setAccessToken(@NonNull Context context, String token) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_LOGIN_INFO, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_ACCESS_TOKEN, token);
        editor.apply();
    }
}
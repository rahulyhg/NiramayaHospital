package com.infobite.niramayahospital.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Rahul Mishra on 7/1/2017.
 */

public class AppPreference {

    private static final String APP_PREFENCE = "app_preferences_niramaya_hospital";

    public static void setTokenPreference(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences("DEVICE_TOKEN", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getTokenPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences("DEVICE_TOKEN", Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public static void setStringPreference(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFENCE, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    public static void setIntegerPreference(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getIntegerPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFENCE, Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    public static void setBooleanPreference(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBooleanPreference(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFENCE, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    public static void clearAllPreferences(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}



package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class Utilities {
    public static String First_Ans;
    public static String Second_Ans;
    public static String Third_Ans;
    public static String Fourth_Ans;
    public static String Fifth_Ans;
    public static String Sixth_Ans;
    public static String Seventh_Ans;
    public static String Eight_Ans;
    public static String Ninth_Ans;
    public static String Tenth_Ans;
    public static boolean Result_btn_click;
    private int screen_Width, screen_height;
    public static void savePref(Context context, String key, String value) {

        SharedPreferences sharedPref = context.getSharedPreferences("Pref-Values", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }


    public static String getpref(Context context, String key, String defaultValue) {
        SharedPreferences sharedPref = context.getSharedPreferences("Pref-Values", Context.MODE_PRIVATE);
        return sharedPref.getString(key, defaultValue);
    }

}

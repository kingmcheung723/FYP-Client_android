package com.example.tszwingyim.pricesharingapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

class MySharedPreference {
    private static final String MemberName = "MemberName";
    private static final String MyPreferenceName = "MyPreferenceName";

    public static void saveMemberName(String memberName, Context context) {
        if (memberName != null && memberName.length() > 0) {
            SharedPreferences preference = context.getSharedPreferences(MyPreferenceName, 0);
            SharedPreferences.Editor editor = preference.edit();
            editor.putString(MemberName, memberName);
            // Commit the edits!
            editor.commit();
        }
    }

    public static String getMemberName(Context context) {
        SharedPreferences preference = context.getSharedPreferences(MyPreferenceName, 0);
        return preference.getString(MemberName, null);
    }
}

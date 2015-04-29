package com.example.tszwingyim.pricesharingapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

class MySharedPreference {
    private static final String MemberName = "MemberName";
    private static final String MyPreferenceName = "MyPreferenceName";
    private static final String LOCALE = "locale";

    public static void saveMemberName(String memberName, Context context) {
        if (memberName != null && memberName.length() > 0) {
            SharedPreferences preference = context.getSharedPreferences(MyPreferenceName, 0);
            SharedPreferences.Editor editor = preference.edit();
            editor.putString(MemberName, memberName);
            // Commit the edits!
            editor.commit();
        }
    }

    public static void clearMemberName(Context context) {
        SharedPreferences preference = context.getSharedPreferences(MyPreferenceName, 0);
        SharedPreferences.Editor editor = preference.edit();
        editor.remove(MemberName);
        // Commit the edits!
        editor.commit();
    }

    public static String getMemberName(Context context) {
        SharedPreferences preference = context.getSharedPreferences(MyPreferenceName, 0);
        return preference.getString(MemberName, null);
    }

    public static void displayDialog(String message, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Alert")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void setLocale(String localeStr, Context context) {
        if (localeStr != null) {
            SharedPreferences preference = context.getSharedPreferences(MyPreferenceName, 0);
            SharedPreferences.Editor editor = preference.edit();
            editor.putString(LOCALE, localeStr);
            // Commit the edits!
            editor.commit();
        }
    }

    public static String getLocale(Context context) {
        SharedPreferences preference = context.getSharedPreferences(MyPreferenceName, 0);
        return preference.getString(LOCALE, "");
    }
}

package com.example.tszwingyim.pricesharingapplication;

import android.util.Log;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

/**
 * Created by tszwingyim on 5/4/15.
 */
class GraphXLabelFormat extends Format {
    final String[] xLabels = {"03-30", "03-31", "04-01", "04-02", "04-03","04-04", "04-05","04-06", "04-07","04-08", "04-09"};
    @Override
    public StringBuffer format(Object arg0, StringBuffer arg1, FieldPosition arg2) {
        // TODO Auto-generated method stub

        int parsedInt = Math.round(Float.parseFloat(arg0.toString()));
        Log.d("test", parsedInt + " " + arg1 + " " + arg2);
        String labelString = xLabels[parsedInt];
        arg1.append(labelString);
        return arg1;
    }

    @Override
    public Object parseObject(String arg0, ParsePosition arg1) {
        // TODO Auto-generated method stub
        return java.util.Arrays.asList(xLabels).indexOf(arg0);
    }
}
package com.example.tszwingyim.pricesharingapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tszwingyim on 12/4/15.
 */
public class SharePriceCustomList extends ArrayAdapter <String> {
    private final Activity context;
    private final String[] contentString;

    public SharePriceCustomList(Activity context,
                      String[] contentString) {
        super(context, R.layout.list_single, contentString);
        this.context = context;
        this.contentString = contentString;
    }
    
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.share_price_list_single, null, true);
        TextView shopNameTextView = (TextView) rowView.findViewById(R.id.txt);
        TextView memberNameTextView = (TextView) rowView.findViewById(R.id.txt);
        TextView priceTextView = (TextView) rowView.findViewById(R.id.txt);
        TextView dateTextView = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setText(this.contentString[position]);
        if (this.imageId != null) {
            imageView.setImageResource(this.imageId[position]);
        }
        return rowView;
    }
}

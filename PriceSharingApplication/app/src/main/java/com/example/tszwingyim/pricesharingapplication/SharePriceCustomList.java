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
        super(context, R.layout.share_price_list_single, contentString);
        this.context = context;
        this.contentString = contentString;
    }

    @Override
     public int getCount(){
        return contentString!=null ? contentString.length / 4 : 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.share_price_list_single, null, true);
        TextView shopNameTextView = (TextView) rowView.findViewById(R.id.textView_shopname);
        TextView shopAddressTextView = (TextView) rowView.findViewById(R.id.textView_address);
        TextView priceTextView = (TextView) rowView.findViewById(R.id.textView_price);
        TextView dateTextView = (TextView) rowView.findViewById(R.id.textView_sharedate);

        int min = position * 4;
        priceTextView.setText(this.contentString[min]);
        shopNameTextView.setText(this.contentString[min + 1]);
        shopAddressTextView.setText(this.contentString[min + 2]);
        dateTextView.setText(this.contentString[min + 3]);

        return rowView;
    }
}

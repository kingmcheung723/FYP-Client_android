package com.example.tszwingyim.pricesharingapplication;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
public class CustomList extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] contentString;
    private final Integer[] imageId;
    public CustomList(Activity context,
                      String[] contentString, Integer[] imageId) {
        super(context, R.layout.list_single, contentString);
        this.context = context;
        this.contentString = contentString;
        this.imageId = imageId;
    }
    public CustomList(Activity context,
                      String[] contentString) {
        super(context, R.layout.list_single, contentString);
        this.context = context;
        this.contentString = contentString;
        this.imageId = null;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(this.contentString[position]);
        if (this.imageId != null) {
            imageView.setImageResource(this.imageId[position]);
        }
        return rowView;
    }
}
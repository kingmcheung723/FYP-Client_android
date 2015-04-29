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
        final View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        final ImageView imageView = (ImageView) rowView.findViewById(R.id.img);

        final String itemName = this.contentString[position];
        txtTitle.setText(itemName);
        if (this.imageId != null) {
            imageView.setImageResource(this.imageId[position]);
        }
        final DBManager dbManager = new DBManager();
        dbManager.queryCallBack = new QueryCallBack() {
            @Override
            public void queryResult(String result) {
                if (result != null && result.length() > 0) {
                    MyStringTokenizer token = new MyStringTokenizer(result, "|");
                    String imageLink = token.nextToken();
                    if (imageLink != null || imageLink.equalsIgnoreCase("null")) {
                        String uri = "@drawable/" + imageLink;
                        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
                        if (imageResource != 0) {
                            Drawable drawable = context.getResources().getDrawable(imageResource);
                            imageView.setImageDrawable(drawable);
                        } else {
                            Drawable drawable = context.getResources().getDrawable(R.drawable.ic_launcher);
                            imageView.setImageDrawable(drawable);
                        }
                    }
                }
            }
        };
        String sql = "SELECT image_link FROM goods WHERE goods.name_zh = '" + itemName + "' OR goods.name_en = '" + itemName + "'";
        dbManager.querySql(sql);

        return rowView;
    }
}
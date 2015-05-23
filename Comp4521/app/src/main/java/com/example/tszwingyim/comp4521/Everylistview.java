package com.example.tszwingyim.comp4521;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class Everylistview extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everylistview);

        String category = getIntent().getExtras().getString("category");
        if (category != null) {
            if (category.equalsIgnoreCase("others")) {
                setTitle("Others");
            } else if (category.equalsIgnoreCase("canteens")) {
                setTitle("Canteens");
            } else if (category.equalsIgnoreCase(("sports"))) {
                setTitle("Sprouts");
            }
        }
    }

    private void setTitle(String title) {
        TextView textview = (TextView) findViewById(R.id.Category_title);
        if (textview != null) {
            textview.setText(title + " Facilities");
        }
    }

    private void displayList() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_everylistview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

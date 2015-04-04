package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.View;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;
import android.widget.Button;
import java.util.Arrays;

public class Pricechart extends ActionBarActivity {
    private XYPlot plot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // initialize our XYPlot reference:
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
        setContentView(R.layout.activity_pricechart);
        // initialize our buttons reference:
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button category = (Button)findViewById(R.id.button_category);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button commentlist = (Button)findViewById(R.id.button_comment);
        Button sharepricelist = (Button)findViewById(R.id.button_shareprice);
        Button itempage = (Button)findViewById(R.id.button_priceinfo);


        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Pricechart.this, Member.class);
                startActivity(intent);

            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Pricechart.this, Recommendation.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Pricechart.this, MainActivity.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Pricechart.this, Barcode.class);
                startActivity(intent);
            }
        });
        itempage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Pricechart.this, Itempage.class);
                startActivity(intent);

            }
        });
        commentlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Pricechart.this, Commentlist.class);
                startActivity(intent);

            }
        });
        sharepricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Pricechart.this, SharePricelist.class);
                startActivity(intent);
            }
        });

        // Create a couple arrays of y-values to plot:
        Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
        Number[] series2Numbers = {4, 6, 3, 8, 2, 10};

        // Turn the above arrays into XYSeries':
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                "Series1");                             // Set the display title of the series

        // same as above
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");

        // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
//        LineAndPointFormatter series1Format = new LineAndPointFormatter();
//        series1Format.setPointLabelFormatter(new PointLabelFormatter());
//        series1Format.configure(getApplicationContext(),
//                R.xml.line_point_formatter_with_plf1);
        LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);

        // add a new series' to the xyplot:
        plot.addSeries(series1, series1Format);

        // same as above:
//        LineAndPointFormatter series2Format = new LineAndPointFormatter();
//        series2Format.setPointLabelFormatter(new PointLabelFormatter());
//        series2Format.configure(getApplicationContext(),
//                R.xml.line_point_formatter_with_plf2);
        LineAndPointFormatter series2Format = new LineAndPointFormatter(Color.RED, Color.GREEN, Color.BLUE, null);
        plot.addSeries(series2, series2Format);

        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pricechart, menu);
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

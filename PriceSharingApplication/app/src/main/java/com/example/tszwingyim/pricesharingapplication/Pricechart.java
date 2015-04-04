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
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

        List s1 = getSeries(20, 10);
        XYSeries series1 = new SimpleXYSeries(s1,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 1");

        List s2 = getSeries(20, 10);
        XYSeries series2 = new SimpleXYSeries(s2,
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series 2");

        LineAndPointFormatter s1Format = new LineAndPointFormatter();
        s1Format.setPointLabelFormatter(new PointLabelFormatter());
        s1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf1);
        plot.addSeries(series1, s1Format);

        LineAndPointFormatter s2Format = new LineAndPointFormatter();
        s2Format.setPointLabelFormatter(new PointLabelFormatter());
        s2Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf2);
        plot.addSeries(series2, s2Format);

        plot.setTicksPerRangeLabel(1);
        plot.getGraphWidget().setDomainLabelOrientation(-45);

    }


    private List getSeries(int count, int max) {
        List series = new ArrayList();
        Random rand = new Random();
        for (int i = 1; i <= count; i++) {
            int value = rand.nextInt(max);
            series.add(rand.nextInt(max));
        }
        return series;
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

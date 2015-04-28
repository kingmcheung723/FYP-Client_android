package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYStepMode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Pricechart extends ActionBarActivity {
    private XYPlot plot;
    private ProgressBar mProgressBar = null;
    private List<Number> WelcomeGoodPrices = new ArrayList<Number>();
    private List<Number> ParknShopGoodPrices = new ArrayList<Number>();
    private List<Number> AeonGoodPrice = new ArrayList<Number>();
    private List<Number> DchGoodPrices = new ArrayList<Number>();
    private List<Number> MarketPlaceGoodPrices = new ArrayList<Number>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_pricechart);
        // initialize our buttons reference:
        Button recommend = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button commentlist = (Button) findViewById(R.id.button_comment);
        Button sharepricelist = (Button) findViewById(R.id.button_shareprice);
        Button itempage = (Button) findViewById(R.id.button_priceinfo);



        final String itemId = getIntent().getExtras().getString("ITEM_ID");

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(Pricechart.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(Pricechart.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(Pricechart.this, Member.class);
                    startActivity(intent);
                }
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
                Intent intent = new Intent(Pricechart.this, Itempage.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);

            }
        });
        commentlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Pricechart.this, Commentlist.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);

            }
        });
        sharepricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Pricechart.this, SharePricelist.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);
            }
        });

        drawChart();
    }

    private Number[] toNumberArray(List<Number> listNumber) {
        if (listNumber != null) {
            Number[] numberArray = new Number[listNumber.size()];
            for (int i = 0; i < numberArray.length; i++) {
                Number n = listNumber.get(i);
                numberArray[i] = n;
            }
            return numberArray;
        }
        return new Number[0];
    }

    private List<Number> toNumberList(float[] floatArray) {
        if (floatArray != null) {
            List<Number> numberList = new ArrayList<Number>();
            for (int i = 0; i < floatArray.length; i++) {
                numberList.add(floatArray[i]);
            }
            return numberList;
        }
        return new ArrayList<Number>();
    }

    private void drawChart() {
        // initialize our XYPlot reference:
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);
        // Create a couple arrays of y-values to plot:
        Number[] series1Numbers = {8.10, 8.20, 8.30, 8.40, 8.50, 8.60, 8.70, 8.80, 8.90, 9.00, 9.10};
        Number[] series2Numbers = {8.20, 8.30, 8.40, 8.50, 8.60, 8.70, 8.80, 8.90, 9.00, 9.10, 9.20};
        Number[] series3Numbers = {8.30, 8.40, 8.50, 8.60, 8.70, 8.80, 8.90, 9.00, 9.10, 9.20, 9.30};
        Number[] series4Numbers = {8.40, 8.50, 8.60, 8.70, 8.80, 8.90, 9.00, 9.10, 9.20, 9.30, 9.40};
        Number[] series5Numbers = {8.50, 8.60, 8.70, 8.80, 8.90, 9.00, 9.10, 9.20, 9.30, 9.40, 9.50};

        float[] welcomePriaces = getIntent().getExtras().getFloatArray("welcomePriaces");
        float[] parkNShopPrices = getIntent().getExtras().getFloatArray("parkNShopPrices");
        float[] aeonPrices = getIntent().getExtras().getFloatArray("aeonPrices");
        float[] dchPrices = getIntent().getExtras().getFloatArray("dchPrices");
        float[] marketPlasePrices = getIntent().getExtras().getFloatArray("marketPlasePrices");

        WelcomeGoodPrices = Arrays.asList(series1Numbers);
        ParknShopGoodPrices = Arrays.asList(series2Numbers);
        AeonGoodPrice = Arrays.asList(series3Numbers);
        DchGoodPrices = Arrays.asList(series4Numbers);
        MarketPlaceGoodPrices = Arrays.asList(series5Numbers);

        // Turn the above arrays into XYSeries':
        // SimpleXYSeries takes a List so turn our array into a List
        // Y_VALS_ONLY means use the element index as the x value
        // Set the display title of the series
//        XYSeries series1 = new SimpleXYSeries(Arrays.asList(series1Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "百佳");
//        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Wellcome");
//        XYSeries series3 = new SimpleXYSeries(Arrays.asList(series3Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Aeon");
//        XYSeries series4 = new SimpleXYSeries(Arrays.asList(series4Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "大昌");
//        XYSeries series5 = new SimpleXYSeries(Arrays.asList(series5Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Market Place");

        XYSeries series1 = new SimpleXYSeries(toNumberList(parkNShopPrices), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "百佳");
        XYSeries series2 = new SimpleXYSeries(toNumberList(welcomePriaces), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Wellcome");
        XYSeries series3 = new SimpleXYSeries(toNumberList(aeonPrices), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Aeon");
        XYSeries series4 = new SimpleXYSeries(toNumberList(dchPrices), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "大昌");
        XYSeries series5 = new SimpleXYSeries(toNumberList(marketPlasePrices), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Market Place");

        // Create a formatter to use for drawing a series using LineAndPointRenderer
        // and configure it from xml:
        LineAndPointFormatter series1Format = new LineAndPointFormatter();
        series1Format.setPointLabelFormatter(new PointLabelFormatter());
        series1Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf1);

        // add a new series' to the xyplot:
        plot.getGraphWidget().setDomainValueFormat(new GraphXLabelFormat());
        plot.addSeries(series1, series1Format);

        // same as above:
        LineAndPointFormatter series2Format = new LineAndPointFormatter();
        series2Format.setPointLabelFormatter(new PointLabelFormatter());
        series2Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf2);
        plot.addSeries(series2, series2Format);
        // same as above:
        LineAndPointFormatter series3Format = new LineAndPointFormatter();
        series3Format.setPointLabelFormatter(new PointLabelFormatter());
        series3Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf3);
        plot.addSeries(series3, series3Format);
        // same as above:
        LineAndPointFormatter series4Format = new LineAndPointFormatter();
        series4Format.setPointLabelFormatter(new PointLabelFormatter());
        series4Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf4);
        plot.addSeries(series4, series4Format);
        // same as above:
        LineAndPointFormatter series5Format = new LineAndPointFormatter();
        series5Format.setPointLabelFormatter(new PointLabelFormatter());
        series5Format.configure(getApplicationContext(),
                R.xml.line_point_formatter_with_plf5);
        plot.addSeries(series5, series5Format);

        // thin out domain tick labels so they dont overlap each other:
        plot.setDomainStepMode(XYStepMode.INCREMENT_BY_VAL);
        plot.setDomainStepValue(1);

        plot.setRangeStepMode(XYStepMode.INCREMENT_BY_VAL);
        plot.setRangeStepValue(1);

        // reduce the number of range labels
        plot.setTicksPerRangeLabel(1);
        plot.getGraphWidget().setDomainLabelOrientation(90);
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


}


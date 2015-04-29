package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.Locale;


public class SharePricelist extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Locale locale = new Locale(MySharedPreference.getLocale(this));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());


        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_share_pricelist);
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button category = (Button)findViewById(R.id.button_category);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button pricechart = (Button)findViewById(R.id.button_pricechart);
        Button commentlist = (Button)findViewById(R.id.button_comment);
        Button itempage = (Button)findViewById(R.id.button_priceinfo);

        final String itemId = getIntent().getExtras().getString("ITEM_ID");

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(SharePricelist.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(SharePricelist.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(SharePricelist.this, Member.class);
                    startActivity(intent);
                }
            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(SharePricelist.this, Recommendation.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(SharePricelist.this, MainActivity.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(SharePricelist.this, Barcode.class);
                startActivity(intent);
            }
        });
        pricechart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(SharePricelist.this, Pricechart.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);
            }
        });
        commentlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(SharePricelist.this, Commentlist.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);
            }
        });
        itempage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(SharePricelist.this, Itempage.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);
            }
        });
        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);
        DBManager dbManager = new DBManager();
        dbManager.queryCallBack = new QueryCallBack() {
            @Override
            public void queryResult(String result) {
                progressBar.setVisibility(View.GONE);
                if (result != null) {
                    MyStringTokenizer token = new MyStringTokenizer(result, "|");
                    String[] comments = new String[token.countTokens()];
                    int count = 0;
                    while (token.hasMoreTokens()) {
                        comments[count] = token.nextToken().toString();
                        count++;
                    }
                    SharePriceCustomList adapter = new
                            SharePriceCustomList(SharePricelist.this, comments);
                    ListView list = (ListView) findViewById(R.id.listView);
                    if (list != null) {
                        list.setAdapter(adapter);
                    }
                }
            }
        };
        String queryCommentsSQL = "SELECT price, shop_location, shop_name, createddate FROM share_prices WHERE good_id = '" + itemId + "'";
        dbManager.querySql(queryCommentsSQL);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share_pricelist, menu);
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

package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.Locale;


public class Commentlist extends ActionBarActivity {

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
        setContentView(R.layout.activity_commentlist);
        Button recommend = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button pricechart = (Button) findViewById(R.id.button_pricechart);
        Button sharepricelist = (Button) findViewById(R.id.button_shareprice);
        Button itempage = (Button) findViewById(R.id.button_priceinfo);

        final String itemId = getIntent().getExtras().getString("ITEM_ID");

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(Commentlist.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(Commentlist.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(Commentlist.this, Member.class);
                    startActivity(intent);
                }
            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentlist.this, Recommendation.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentlist.this, MainActivity.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentlist.this, Barcode.class);
                startActivity(intent);
            }
        });
        pricechart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Commentlist.this, Pricechart.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);
            }
        });
        sharepricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Commentlist.this, SharePricelist.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);
            }
        });
        itempage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Commentlist.this, Itempage.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);
            }
        });

        final ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar1);
        progressBar.setVisibility(View.VISIBLE);

        String goodId = this.getIntent().getExtras().getString("ITEM_ID");
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
                    CustomList adapter = new
                            CustomList(Commentlist.this, comments);
                    ListView list = (ListView) findViewById(R.id.listView3);
                    if (list != null) {
                        list.setAdapter(adapter);
                    }
                }
            }
        };
        String queryCommentsSQL = "SELECT comment FROM good_comments WHERE good_id = '" + goodId + "'";
        dbManager.querySql(queryCommentsSQL);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_commentlist, menu);
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

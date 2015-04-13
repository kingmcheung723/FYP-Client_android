package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


public class Recommendation extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_recommendation);
        Button search = (Button)findViewById(R.id.button_search);
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(Recommendation.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(Recommendation.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(Recommendation.this, Member.class);
                    startActivity(intent);
                }
            }
        });
        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = TabManager.getInstance().getIntent(Recommendation.this, Search.class);
                startActivity(myintent2);

            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent3 = TabManager.getInstance().getIntent(Recommendation.this, Recommendation.class);
                startActivity(myintent3);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent4 = TabManager.getInstance().getIntent(Recommendation.this, Barcode.class);
                startActivity(myintent4);
            }
        });

        final ListView listView = (ListView)findViewById(R.id.listView2);
        if (listView != null) {
            DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    if (result != null && result.length() > 0) {
                        MyStringTokenizer token = new MyStringTokenizer(result, "|");
                        if (token != null && token.countTokens() >= 1) {
                            String[] itemNames = new String[token.countTokens()];
                            int count = 0;
                            while (token.hasMoreTokens()) {
                                String goodName = token.nextToken();
                                if (goodName != null) {
                                    itemNames[count] = goodName;
                                    count++;
                                }
                            }
                            CustomList adapter = new
                                    CustomList(Recommendation.this, itemNames);
                            listView.setAdapter(adapter);
                        } else {
                            MySharedPreference.displayDialog("No such item.", Recommendation.this);
                        }
                    }
                }
            };
            String itemSql = "SELECT name_zh FROM goods WHERE category_id = 1 ORDER BY RAND() limit 10";
            dbManager.querySql(itemSql);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recommendation, menu);
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

package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.StringTokenizer;

public class GoodList extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_goodlist);
        Button search = (Button) findViewById(R.id.button_search);
        Button recommend = (Button) findViewById(R.id.button_recommend);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent1 = TabManager.getInstance().getIntent(GoodList.this, Member.class);
                startActivity(myintent1);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = TabManager.getInstance().getIntent(GoodList.this, Search.class);
                startActivity(myintent2);

            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent3 = TabManager.getInstance().getIntent(GoodList.this, Recommendation.class);
                startActivity(myintent3);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent4 = TabManager.getInstance().getIntent(GoodList.this, Barcode.class);
                startActivity(myintent4);
            }
        });

        final String category = this.getIntent().getExtras().getString("Category");
        if (category != null) {
            final DBManager categoryIdDbManager = new DBManager();
            categoryIdDbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    if (result != null) {
                        StringTokenizer token = new StringTokenizer(result, "|");
                        if (token.hasMoreTokens()) {
                            String categoryId = token.nextToken();
                            if (categoryId != null) {
                                DBManager goodsDbManager = new DBManager();
                                goodsDbManager.queryCallBack = new QueryCallBack() {
                                    @Override
                                    public void queryResult(String result) {
                                        if (result != null) {
                                            StringTokenizer token = new StringTokenizer(result, "|");
                                            String[] goodNames = new String[token.countTokens()];
                                            int count = 0;
                                            while (token.hasMoreTokens()) {
                                                String goodName = token.nextToken();
                                                if (goodName != null) {
                                                    goodNames[count] = goodName;
                                                    count++;
                                                }
                                            }
                                            CustomList adapter = new
                                                    CustomList(GoodList.this, goodNames);
                                            ListView list = (ListView) findViewById(R.id.listView_good);
                                            list.setAdapter(adapter);
                                            list.setOnItemClickListener(new MyOnItemClickListener(goodNames));
                                        }
                                    }
                                };
                                String goodsSQL = "SELECT name_zh FROM goods WHERE goods.category_id = '" + categoryId + "'";
                                goodsDbManager.querySql(goodsSQL);
                            }
                        }
                    }
                }
            };
            String categorySQL = "SELECT id FROM categories WHERE categories.name_zh =  '" + category + "' OR categories.name_en = '" + category + "'";
            categoryIdDbManager.querySql(categorySQL);

            String[] web = {
                    "Google Plus",
                    "Twitter",
                    "Windows",
                    "Bing",
                    "Itunes",
                    "Wordpress",
                    "Drupal"
            };
            Integer[] imageId = {
                    R.drawable.tea5,
                    R.drawable.tea6,
                    R.drawable.tea7,
                    R.drawable.tea8,
                    R.drawable.tea22,
                    R.drawable.tea8,
                    R.drawable.tea22
            };
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_goodlist, menu);
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

    public class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        String[] goodNames;

        public MyOnItemClickListener(String[] goodNames) {
            this.goodNames = goodNames;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position <= goodNames.length && position >= 0) {
                String goodName = this.goodNames[position];
                if (goodName != null) {
                    Intent myintent4 = new Intent(GoodList.this, Itempage.class);
                    myintent4.putExtra("ItemName", goodName);
                    startActivity(myintent4);
                }
            }
        }
    };
}

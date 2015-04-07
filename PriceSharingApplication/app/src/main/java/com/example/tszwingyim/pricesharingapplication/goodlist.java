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

        final String[] goodNamesArray = this.getIntent().getExtras().getStringArray("GOOD_NAMES");
        if (goodNamesArray != null && goodNamesArray.length > 0) {
            CustomList adapter = new
                    CustomList(GoodList.this, goodNamesArray);
            ListView list = (ListView) findViewById(R.id.listView_good);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new MyOnItemClickListener(goodNamesArray));
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
                    DBManager dbManager = new DBManager();
                    dbManager.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null && result.length() > 0) {
                                StringTokenizer token = new StringTokenizer(result, "|");
                                if (token != null && token.countTokens() >= 1) {
                                    String itemId = token.nextToken().toString();
                                    Intent intent = new Intent(GoodList.this, Itempage.class);
                                    intent.putExtra("ITEM_ID", itemId);
                                    startActivity(intent);
                                } else {
                                    MySharedPreference.displayDialog("No such item.", GoodList.this);
                                }
                            }
                        }
                    };
                    String itemSql = "SELECT id FROM goods WHERE goods.name_zh = '" + goodName + "' OR goods.name_en = '" + goodName + "'";
                    dbManager.querySql(itemSql);
                }
            }
        }
    }

    ;
}

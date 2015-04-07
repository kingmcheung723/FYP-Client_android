package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;


public class Commentlist extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentlist.this, Member.class);
                startActivity(intent);

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
                Intent intent = TabManager.getInstance().getIntent(Commentlist.this, Pricechart.class);
                startActivity(intent);
            }
        });
        sharepricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentlist.this, SharePricelist.class);
                startActivity(intent);
            }
        });
        itempage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentlist.this, Itempage.class);
                startActivity(intent);
            }
        });

        String goodId = this.getIntent().getExtras().getString("ITEM_ID");
        DBManager dbManager = new DBManager();
        dbManager.queryCallBack = new QueryCallBack() {
            @Override
            public void queryResult(String result) {
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
//        String queryCommentsSQL = "SELECT comment, member_email, createddate FROM good_comments WHERE good_id = '" + goodId + "'";
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

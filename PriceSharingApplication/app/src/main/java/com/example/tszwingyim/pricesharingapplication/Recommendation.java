package com.example.tszwingyim.pricesharingapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;


public class Recommendation extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Spinner spinnerrecomm;
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

        spinnerrecomm = (Spinner) findViewById(R.id.spinner_recommend);
        String[] cateStr = { "Select Category","媽媽之選","生活品味","潮流之選","女性推介","甜心推介" };
        //媽媽之選:18,2,6,19,20
        //生活品味:64,66,67,36,9
        //潮流之選:29,16,17,35,37
        //女性推介:47,48,49,50,51
        //甜心推介:40,13,14,1,9


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cateStr);
        //selected item will look like a spinner set from XML
        spinnerrecomm.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerrecomm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (pos == 1) {

                    Toast.makeText(parent.getContext(),
                            "You have selected Chinese", Toast.LENGTH_SHORT)
                            .show();

                } else if (pos == 2) {

                    Toast.makeText(parent.getContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();

                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
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

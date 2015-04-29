package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import java.util.Locale;


public class Recommendation extends ActionBarActivity {

    private String[] itemNames;
    private int selectedPosition = 0;
    private ProgressBar mProgressBar;



    public void onCreate(Bundle savedInstanceState) {
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
        setContentView(R.layout.activity_recommendation);
        Button search = (Button) findViewById(R.id.button_search);
        Button recommend = (Button) findViewById(R.id.button_recommend);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button refresh = (Button) findViewById(R.id.button_refresh);



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
        refresh.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                doRecommendation(getSql(selectedPosition));
            }
        });
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        mProgressBar.setVisibility(View.VISIBLE);
        setUpDropdownList();

        doRecommendation(getSql(selectedPosition));

    }

    private String getSql(int position) {
        String itemSql = "SELECT name_zh FROM goods WHERE category_id = 2 OR category_id = 6 OR category_id = 18 OR category_id = 19 OR category_id = 20 ORDER BY RAND() limit 10";
        if (position == 0) {
            //媽媽之選:18,2,6,19,20
            itemSql = "SELECT name_zh FROM goods WHERE category_id = 2 OR category_id = 6 OR category_id = 18 OR category_id = 19 OR category_id = 20 ORDER BY RAND() limit 10";
        } else if (position == 1) {
            //生活品味:64,66,67,36,9
            itemSql = "SELECT name_zh FROM goods WHERE category_id = 9 OR category_id = 36 OR category_id = 66 OR category_id = 64 OR category_id = 67 ORDER BY RAND() limit 10";
        } else if (position == 2) {
            //潮流之選:29,16,17,35,37
            itemSql = "SELECT name_zh FROM goods WHERE category_id = 16 OR category_id = 17 OR category_id = 29 OR category_id = 35 OR category_id = 37 ORDER BY RAND() limit 10";
        } else if (position == 3) {
            //女性推介:47,48,49,50,51
            itemSql = "SELECT name_zh FROM goods WHERE category_id = 47 OR category_id = 48 OR category_id = 49 OR category_id = 50 OR category_id = 251 ORDER BY RAND() limit 10";
        } else if (position == 4) {
            //甜心推介:40,13,14,1,9
            itemSql = "SELECT name_zh FROM goods WHERE category_id = 1 OR category_id = 9 OR category_id = 13 OR category_id = 14 OR category_id = 40 ORDER BY RAND() limit 10";
        }
        return itemSql;
    }

    private void setUpDropdownList() {
        Spinner spinnerrecomm = (Spinner) findViewById(R.id.spinner_recommend);
        String[] cateStr = {"媽媽之選", "生活品味", "潮流之選", "女性推介", "甜心推介"};
        //媽媽之選:18,2,6,19,20
        //生活品味:64,66,67,36,9
        //潮流之選:29,16,17,35,37
        //女性推介:47,48,49,50,51
        //甜心推介:40,13,14,1,9
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cateStr);
        spinnerrecomm.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerrecomm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                selectedPosition = pos;
                String sql = getSql(pos);

                final ListView listView = (ListView) findViewById(R.id.listView2);
                if (listView != null) {
                    DBManager dbManager = new DBManager();
                    dbManager.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null && result.length() > 0) {
                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                if (token != null && token.countTokens() >= 1) {
                                    itemNames = new String[token.countTokens()];
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
                    dbManager.querySql(sql);
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void doRecommendation(String sql) {
        final ListView listView = (ListView) findViewById(R.id.listView2);
        if (listView != null) {
            DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    mProgressBar.setVisibility(View.GONE);
                    if (result != null && result.length() > 0) {
                        MyStringTokenizer token = new MyStringTokenizer(result, "|");
                        if (token != null && token.countTokens() >= 1) {
                            itemNames = new String[token.countTokens()];
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
            dbManager.querySql(sql);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String itemName = itemNames[position].toString();
                    if (itemName != null) {
                        DBManager dbManager = new DBManager();
                        dbManager.queryCallBack = new QueryCallBack() {
                            @Override
                            public void queryResult(String result) {
                                if (result != null && result.length() > 0) {
                                    MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                    if (token != null && token.countTokens() >= 1) {
                                        String itemId = token.nextToken().toString();
                                        Intent intent = new Intent(Recommendation.this, Itempage.class);
                                        intent.putExtra("ITEM_ID", itemId);
                                        startActivity(intent);
                                    } else {
                                        MySharedPreference.displayDialog("No such item.", Recommendation.this);
                                    }
                                }
                            }
                        };
                        String itemSql = "SELECT id FROM goods WHERE goods.name_zh = '" + itemName + "' OR goods.name_en = '" + itemName + "'";
                        dbManager.querySql(itemSql);
                    }
                }
            });
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

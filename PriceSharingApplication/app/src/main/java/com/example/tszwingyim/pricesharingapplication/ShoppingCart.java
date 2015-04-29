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
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;


public class ShoppingCart extends ActionBarActivity {

    private boolean isDeleting = false;
    private ProgressBar mProgressBar;
    private double totalPrice = 0;
    private TextView totalPriceTextView;


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
        setContentView(R.layout.activity_shopping_cart);
        Button recommend = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        final Button delete = (Button) findViewById(R.id.button_delete);
        totalPriceTextView = (TextView)findViewById(R.id.textView_sumofgoods);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(ShoppingCart.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(ShoppingCart.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(ShoppingCart.this, Member.class);
                    startActivity(intent);
                }
            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(ShoppingCart.this, Recommendation.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(ShoppingCart.this, MainActivity.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(ShoppingCart.this, Barcode.class);
                startActivity(intent);
            }
        });

//        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar1);
//        progressBar.setVisibility(View.VISIBLE);


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDeleting = !isDeleting;
                if (isDeleting) {
                    delete.setText("Select To Delete");
                } else {
                    delete.setText("Delete");
                }
            }
        });


        mProgressBar = (ProgressBar) findViewById(R.id.progressBar1);
        mProgressBar.setVisibility(View.VISIBLE);

        displayShoppintCartItems();
    }

    private void displayShoppintCartItems() {
        final String memberEmail = MySharedPreference.getMemberName(this);
        if (memberEmail != null && memberEmail.length() > 0) {
            DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    if (result != null) {
                        MyStringTokenizer token = new MyStringTokenizer(result, "|");

                        final String[] ids = new String[token.countTokens() / 2];
                        String[] goods = new String[token.countTokens() / 2];
                        int count = 0;
                        while (token.hasMoreTokens()) {
                            ids[count] = token.nextToken().toString();
                            goods[count] = token.nextToken().toString();
                            count++;
                        }
                        // Set total price
                        sumShoppingCartPrice(ids);

                        CustomList adapter = new
                                CustomList(ShoppingCart.this, goods);
                        ListView list = (ListView) findViewById(R.id.listView3);
                        if (list != null) {
                            list.setAdapter(adapter);
                        }
                        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                if (isDeleting) {
                                    String itemId = ids[position];
                                    DBManager deleteDBManager = new DBManager();
                                    deleteDBManager.queryCallBack = new QueryCallBack() {
                                        @Override
                                        public void queryResult(String result) {
                                            displayShoppintCartItems();
                                        }
                                    };
                                    String sql = "DELETE FROM shopping_carts WHERE member_email = '" + memberEmail + "' AND good_id = '" + itemId + "'";
                                    deleteDBManager.updateSql(sql);
                                } else {
                                    String itemId = ids[position].toString();
                                    Intent intent = new Intent(ShoppingCart.this, Itempage.class);
                                    intent.putExtra("ITEM_ID", itemId);
                                    startActivity(intent);
                                }
                            }
                        });
                    }
                }
            };
            String sql = "SELECT id, name_zh FROM goods WHERE goods.id IN (SELECT good_id FROM shopping_carts WHERE shopping_carts.member_email = '" + memberEmail + "')";
            dbManager.querySql(sql);
        }
    }

    private void sumShoppingCartPrice(final String[] itemIds) {
        if (itemIds != null) {
            for (int i = 0; i < itemIds.length; i++) {
                DBManager dbManager = new DBManager();
                dbManager.queryCallBack = new QueryCallBack() {
                    @Override
                    public void queryResult(String result) {
                        mProgressBar.setVisibility(View.GONE);
                        if (result != null) {
                            MyStringTokenizer token = new MyStringTokenizer(result, "|");
                            String price = token.nextToken();
                            if (price != null) {
                                totalPrice += Double.parseDouble(price);
                                if (totalPriceTextView != null) {
                                    totalPriceTextView.setText(Double.toString(totalPrice));
                                }
                            }
                        }
                    }
                };
                String goodId = itemIds[i].toString();
                String sql = "SELECT price FROM shop_goods WHERE good_id = '" + goodId + "' ORDER BY lastmoddate DESC limit 1";
                dbManager.querySql(sql);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping_cart, menu);
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

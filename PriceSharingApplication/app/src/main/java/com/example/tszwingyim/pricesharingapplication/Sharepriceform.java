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
import android.widget.EditText;

import java.util.Locale;


public class Sharepriceform extends ActionBarActivity {

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
        setContentView(R.layout.activity_sharepriceform);
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button category = (Button)findViewById(R.id.button_category);
        final Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button pricechart = (Button)findViewById(R.id.button_pricechart);
        Button sharepricelist = (Button)findViewById(R.id.button_shareprice);
        Button commentlist = (Button)findViewById(R.id.button_comment);
        Button confirm = (Button)findViewById(R.id.button_confirm);
        Button itempage = (Button)findViewById(R.id.button_priceinfo);

        final String itemId = getIntent().getExtras().getString("ITEM_ID");

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(Sharepriceform.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(Sharepriceform.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(Sharepriceform.this, Member.class);
                    startActivity(intent);
                }
            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Sharepriceform.this, Recommendation.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Sharepriceform.this, MainActivity.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Sharepriceform.this, Barcode.class);
                startActivity(intent);
            }
        });
        commentlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Sharepriceform.this, Commentlist.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);

            }
        });
        itempage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Sharepriceform.this, Itempage.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);
            }
        });
        pricechart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Sharepriceform.this, Pricechart.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);

            }
        });
        sharepricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Sharepriceform.this, SharePricelist.class);
                intent.putExtra("ITEM_ID", itemId);
                startActivity(intent);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {

            private boolean isFieldsValid() {
                boolean isValid = false;
                EditText shopName = (EditText)findViewById(R.id.editText_shopname);
                EditText shopAddress = (EditText)findViewById(R.id.editText_shopaddress);
                EditText price = (EditText)findViewById(R.id.editText_latestprice);
                if (shopName != null && shopAddress != null && price != null) {
                    if (shopName.getText().toString().length() <= 0) {
                        MySharedPreference.displayDialog("Shop name cannot leave blank.", Sharepriceform.this);
                        return isValid;
                    }
                    if (shopAddress.getText().toString().length() <= 0) {
                        MySharedPreference.displayDialog("Shop address cannot leave blank.", Sharepriceform.this);
                        return isValid;
                    }
                    if (price.getText().toString().length() <= 0) {
                        MySharedPreference.displayDialog("Price cannot leave blank.", Sharepriceform.this);
                        return isValid;
                    }
                    isValid = true;
                }
                return isValid;
            }

            public void onClick(View v) {
                EditText shopNameEditText = (EditText)findViewById(R.id.editText_shopname);
                EditText shopAddressEditText = (EditText)findViewById(R.id.editText_shopaddress);
                EditText priceEditText = (EditText)findViewById(R.id.editText_latestprice);
                if (shopNameEditText != null && shopAddressEditText != null && priceEditText != null) {
                    String shopName = shopNameEditText.getText().toString();
                    String shopAddress = shopAddressEditText.getText().toString();
                    String price = priceEditText.getText().toString();
                    if (shopName.length() <= 0) {
                        MySharedPreference.displayDialog("Shop name cannot leave blank.", Sharepriceform.this);
                        return;
                    }
                    if (shopAddress.length() <= 0) {
                        MySharedPreference.displayDialog("Shop address cannot leave blank.", Sharepriceform.this);
                        return;
                    }
                    if (price.length() <= 0) {
                        MySharedPreference.displayDialog("Price cannot leave blank.", Sharepriceform.this);
                        return;
                    }

                    String memberEmail = MySharedPreference.getMemberName(Sharepriceform.this);
                    if (memberEmail != null) {
                        DBManager dbManager = new DBManager();
                        dbManager.queryCallBack = new QueryCallBack() {
                            @Override
                            public void queryResult(String result) {
                                if (result != null && result.equalsIgnoreCase(DBManager.SUCCESS)) {
                                    Intent intent = new Intent(Sharepriceform.this, SharePricelist.class);
                                    intent.putExtra("ITEM_ID", itemId);
                                    startActivity(intent);
                                } else {
                                    MySharedPreference.displayDialog("Try again", Sharepriceform.this);
                                }
                            }
                        };
                        String sql = "INSERT INTO share_prices (member_email, good_id, shop_location, price, shop_name) VALUES ('" +
                                memberEmail + "','" + itemId + "','" + shopAddress + "','" + price + "','" + shopName + "')";
                        dbManager.updateSql(sql);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sharepriceform, menu);
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

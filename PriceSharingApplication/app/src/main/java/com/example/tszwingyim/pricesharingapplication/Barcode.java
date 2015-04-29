package com.example.tszwingyim.pricesharingapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.Locale;


public class Barcode extends ActionBarActivity {

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
        setContentView(R.layout.activity_barcode);
        Button recommendation = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button search = (Button) findViewById(R.id.button_search);
        Button member = (Button) findViewById(R.id.button_member);
        Button scan = (Button) findViewById(R.id.button_scan);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Barcode.this, Search.class);
                startActivity(intent);
            }
        });
        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Barcode.this, MainActivity.class);
                startActivity(intent);

            }
        });
        recommendation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Barcode.this, Recommendation.class);
                startActivity(intent);

            }
        });
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(Barcode.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(Barcode.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(Barcode.this, Member.class);
                    startActivity(intent);
                }
            }
        });

        // Tap scan to trigger barcode scanner
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator integrator = new IntentIntegrator(Barcode.this);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
                integrator.autoWide();
                integrator.initiateScan();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanResult != null) {
            String barcode = scanResult.getContents();

            TextView view = (TextView)this.findViewById(R.id.barcode);
            view.setText(barcode);

            DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    if (result != null && result.length() > 0) {
                        MyStringTokenizer token = new MyStringTokenizer(result, "|");
                        if (token != null && token.countTokens() >= 1) {
                            String itemId = token.nextToken().toString();
                            Intent intent = new Intent(Barcode.this, Itempage.class);
                            intent.putExtra("ITEM_ID", itemId);
                            startActivity(intent);
                        } else {
                            MySharedPreference.displayDialog("No such item.", Barcode.this);
                        }
                    }
                }
            };
            String sql = "SELECT id FROM goods WHERE barcode = '" + barcode + "'";
            dbManager.querySql(sql);
        }
        // else continue with any other code you need in the method
        super.onActivityResult(requestCode, resultCode, intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_barcode, menu);
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

package com.example.tszwingyim.pricesharingapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Memberpage extends ActionBarActivity {
    Spinner spinnerctrl;
    Button btn;
    Locale myLocale;

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
        setContentView(R.layout.activity_memberpage);

        Button recommendation = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button search = (Button) findViewById(R.id.button_search);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button logout = (Button) findViewById(R.id.button_logout);
        Button about = (Button) findViewById(R.id.button_about);
        Button setting = (Button) findViewById(R.id.button_setting);
        Button shoppingCart = (Button) findViewById(R.id.button_shoppingcart);

        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Memberpage.this, Search.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Memberpage.this, MainActivity.class);
                startActivity(intent);

            }
        });
        recommendation.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Memberpage.this, Recommendation.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Memberpage.this, Barcode.class);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(Memberpage.this);
                if (memberEmail != null) {
                    MySharedPreference.clearMemberName(Memberpage.this);
                }


                Intent intent = TabManager.getInstance().getIntent(Memberpage.this, MainActivity.class);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Memberpage.this, Setting.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Memberpage.this, About.class);
                startActivity(intent);
            }
        });

        shoppingCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Memberpage.this, ShoppingCart.class);
                startActivity(intent);
            }
        });

        String memberEmail = MySharedPreference.getMemberName(this);
        if (memberEmail != null && memberEmail.length() > 0) {
            TextView memberEmailTextView = (TextView) findViewById(R.id.textView_email1);
            memberEmailTextView.setText(memberEmail);
        }

        spinnerctrl = (Spinner) findViewById(R.id.spinner_language);
        String[] langStr = {"Select Language", "中文", "English"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, langStr);
        //selected item will look like a spinner set from XML
        spinnerctrl.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerctrl.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (pos == 1) {

                    Toast.makeText(parent.getContext(),
                            "You have selected Chinese", Toast.LENGTH_SHORT)
                            .show();
//                    MySharedPreference.setLocale("en", Memberpage.this);
                    setLocale("");
                } else if (pos == 2) {

                    Toast.makeText(parent.getContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();
//                    MySharedPreference.setLocale("en", Memberpage.this);
                    setLocale("en");
                }
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
    }

    public void setLocale(String lo) {

        MySharedPreference.setLocale(lo, this);


        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Please re-launch the app to change language")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_member, menu);
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

    @Override
    protected void onResume() {
        super.onResume();

    }
}

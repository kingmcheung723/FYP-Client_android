package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import java.util.Locale;


public class Member extends ActionBarActivity {
    Spinner spinnerctrl;
    Button btn;
    Locale myLocale;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_member);
        Button recommendation = (Button)findViewById(R.id.button_recommend);
        Button category = (Button)findViewById(R.id.button_category);
        Button search = (Button)findViewById(R.id.button_search);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button login = (Button)findViewById(R.id.button_login);
        Button register = (Button)findViewById(R.id.button_register);
        Button about = (Button)findViewById(R.id.button_about);
        Button setting = (Button)findViewById(R.id.button_setting);

        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Member.this, Search.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Member.this, MainActivity.class);
                startActivity(intent);

            }
        });
        recommendation.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Member.this, Recommendation.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Member.this, Barcode.class);
                startActivity(intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Member.this, Login.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Member.this, Register.class);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Member.this, Setting.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Member.this, About.class);
                startActivity(intent);
            }
        });
        spinnerctrl = (Spinner) findViewById(R.id.spinner_language);
        String[] langStr = { "Select Language","中文","English" };


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
                    setLocale("");
                } else if (pos == 2) {

                    Toast.makeText(parent.getContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("en");
                }

            }
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
    }

    public void setLocale(String lang) {

        myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, Member.class);
        Intent refresh1 = new Intent(this, Barcode.class);
        Intent refresh2 = new Intent(this, MainActivity.class);
        Intent refresh3 = new Intent(this, Recommendation.class);
        Intent refresh4 = new Intent(this, Search.class);
        Intent refresh5 = new Intent(this, Commentlist.class);
        Intent refresh6 = new Intent(this, Commentform.class);
        Intent refresh7 = new Intent(this, GoodList.class);
        Intent refresh8 = new Intent(this, Itempage.class);
        Intent refresh9 = new Intent(this, loadingPage.class);
        Intent refresh10 = new Intent(this, Memberpage.class);
        Intent refresh11= new Intent(this, Pricechart.class);
        Intent refresh12 = new Intent(this, Register.class);
        Intent refresh13 = new Intent(this, SearchGoodlist.class);
        Intent refresh14 = new Intent(this, SearchLocation.class);
        Intent refresh15 = new Intent(this, Setting.class);
        Intent refresh16= new Intent(this, Sharepriceform.class);
        Intent refresh17 = new Intent(this, SharePricelist.class);
        startActivity(refresh);
        startActivity(refresh1);
        startActivity(refresh2);
        startActivity(refresh3);
        startActivity(refresh4);
        startActivity(refresh5);
        startActivity(refresh6);
        startActivity(refresh7);
        startActivity(refresh8);
        startActivity(refresh9);
        startActivity(refresh10);
        startActivity(refresh11);
        startActivity(refresh12);
        startActivity(refresh13);
        startActivity(refresh14);
        startActivity(refresh15);
        startActivity(refresh16);
        startActivity(refresh17);


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
}

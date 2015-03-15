package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Memberpage extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_memberpage);
        Button recommendation = (Button)findViewById(R.id.button_recommend);
        Button category = (Button)findViewById(R.id.button_category);
        Button search = (Button)findViewById(R.id.button_search);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button logout = (Button)findViewById(R.id.button_logout);
        Button about = (Button)findViewById(R.id.button_about);
        Button setting = (Button)findViewById(R.id.button_setting);

        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent1 = new Intent(Memberpage.this,Search.class);
                startActivity(myintent1);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = new Intent(Memberpage.this,MainActivity.class);
                startActivity(myintent2);

            }
        });
        recommendation.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent3 = new Intent(Memberpage.this,Recommendation.class);
                startActivity(myintent3);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent4 = new Intent(Memberpage.this,Barcode.class);
                startActivity(myintent4);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent5 = new Intent(Memberpage.this, MainActivity.class);
                startActivity(myintent5);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent7 = new Intent(Memberpage.this, Setting.class);
                startActivity(myintent7);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent8 = new Intent(Memberpage.this, About.class);
                startActivity(myintent8);
            }
        });
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

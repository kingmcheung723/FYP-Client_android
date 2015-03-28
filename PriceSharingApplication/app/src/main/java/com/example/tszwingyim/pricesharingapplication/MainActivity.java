package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.content.Context;
import android.widget.ListView;
import android.widget.RelativeLayout;


public class MainActivity extends ActionBarActivity {
    // ListView lv1  = (ListView) findViewById(R.id.listView_category);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        Button search = (Button) findViewById(R.id.button_search);
        Button recommend = (Button) findViewById(R.id.button_recommend);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button sugarlist = (Button) findViewById(R.id.button_sugar);
        Button dairylist = (Button) findViewById(R.id.button_dairy);
        Button dailylist = (Button) findViewById(R.id.button_daily);
        Button ricelist = (Button) findViewById(R.id.button_rice);
        Button candylist = (Button) findViewById(R.id.button_candy);
        Button cleaninglist = (Button) findViewById(R.id.button_cleaning);
        Button drinklist = (Button) findViewById(R.id.button_drink);
        Button alcohollist = (Button) findViewById(R.id.button_alcohol);
        Button breadlist = (Button) findViewById(R.id.button_bread);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent1 = TabManager.getInstance().getIntent(MainActivity.this, Member.class);
                startActivity(myintent1);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = TabManager.getInstance().getIntent(MainActivity.this, Search.class);
                startActivity(myintent2);

            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent3 = TabManager.getInstance().getIntent(MainActivity.this, Recommendation.class);
                startActivity(myintent3);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent4 = TabManager.getInstance().getIntent(MainActivity.this, Barcode.class);
                startActivity(myintent4);
            }
        });
        sugarlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent5 = TabManager.getInstance().getIntent(MainActivity.this, sugarlist.class);
                startActivity(myintent5);

            }
        });
        alcohollist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent6 = TabManager.getInstance().getIntent(MainActivity.this, sugarlist.class);
                startActivity(myintent6);

            }
        });
        breadlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent7 = TabManager.getInstance().getIntent(MainActivity.this, sugarlist.class);
                startActivity(myintent7);

            }
        });
        candylist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent8 = TabManager.getInstance().getIntent(MainActivity.this, sugarlist.class);
                startActivity(myintent8);

            }
        });
        dailylist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent9 = TabManager.getInstance().getIntent(MainActivity.this, sugarlist.class);
                startActivity(myintent9);

            }
        });
        dairylist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent10 = TabManager.getInstance().getIntent(MainActivity.this, sugarlist.class);
                startActivity(myintent10);

            }
        });
        ricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent11 = TabManager.getInstance().getIntent(MainActivity.this, sugarlist.class);
                startActivity(myintent11);

            }
        });
        cleaninglist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent5 = TabManager.getInstance().getIntent(MainActivity.this, sugarlist.class);
                startActivity(myintent5);

            }
        });
        drinklist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent12 = TabManager.getInstance().getIntent(MainActivity.this, sugarlist.class);
                startActivity(myintent12);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

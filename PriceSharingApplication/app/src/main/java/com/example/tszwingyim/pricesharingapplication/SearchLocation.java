package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class SearchLocation extends ActionBarActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_search);
        Button recommendation = (Button)findViewById(R.id.button_recommend);
        Button category = (Button)findViewById(R.id.button_category);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button goods = (Button)findViewById(R.id.button_goods);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent1 = new Intent(SearchLocation.this,Member.class);
                startActivity(myintent1);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = new Intent(SearchLocation.this,MainActivity.class);
                startActivity(myintent2);

            }
        });
        recommendation.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent3 = new Intent(SearchLocation.this,Recommendation.class);
                startActivity(myintent3);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent4 = new Intent(SearchLocation.this,Barcode.class);
                startActivity(myintent4);
            }
        });
        goods.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent5 = new Intent(SearchLocation.this,Search.class);
                startActivity(myintent5);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_location, menu);
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

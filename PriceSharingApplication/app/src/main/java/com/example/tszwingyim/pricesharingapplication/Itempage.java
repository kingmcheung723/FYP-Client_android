package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Itempage extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_itempage);
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button category = (Button)findViewById(R.id.button_category);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button pricechart = (Button)findViewById(R.id.button_pricechart);
        Button sharepricelist = (Button)findViewById(R.id.button_shareprice);
        Button commentlist = (Button)findViewById(R.id.button_comment);
        Button sharepriceform = (Button)findViewById(R.id.button_goshareprice);
        Button commentform = (Button)findViewById(R.id.button_givecomment);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent1 = new Intent(Itempage.this,Member.class);
                startActivity(myintent1);

            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = new Intent(Itempage.this,Recommendation.class);
                startActivity(myintent2);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent3 = new Intent(Itempage.this,MainActivity.class);
                startActivity(myintent3);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent4 = new Intent(Itempage.this,Barcode.class);
                startActivity(myintent4);
            }
        });
        commentlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent5 = new Intent(Itempage.this,Commentlist.class);
                startActivity(myintent5);

            }
        });
        pricechart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent6 = new Intent(Itempage.this,Pricechart.class);
                startActivity(myintent6);

            }
        });
        sharepricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent7 = new Intent(Itempage.this,SharePricelist.class);
                startActivity(myintent7);
            }
        });
        sharepriceform.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent8 = new Intent(Itempage.this,Sharepriceform.class);
                startActivity(myintent8);
            }
        });
        commentform.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent9 = new Intent(Itempage.this,Commentform.class);
                startActivity(myintent9);
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_itempage, menu);
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

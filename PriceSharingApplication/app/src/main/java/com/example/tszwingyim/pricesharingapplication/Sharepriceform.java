package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Sharepriceform extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_sharepriceform);
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button category = (Button)findViewById(R.id.button_category);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button pricechart = (Button)findViewById(R.id.button_pricechart);
        Button sharepricelist = (Button)findViewById(R.id.button_shareprice);
        Button commentlist = (Button)findViewById(R.id.button_comment);
        Button confirm = (Button)findViewById(R.id.button_confirm);
        Button itempage = (Button)findViewById(R.id.button_priceinfo);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent1 = new Intent(Sharepriceform.this,Member.class);
                startActivity(myintent1);

            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = new Intent(Sharepriceform.this,Recommendation.class);
                startActivity(myintent2);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent3 = new Intent(Sharepriceform.this,MainActivity.class);
                startActivity(myintent3);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent4 = new Intent(Sharepriceform.this,Barcode.class);
                startActivity(myintent4);
            }
        });
        commentlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent5 = new Intent(Sharepriceform.this,Commentlist.class);
                startActivity(myintent5);

            }
        });
        itempage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent8 = new Intent(Sharepriceform.this,Itempage.class);
                startActivity(myintent8);
            }
        });
        pricechart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent6 = new Intent(Sharepriceform.this,Pricechart.class);
                startActivity(myintent6);

            }
        });
        sharepricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent7 = new Intent(Sharepriceform.this,SharePricelist.class);
                startActivity(myintent7);
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent8 = new Intent(Sharepriceform.this,SharePricelist.class);
                startActivity(myintent8);
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

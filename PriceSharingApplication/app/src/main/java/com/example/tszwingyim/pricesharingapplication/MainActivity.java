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
        Button search = (Button)findViewById(R.id.button_search);
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button sugarlist = (Button)findViewById(R.id.button_sugar);

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
           // Button sugar = (Button) findViewById(R.id.button_sugar);
        //    Button rice = (Button) findViewById(R.id.button_rice);
          //  Button buttonalcohol = (Button) findViewById(R.id.button_alcohol);
         //   Button buttondrink = (Button) findViewById(R.id.button_drink);
         //   Button buttondaily = (Button) findViewById(R.id.button_daily);
           // Button buttondairy = (Button) findViewById(R.id.button_dairy);
           // Button buttoncleaning = (Button) findViewById(R.id.button_cleaning);
            //Button buttonbread = (Button) findViewById(R.id.button_bread);
           // Button buttoncandy = (Button) findViewById(R.id.button_candy);
            }
               // sugar.setOnClickListener(new View.OnClickListener() {
                 //   @Override
                 //   public void onClick(View v) {
                  //      lv1.setVisibility(View.VISIBLE);

                        //RelativeLayout.LayoutParams head1Params = new RelativeLayout.LayoutParams(
                        //ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        //head1Params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                        //rice.setLayoutParams(head1Params);//

               // });
            //}





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

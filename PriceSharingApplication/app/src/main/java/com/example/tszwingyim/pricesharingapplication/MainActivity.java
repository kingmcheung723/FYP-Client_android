package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


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
        final Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        final Button sugarlist = (Button) findViewById(R.id.button_sugar);
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
                String memberEmail = MySharedPreference.getMemberName(MainActivity.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(MainActivity.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(MainActivity.this, Member.class);
                    startActivity(intent);
                }
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
                Intent intent = new Intent(MainActivity.this, Smallcategory.class);
                intent.putExtra("Category", "sugar");
                startActivity(intent);

            }
        });
        alcohollist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent6 =  new Intent(MainActivity.this, Smallcategory.class);
                Intent intent = new Intent(MainActivity.this, Smallcategory.class);
                intent.putExtra("Category", "alcohol");
                startActivity(intent);

            }
        });
        breadlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent7 =  new Intent(MainActivity.this, Smallcategory.class);
                Intent intent = new Intent(MainActivity.this, Smallcategory.class);
                intent.putExtra("Category", "bread");
                startActivity(intent);

            }
        });
        candylist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent8 =  new Intent(MainActivity.this, Smallcategory.class);
                Intent intent = new Intent(MainActivity.this, Smallcategory.class);
                intent.putExtra("Category", "candy");
                startActivity(intent);

            }
        });
        dailylist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent9 =  new Intent(MainActivity.this, Smallcategory.class);
                Intent intent = new Intent(MainActivity.this, Smallcategory.class);
                intent.putExtra("Category", "daily");
                startActivity(intent);

            }
        });
        dairylist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent10 =  new Intent(MainActivity.this, Smallcategory.class);
                Intent intent = new Intent(MainActivity.this, Smallcategory.class);
                intent.putExtra("Category", "dairy");
                startActivity(intent);

            }
        });
        ricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent11 =  new Intent(MainActivity.this, Smallcategory.class);
                Intent intent = new Intent(MainActivity.this, Smallcategory.class);
                intent.putExtra("Category", "rice");
                startActivity(intent);

            }
        });
        cleaninglist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent13 =  new Intent(MainActivity.this, Smallcategory.class);
                Intent intent = new Intent(MainActivity.this, Smallcategory.class);
                intent.putExtra("Category", "cleaning");
                startActivity(intent);

            }
        });
        drinklist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent12 =  new Intent(MainActivity.this, Smallcategory.class);
                Intent intent = new Intent(MainActivity.this, Smallcategory.class);
                intent.putExtra("Category", "drink");
                startActivity(intent);

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

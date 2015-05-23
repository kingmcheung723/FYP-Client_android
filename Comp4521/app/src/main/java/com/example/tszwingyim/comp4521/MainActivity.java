package com.example.tszwingyim.comp4521;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_main);
        Button others = (Button) findViewById(R.id.button_others);
        Button sports = (Button) findViewById(R.id.button_sports);
        Button canteens = (Button) findViewById(R.id.button_canteens);
        others.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(MainActivity.this, Everylistview.class);
                intent.putExtra("category", "others");
;                startActivity(intent);
            }
        });
        canteens.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(MainActivity.this, Everylistview.class);
                intent.putExtra("category", "canteens");
                startActivity(intent);
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(MainActivity.this, Everylistview.class);
                intent.putExtra("category", "sports");
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

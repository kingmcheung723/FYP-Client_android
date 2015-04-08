package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;



public class loadingPage extends ActionBarActivity {

    private final int WAIT_TIME = 2500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_loading_page);
        findViewById(R.id.mainSpinner1).setVisibility(View.VISIBLE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Simulating a long running task
                System.out.println("Going to Profile Data");
	            /* Create an Intent that will start the MainActivity. */
                Intent mainIntent = new Intent(loadingPage.this, MainActivity.class);
                loadingPage.this.startActivity(mainIntent);
                loadingPage.this.finish();
            }
        }, WAIT_TIME);
    }
}

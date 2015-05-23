package com.example.tszwingyim.comp4521;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class Comment extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        ListView commentList = (ListView) findViewById(R.id.listView);
        if (commentList != null) {
            DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    if (result != null) {

                    }
                }
            };
            String sql = "SELECT * from Comments WHERE FacilitiesID = 1 AND ";
            dbManager.querySql(sql);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comment, menu);
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

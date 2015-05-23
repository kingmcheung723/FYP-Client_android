package com.example.tszwingyim.comp4521;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.StringTokenizer;


public class Everylistview extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_everylistview);

        String category = getIntent().getExtras().getString("category");
        if (category != null) {
            if (category.equalsIgnoreCase("others")) {
                setTitle("Others");
                displayList("Others");
            } else if (category.equalsIgnoreCase("dinning")) {
                setTitle("Dinning");
                displayList("Dinning");
            } else if (category.equalsIgnoreCase(("sports"))) {
                setTitle("Sports");
                displayList("SPORTS");
            }
        }
    }

    private void setTitle(String title) {
        TextView textview = (TextView) findViewById(R.id.Category_title);
        if (textview != null) {
            textview.setText(title + " Facilities");
        }
    }

    private void displayList(String category) {
        final ListView listView = (ListView) findViewById(R.id.listView2);
        if (listView != null) {
            DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    if (result != null) {
                        MyStringTokenizer token = new MyStringTokenizer(result, "|");
                        String[] names = new String[token.countTokens()];
                        int count = 0;
                        while (token.hasMoreTokens()) {
                            names[count] = token.nextToken();
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Everylistview.this, android.R.layout.simple_list_item_1, names);
                        listView.setAdapter(adapter);
                    }
                }
            };
            String sql = "SELECT NAME FROM Facilities WHERE CATEGORIES = '" + category + "'";
            dbManager.querySql(sql);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_everylistview, menu);
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

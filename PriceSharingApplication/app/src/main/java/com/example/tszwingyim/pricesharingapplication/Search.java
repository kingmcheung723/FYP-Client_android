package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;


public class Search extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_search);
        Button recommendation = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button location = (Button) findViewById(R.id.button_location);
        Button Searchgoodlist = (Button) findViewById(R.id.button_itempage);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Search.this, Member.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Search.this, MainActivity.class);
                startActivity(intent);

            }
        });
        recommendation.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Search.this, Recommendation.class);
                startActivity(intent);
            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Search.this, Barcode.class);
                startActivity(intent);

            }
        });
        location.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Search.this, SearchLocation.class);
                startActivity(intent);

            }
        });
        Searchgoodlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.autocomplete_searchitem);
                String itemName = textView.getText().toString();
                if (itemName != null && itemName.length() > 0) {
                    Intent intent = new Intent(Search.this, Itempage.class);
                    intent.putExtra("ItemName", itemName);
                    startActivity(intent);
                } else {

                    MySharedPreference.displayDialog("Incorrect Itemname", Search.this);
                }
            }
        });

        Spinner goods = (Spinner) findViewById(R.id.spinner_search);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.searchgoods_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        goods.setAdapter(adapter);

      //  Spinner choosecategory = (Spinner) findViewById(R.id.spinner_category);
        // Create an ArrayAdapter using the string array and a default spinner layout
        //ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
             //   R.array.searchbread_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
       // adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
       // choosecategory.setAdapter(adapter1);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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

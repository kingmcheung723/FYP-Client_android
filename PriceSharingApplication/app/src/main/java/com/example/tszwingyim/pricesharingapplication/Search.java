package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;


public class Search extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Locale locale = new Locale(MySharedPreference.getLocale(this));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());


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
                String memberEmail = MySharedPreference.getMemberName(Search.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(Search.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(Search.this, Member.class);
                    startActivity(intent);
                }
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
                AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocomplete_searchitem);
                if (textView != null) {
                    String searchText = textView.getText().toString();
                    if (searchText != null && searchText.length() > 0) {
                        Spinner searchingCategorySpinner = (Spinner) findViewById(R.id.spinner_search);
                        if (searchingCategorySpinner != null) {
                            String selectedSearchCategory = searchingCategorySpinner.getSelectedItem().toString();
                            int selectedSearchCategoryPosition = searchingCategorySpinner.getSelectedItemPosition();
                            if (selectedSearchCategory != null && selectedSearchCategory.length() > 0 &&
                                    selectedSearchCategoryPosition >= 0 && selectedSearchCategoryPosition < searchingCategorySpinner.getCount())
                                if (selectedSearchCategoryPosition == 0) {
                                    String itemName = searchText;
                                    DBManager dbManager = new DBManager();
                                    dbManager.queryCallBack = new QueryCallBack() {
                                        @Override
                                        public void queryResult(String result) {
                                            if (result != null && result.length() > 0) {
                                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                                String[] goodNames = new String[token.countTokens()];
                                                int count = 0;
                                                while (token.hasMoreTokens()) {
                                                    String goodName = token.nextToken();
                                                    if (goodName != null) {
                                                        goodNames[count] = goodName;
                                                        count++;
                                                    }
                                                }
                                                Intent intent = new Intent(Search.this, GoodList.class);
                                                intent.putExtra("GOOD_NAMES", goodNames);
                                                startActivity(intent);
                                            } else {
                                                MySharedPreference.displayDialog("No such items.", Search.this);
                                            }
                                        }
                                    };
                                    String itemSql = "SELECT name_zh FROM goods WHERE goods.name_zh like '%" + itemName + "%' OR goods.name_en like '%" + itemName + "%'";
                                    dbManager.querySql(itemSql);
                                } else {
                                    DBManager dbManager = new DBManager();
                                    dbManager.queryCallBack = new QueryCallBack() {
                                        @Override
                                        public void queryResult(String result) {
                                            if (result != null) {
                                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                                String[] goodNames = new String[token.countTokens()];
                                                int count = 0;
                                                while (token.hasMoreTokens()) {
                                                    String goodName = token.nextToken();
                                                    if (goodName != null) {
                                                        goodNames[count] = goodName;
                                                        count++;
                                                    }
                                                }
                                                Intent intent = new Intent(Search.this, GoodList.class);
                                                intent.putExtra("GOOD_NAMES", goodNames);
                                                startActivity(intent);
                                            }
                                        }
                                    };
                                    String sql = "SELECT name_zh FROM goods WHERE goods.brand_id IN " +
                                            "(SELECT id FROM brands WHERE brands.name_zh like '%" + searchText + "%' OR brands.name_en like '%" + searchText + "%')";

                                    dbManager.querySql(sql);
                                }
                        }
                    } else {
                        MySharedPreference.displayDialog("Please enter the name you want to search.", Search.this);
                    }
                }
            }
        });

        Spinner searchingCategorySpinner = (Spinner) findViewById(R.id.spinner_search);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.searchgoods_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        searchingCategorySpinner.setAdapter(adapter);
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

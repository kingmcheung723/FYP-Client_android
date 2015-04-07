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
import android.widget.Spinner;

import java.util.StringTokenizer;


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
        Button itemPage = (Button) findViewById(R.id.button_itempage);

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
        itemPage.setOnClickListener(new View.OnClickListener() {

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
                                                StringTokenizer token = new StringTokenizer(result, "|");
                                                if (token != null && token.countTokens() >= 1) {
                                                    String itemId = token.nextToken().toString();
                                                    Intent intent = new Intent(Search.this, Itempage.class);
                                                    intent.putExtra("ITEM_ID", itemId);
                                                    startActivity(intent);
                                                }
                                            } else {
                                                MySharedPreference.displayDialog("No such item.", Search.this);
                                            }
                                        }
                                    };
                                    String itemSql = "SELECT id FROM goods WHERE goods.name_zh = '" + itemName + "' OR goods.name_en = '" + itemName + "'";
                                    dbManager.querySql(itemSql);
                                } else {
                                    DBManager dbManager = new DBManager();
                                    dbManager.queryCallBack = new QueryCallBack() {
                                        @Override
                                        public void queryResult(String result) {
                                            if (result != null) {
                                                StringTokenizer token = new StringTokenizer(result, "|");
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
                                    String sql = null;
                                    switch (selectedSearchCategoryPosition) {
                                        case 1:
                                            // Search by good category
                                            sql = "SELECT name_zh FROM goods WHERE goods.category_id IN " +
                                                    "(SELECT id FROM categories WHERE categories.name_zh like '%" + searchText + "%' OR categories.name_en like '%" + searchText + "%')";
                                            break;
                                        case 2:
                                            // Search by good brand
                                            sql = "SELECT name_zh FROM goods WHERE goods.brand_id IN " +
                                                    "(SELECT id FROM brands WHERE brands.name_zh like '%" + searchText + "%' OR brands.name_en like '%" + searchText + "%')";
                                            break;
                                        case 3:
                                            // Search by shop name
                                            sql = "SELECT name_zh FROM goods WHERE goods.id IN (SELECT good_id FROM shop_goods WHERE shop_goods.shop_id = (SELECT id FROM shops WHERE shops.name_en like '%" + searchText + "%' OR shops.name_zh like '% + searchText + %'))";
                                            break;
                                    }
                                    if (sql != null) {
                                        dbManager.querySql(sql);
                                    }
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

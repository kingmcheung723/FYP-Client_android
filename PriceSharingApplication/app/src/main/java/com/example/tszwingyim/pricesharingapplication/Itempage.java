package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;
import java.util.StringTokenizer;


public class Itempage extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_itempage);
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button category = (Button)findViewById(R.id.button_category);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button pricechart = (Button)findViewById(R.id.button_pricechart);
        Button sharepricelist = (Button)findViewById(R.id.button_shareprice);
        Button commentlist = (Button)findViewById(R.id.button_comment);
        Button sharepriceform = (Button)findViewById(R.id.button_goshareprice);
        Button commentform = (Button)findViewById(R.id.button_givecomment);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Member.class);
                startActivity(intent);

            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Recommendation.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, MainActivity.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Barcode.class);
                startActivity(intent);
            }
        });
        commentlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Commentlist.class);
                startActivity(intent);

            }
        });
        pricechart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Pricechart.class);
                startActivity(intent);
            }
        });
        sharepricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, SharePricelist.class);
                startActivity(intent);
            }
        });
        sharepriceform.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Sharepriceform.class);
                startActivity(intent);
            }
        });
        commentform.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Commentform.class);
                startActivity(intent);
            }
        });

        String itemName = getIntent().getExtras().getString("ItemName");
        if (itemName != null && itemName.length() > 0) {
            final DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    Locale locale = getResources().getConfiguration().locale;
                    StringTokenizer token = new StringTokenizer(result, "|");

                    String itemId = token.nextToken().toString();

                    // Item name
                    String itemNameEn = token.nextToken().toString();
                    String itemNameZh = token.nextToken().toString();
                    TextView textView = (TextView)findViewById(R.id.editText_name);
                    textView.setText(itemNameZh);

                    // Brand name
                    String brandId = token.nextToken().toString();
                    DBManager dbManager1 = new DBManager();
                    dbManager1.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            StringTokenizer token = new StringTokenizer(result, "|");
                            String brandNameEn = token.nextToken().toString();
                            String brandNameZh = token.nextToken().toString();
                            TextView textView1 = (TextView)findViewById(R.id.editText_brand);
                            textView1.setText(brandNameZh);
                        }
                    };
                    String brandSql = "SELECT brands.name_en, brands.name_zh FROM brands WHERE brands.id = " + brandId;
                    dbManager1.querySql(brandSql);

                    // Category name
                    String categoryId = token.nextToken().toString();
                    DBManager dbManager2 = new DBManager();
                    dbManager2.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            StringTokenizer token = new StringTokenizer(result, "|");
                            String categoryNameEn = token.nextToken().toString();
                            String categoryNameZh = token.nextToken().toString();
                            TextView textView2 = (TextView)findViewById(R.id.editText_category);
                            textView2.setText(categoryNameZh);
                        }
                    };
                    String categorySql = "SELECT categories.name_en, categories.name_zh FROM categories WHERE categories.id = " + categoryId;
                    dbManager2.querySql(categorySql);

                    DBManager dbManager3 = new DBManager();
                    dbManager3.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null  && result.length() > 0) {
                                StringTokenizer token = new StringTokenizer(result, "|");
                                String price = token.nextToken().toString();
                                String discountPriceEn = token.nextToken().toString();
                                String discountPriceZH = token.nextToken().toString();
                                String priceString = price;
                                if (!discountPriceZH.equalsIgnoreCase("null")) {
                                    priceString += " " + discountPriceZH;
                                }

                                TextView textView4 = (TextView)findViewById(R.id.editText_wellcome);
                                textView4.setText(priceString);
                            }
                        }
                    };
                    String wellcomeSql = "SELECT shop_goods.price,  shop_goods.discount_details_en, shop_goods.discount_details_zh FROM shop_goods WHERE shop_goods.shop_id = 1 AND shop_goods.good_id = " + itemId;
                    dbManager3.querySql(wellcomeSql);

                    DBManager dbManager4 = new DBManager();
                    dbManager4.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null  && result.length() > 0) {
                                StringTokenizer token = new StringTokenizer(result, "|");
                                String price = token.nextToken().toString();
                                String discountPriceEn = token.nextToken().toString();
                                String discountPriceZH = token.nextToken().toString();
                                String priceString = price;
                                if (!discountPriceZH.equalsIgnoreCase("null")) {
                                    priceString += " " + discountPriceZH;
                                }
                                TextView textView3 = (TextView)findViewById(R.id.editText_parknshop);
                                textView3.setText(priceString);
                            }
                        }
                    };
                    String parknShopSql = "SELECT shop_goods.price,  shop_goods.discount_details_en, shop_goods.discount_details_zh FROM shop_goods WHERE shop_goods.shop_id = 2 AND  shop_goods.good_id = " + itemId;
                    dbManager4.querySql(parknShopSql);

                    DBManager dbManager5 = new DBManager();
                    dbManager5.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null  && result.length() > 0) {
                                StringTokenizer token = new StringTokenizer(result, "|");
                                String price = token.nextToken().toString();
                                String discountPriceEn = token.nextToken().toString();
                                String discountPriceZH = token.nextToken().toString();
                                String priceString = price;
                                if (!discountPriceZH.equalsIgnoreCase("null")) {
                                    priceString += " " + discountPriceZH;
                                }
                                TextView textView7 = (TextView)findViewById(R.id.editText_marketplace);
                                textView7.setText(priceString);
                            }
                        }
                    };
                    String marketPlaceSql = "SELECT shop_goods.price,  shop_goods.discount_details_en, shop_goods.discount_details_zh FROM shop_goods WHERE shop_goods.shop_id = 3 AND  shop_goods.good_id = " + itemId;
                    dbManager5.querySql(marketPlaceSql);

                    DBManager dbManager6 = new DBManager();
                    dbManager6.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null  && result.length() > 0) {
                                StringTokenizer token = new StringTokenizer(result, "|");
                                String price = token.nextToken().toString();
                                String discountPriceEn = token.nextToken().toString();
                                String discountPriceZH = token.nextToken().toString();
                                String priceString = price;
                                if (!discountPriceZH.equalsIgnoreCase("null")) {
                                    priceString += " " + discountPriceZH;
                                }
                                TextView textView5 = (TextView)findViewById(R.id.editText_aeon);
                                textView5.setText(priceString);
                            }
                        }
                    };
                    String aeonSql = "SELECT shop_goods.price,  shop_goods.discount_details_en, shop_goods.discount_details_zh FROM shop_goods WHERE shop_goods.shop_id = 4 AND  shop_goods.good_id = " + itemId;
                    dbManager6.querySql(aeonSql);

                    DBManager dbManager7 = new DBManager();
                    dbManager7.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null  && result.length() > 0) {
                                StringTokenizer token = new StringTokenizer(result, "|");
                                String price = token.nextToken().toString();
                                String discountPriceEn = token.nextToken().toString();
                                String discountPriceZH = token.nextToken().toString();
                                String priceString = price;
                                if (!discountPriceZH.equalsIgnoreCase("null")) {
                                    priceString += " " + discountPriceZH;
                                }
                                TextView textView6 = (TextView)findViewById(R.id.editText_daicheong);
                                textView6.setText(priceString);
                            }
                        }
                    };
                    String dhcSql = "SELECT shop_goods.price,  shop_goods.discount_details_en, shop_goods.discount_details_zh FROM shop_goods WHERE shop_goods.shop_id = 5 AND  shop_goods.good_id = " + itemId;
                    dbManager7.querySql(dhcSql);

                    DBManager dbManager8 = new DBManager();
                    dbManager8.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null  && result.length() > 0) {
                                StringTokenizer token = new StringTokenizer(result, "|");
                                String likes = token.nextToken().toString();
                                TextView textView6 = (TextView)findViewById(R.id.textView_numoflike);
                                textView6.setText(likes);
                            }
                        }
                    };
                    String likeSql = "SELECT COUNT(*) FROM likes WHERE likes.good_id = '" + itemId + "'";
                    dbManager8.querySql(likeSql);
                }
            };
            String itemSql = "SELECT id, name_en, name_zh, brand_id, category_id FROM goods WHERE goods.name_zh = '" + itemName + "' OR goods.name_en = '" + itemName + "'";
            dbManager.querySql(itemSql);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_itempage, menu);
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

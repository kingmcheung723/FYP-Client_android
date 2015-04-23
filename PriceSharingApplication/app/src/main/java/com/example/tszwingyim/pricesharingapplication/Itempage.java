package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Itempage extends ActionBarActivity {
    CallbackManager callbackManager;

    //    ShareDialog shareDialog;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_itempage);
        Button recommend = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button pricechart = (Button) findViewById(R.id.button_pricechart);
        Button sharepricelist = (Button) findViewById(R.id.button_shareprice);
        Button commentlist = (Button) findViewById(R.id.button_comment);
        Button sharepriceform = (Button) findViewById(R.id.button_goshareprice);
        Button commentform = (Button) findViewById(R.id.button_givecomment);
        Button saveToShoppingCart = (Button) this.findViewById(R.id.button_save);
        final ShareButton shareButton = (ShareButton) findViewById(R.id.fb_share_button);



        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(Itempage.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(Itempage.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(Itempage.this, Member.class);
                    startActivity(intent);
                }
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

        final String itemId = getIntent().getExtras().getString("ITEM_ID");
        if (itemId != null) {

            commentlist.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Intent intent = new Intent(Itempage.this, Commentlist.class);
                    intent.putExtra("ITEM_ID", itemId);
                    startActivity(intent);
                }
            });
            pricechart.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final List<Float> WelcomeGoodPrices = new ArrayList<Float>();
                    final List<Float> ParknShopGoodPrices = new ArrayList<Float>();
                    final List<Float> AeonGoodPrice = new ArrayList<Float>();
                    final List<Float> DchGoodPrices = new ArrayList<Float>();
                    final List<Float> MarketPlaceGoodPrices = new ArrayList<Float>();

                    DBManager welcomeDBManager = new DBManager();
                    welcomeDBManager.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null) {
                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                if (token != null && token.countTokens() >= 1) {
                                    String price = null;
                                    while (token.hasMoreTokens()) {
                                        price = token.nextToken();
                                        if (price != null) {
                                            Float priceNumber = Float.parseFloat(price);
                                            WelcomeGoodPrices.add(priceNumber);
                                        }
                                    }
                                }

                                // ParknShop
                                DBManager parkNShopDBManager = new DBManager();
                                parkNShopDBManager.queryCallBack = new QueryCallBack() {
                                    @Override
                                    public void queryResult(String result) {
                                        MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                        if (token != null && token.countTokens() >= 1) {
                                            String price = null;
                                            while (token.hasMoreTokens()) {
                                                price = token.nextToken();
                                                if (price != null) {
                                                    Float priceNumber = Float.parseFloat(price);
                                                    ParknShopGoodPrices.add(priceNumber);
                                                }
                                            }
                                        }
                                        // Market Place
                                        DBManager marketPlaceDBManager = new DBManager();
                                        marketPlaceDBManager.queryCallBack = new QueryCallBack() {
                                            @Override
                                            public void queryResult(String result) {
                                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                                if (token != null && token.countTokens() >= 1) {
                                                    String price = null;
                                                    while (token.hasMoreTokens()) {
                                                        price = token.nextToken();
                                                        if (price != null) {
                                                            Float priceNumber = Float.parseFloat(price);
                                                            MarketPlaceGoodPrices.add(priceNumber);
                                                        }
                                                    }
                                                }

                                                //  Aeon
                                                DBManager aeonDBManager = new DBManager();
                                                aeonDBManager.queryCallBack = new QueryCallBack() {
                                                    @Override
                                                    public void queryResult(String result) {
                                                        MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                                        if (token != null && token.countTokens() >= 1) {
                                                            String price = null;
                                                            while (token.hasMoreTokens()) {
                                                                price = token.nextToken();
                                                                if (price != null) {
                                                                    Float priceNumber = Float.parseFloat(price);
                                                                    AeonGoodPrice.add(priceNumber);
                                                                }
                                                            }
                                                        }
                                                        //  DCH
                                                        DBManager dchDBManager = new DBManager();
                                                        dchDBManager.queryCallBack = new QueryCallBack() {
                                                            @Override
                                                            public void queryResult(String result) {
                                                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                                                if (token != null && token.countTokens() >= 1) {
                                                                    String price = null;
                                                                    while (token.hasMoreTokens()) {
                                                                        price = token.nextToken();
                                                                        if (price != null) {
                                                                            Float priceNumber = Float.parseFloat(price);
                                                                            DchGoodPrices.add(priceNumber);
                                                                        }
                                                                    }
                                                                }

                                                                float[] welcomePrices = toFloatArray(WelcomeGoodPrices);
                                                                float[] parkNShopPrices = toFloatArray(ParknShopGoodPrices);
                                                                float[] aeonPrices = toFloatArray(AeonGoodPrice);
                                                                float[] dchPrices = toFloatArray(DchGoodPrices);
                                                                float[] marketPlasePrices = toFloatArray(MarketPlaceGoodPrices);
                                                                Intent intent = new Intent(Itempage.this, Pricechart.class);
                                                                intent.putExtra("ITEM_ID", itemId);
                                                                intent.putExtra("welcomePrices", welcomePrices);
                                                                intent.putExtra("parkNShopPrices", parkNShopPrices);
                                                                intent.putExtra("aeonPrices", aeonPrices);
                                                                intent.putExtra("dchPrices", dchPrices);
                                                                intent.putExtra("marketPlasePrices", marketPlasePrices);
                                                                startActivity(intent);
                                                            }
                                                        };
                                                        String dchSQL = "SELECT price FROM `shop_goods` WHERE shop_id = '5' AND good_id = '" + itemId + "' AND lastmoddate > '2015-4-12' ORDER BY `lastmoddate` ASC  ";
                                                        dchDBManager.querySql(dchSQL);
                                                    }
                                                };
                                                String aeonSQL = "SELECT price FROM `shop_goods` WHERE shop_id = '4' AND good_id = '" + itemId + "' AND lastmoddate > '2015-4-12' ORDER BY `lastmoddate` ASC  ";
                                                aeonDBManager.querySql(aeonSQL);
                                            }
                                        };
                                        String marketPlaceSQL = "SELECT price FROM `shop_goods` WHERE shop_id = '3' AND good_id = '" + itemId + "' AND lastmoddate > '2015-4-12' ORDER BY `lastmoddate` ASC  ";
                                        marketPlaceDBManager.querySql(marketPlaceSQL);
                                    }
                                };
                                String parkNShopSQL = "SELECT price FROM `shop_goods` WHERE shop_id = '2' AND good_id = '" + itemId + "' AND lastmoddate > '2015-4-12' ORDER BY `lastmoddate` ASC  ";
                                parkNShopDBManager.querySql(parkNShopSQL);
                            }
                        }
                    };
                    String welcomeSQL = "SELECT price FROM `shop_goods` WHERE shop_id = '1' AND good_id = '" + itemId + "' AND lastmoddate > '2015-4-12' ORDER BY `lastmoddate` ASC  ";
                    welcomeDBManager.querySql(welcomeSQL);
                }
            });
            sharepricelist.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Itempage.this, SharePricelist.class);
                    intent.putExtra("ITEM_ID", itemId);
                    startActivity(intent);
                }
            });
            sharepriceform.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(Itempage.this, Sharepriceform.class);
                    intent.putExtra("ITEM_ID", itemId);
                    startActivity(intent);
                }
            });
            commentform.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if (itemId != null && itemId.length() > 0) {
                        Intent intent = new Intent(Itempage.this, Commentform.class);
                        intent.putExtra("ITEM_ID", itemId);
                        startActivity(intent);
                    }
                }
            });
            saveToShoppingCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemId != null && itemId.length() > 0) {
                        String memberEmail = MySharedPreference.getMemberName(Itempage.this);
                        if (memberEmail != null && memberEmail.length() > 0) {
                            DBManager dbManager = new DBManager();
                            dbManager.queryCallBack = new QueryCallBack() {
                                @Override
                                public void queryResult(String result) {
                                    if (result.equalsIgnoreCase(DBManager.SUCCESS)) {
                                        MySharedPreference.displayDialog("Added to shopping cart", Itempage.this);
                                    }
                                }
                            };
                            String insertCommentSQL = "INSERT INTO shopping_carts (good_id, member_email) VALUES ('" +
                                    itemId + "','" + memberEmail + "')";
                            dbManager.insertSql(insertCommentSQL);
                        } else {
                            MySharedPreference.displayDialog("You have not yet registered", Itempage.this);
                        }
                    }
                }
            });

            if (itemId != null && itemId.length() > 0) {
                final DBManager dbManager = new DBManager();
                dbManager.queryCallBack = new QueryCallBack() {
                    @Override
                    public void queryResult(String result) {
                        Locale locale = getResources().getConfiguration().locale;
                        MyStringTokenizer token = new MyStringTokenizer(result, "|");

                        // Item name
                        String itemNameEn = token.nextToken().toString();
                        String itemNameZh = token.nextToken().toString();
                        TextView textView = (TextView) findViewById(R.id.editText_name);
                        textView.setText(itemNameZh);

                        // Share
                        ShareLinkContent content = new ShareLinkContent.Builder()
                                .setContentTitle("Cheapest!!")
                                .setContentDescription(
                                        "I found a cheap good " + itemNameEn + " through Price Sharing Application")

                                .build();
                        shareButton.setShareContent(content);
                        callbackManager = CallbackManager.Factory.create();


                        // Brand name
                        String brandId = token.nextToken().toString();
                        DBManager dbManager1 = new DBManager();
                        dbManager1.queryCallBack = new QueryCallBack() {
                            @Override
                            public void queryResult(String result) {
                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                String brandNameEn = token.nextToken().toString();
                                String brandNameZh = token.nextToken().toString();
                                TextView textView1 = (TextView) findViewById(R.id.editText_brand);
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
                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                String categoryNameEn = token.nextToken().toString();
                                String categoryNameZh = token.nextToken().toString();
                                TextView textView2 = (TextView) findViewById(R.id.editText_category);
                                textView2.setText(categoryNameZh);
                            }
                        };
                        String categorySql = "SELECT categories.name_en, categories.name_zh FROM categories WHERE categories.id = " + categoryId;
                        dbManager2.querySql(categorySql);

                        DBManager dbManager3 = new DBManager();
                        dbManager3.queryCallBack = new QueryCallBack() {
                            @Override
                            public void queryResult(String result) {
                                if (result != null && result.length() > 0) {
                                    MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                    String price = token.nextToken().toString();
                                    String discountPriceEn = token.nextToken().toString();
                                    String discountPriceZH = token.nextToken().toString();
                                    String priceString = price;
                                    if (!discountPriceZH.equalsIgnoreCase("null")) {
                                        priceString += " " + discountPriceZH;
                                    }

                                    TextView textView4 = (TextView) findViewById(R.id.editText_wellcome);
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
                                if (result != null && result.length() > 0) {
                                    MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                    String price = token.nextToken().toString();
                                    String discountPriceEn = token.nextToken().toString();
                                    String discountPriceZH = token.nextToken().toString();
                                    String priceString = price;
                                    if (!discountPriceZH.equalsIgnoreCase("null")) {
                                        priceString += " " + discountPriceZH;
                                    }
                                    TextView textView3 = (TextView) findViewById(R.id.editText_parknshop);
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
                                if (result != null && result.length() > 0) {
                                    MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                    String price = token.nextToken().toString();
                                    String discountPriceEn = token.nextToken().toString();
                                    String discountPriceZH = token.nextToken().toString();
                                    String priceString = price;
                                    if (!discountPriceZH.equalsIgnoreCase("null")) {
                                        priceString += " " + discountPriceZH;
                                    }
                                    TextView textView7 = (TextView) findViewById(R.id.editText_marketplace);
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
                                if (result != null && result.length() > 0) {
                                    MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                    String price = token.nextToken().toString();
                                    String discountPriceEn = token.nextToken().toString();
                                    String discountPriceZH = token.nextToken().toString();
                                    String priceString = price;
                                    if (!discountPriceZH.equalsIgnoreCase("null")) {
                                        priceString += " " + discountPriceZH;
                                    }
                                    TextView textView5 = (TextView) findViewById(R.id.editText_aeon);
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
                                if (result != null && result.length() > 0) {
                                    MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                    String price = token.nextToken().toString();
                                    String discountPriceEn = token.nextToken().toString();
                                    String discountPriceZH = token.nextToken().toString();
                                    String priceString = price;
                                    if (!discountPriceZH.equalsIgnoreCase("null")) {
                                        priceString += " " + discountPriceZH;
                                    }
                                    TextView textView6 = (TextView) findViewById(R.id.editText_daicheong);
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
                                if (result != null && result.length() > 0) {
                                    MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                    String likes = token.nextToken().toString();
                                    TextView textView6 = (TextView) findViewById(R.id.textView_numoflike);
                                    textView6.setText(likes);
                                }
                            }
                        };
                        String likeSql = "SELECT COUNT(*) FROM likes WHERE likes.good_id = '" + itemId + "'";
                        dbManager8.querySql(likeSql);

                        // ImageID


                        DBManager dbManager9 = new DBManager();

                        dbManager9.queryCallBack = new QueryCallBack() {
                            @Override
                            public void queryResult(String result) {
                                if (result != null && result.length() > 0) {
                                    MyStringTokenizer token = new MyStringTokenizer(result, "|");

                                    if (token != null && token.countTokens() >= 1) {
                                        String imageId = token.nextToken().toString();
//                                        if(imageId != null){
                                    String uri = "@drawable/"+imageId;
                                    int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                                            if(imageResource!=0){
                                    Drawable drawable = getResources().getDrawable(imageResource);
                                    ImageView imageView1 = (ImageView) findViewById(R.id.imageView_like);
                                    imageView1.setImageDrawable(drawable);}

                                else {ImageView imageView1 = (ImageView) findViewById(R.id.imageView_like);
                                            Drawable drawable = getResources().getDrawable(R.drawable.ic_launcher);
                                            imageView1.setImageDrawable(drawable);}}}

                                }};
                        String imageSql = "SELECT image_link FROM goods WHERE id = " + itemId;
                        dbManager9.querySql(imageSql);


                        View.OnClickListener onLikeClickListener = new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final String memberName = MySharedPreference.getMemberName(Itempage.this);
                                if (memberName != null) {
                                    final QueryCallBack queryTotalLikesCallBack = new QueryCallBack() {
                                        @Override
                                        public void queryResult(String result) {
                                            if (result != null && result.length() > 0) {
                                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                                String likes = token.nextToken().toString();
                                                TextView textView6 = (TextView) findViewById(R.id.textView_numoflike);
                                                textView6.setText(likes);
                                            }
                                        }
                                    };

                                    final QueryCallBack queryInsertLikeCallBack = new QueryCallBack() {
                                        @Override
                                        public void queryResult(String result) {
                                            if (result != null) {
                                                DBManager queryTotalLikes = new DBManager();
                                                queryTotalLikes.queryCallBack = queryTotalLikesCallBack;
                                                String likeSql = "SELECT COUNT(*) FROM likes WHERE likes.good_id = '" + itemId + "'";
                                                queryTotalLikes.querySql(likeSql);
                                            }
                                        }
                                    };
                                    QueryCallBack queryIsLikedCallBack = new QueryCallBack() {
                                        @Override
                                        public void queryResult(String result) {
                                            if (result == null || result.equalsIgnoreCase("")) {
                                                DBManager queryInsertLike = new DBManager();
                                                queryInsertLike.queryCallBack = queryInsertLikeCallBack;
                                                String insertLikeSQL = "INSERT INTO likes (member_email, good_id, likedislike) VALUES ( '" + memberName + "','" + itemId + "'," + "'1')";
                                                queryInsertLike.insertSql(insertLikeSQL);
                                            } else {
                                                MySharedPreference.displayDialog("You have liked this item", Itempage.this);
                                            }
                                        }
                                    };
                                    DBManager queryIsLiked = new DBManager();
                                    queryIsLiked.queryCallBack = queryIsLikedCallBack;
                                    String queryIsLikedSQL = "SELECT * FROM likes WHERE likes.good_id = '" + itemId + "' AND likes.member_email = '" + memberName + "'";
                                    queryIsLiked.querySql(queryIsLikedSQL);
                                } else {
                                    MySharedPreference.displayDialog("You are not yet registered.", Itempage.this);
                                }
                            }
                        };
                        Button likeButton = (Button) findViewById(R.id.button_like);
                        ImageView likeView = (ImageView) findViewById(R.id.imageView_like);
                        likeButton.setOnClickListener(onLikeClickListener);
                        likeView.setOnClickListener(onLikeClickListener);
                    }
                };
                String itemSql = "SELECT name_en, name_zh, brand_id, category_id FROM goods WHERE goods.id = '" + itemId + "'";
                dbManager.querySql(itemSql);
            }

        }
    }

    private float[] toFloatArray(List<Float> listFloat) {
        if (listFloat != null) {
            float[] floatArray = new float[listFloat.size()];
            for (int i = 0; i < floatArray.length; i++) {
                float f = listFloat.get(i);
                floatArray[i] = f;
            }
            return floatArray;
        }
        return new float[0];
    }

    private Number[] toNumberArray(List<Number> listNumber) {
        if (listNumber != null) {
            Number[] numberArray = new Number[listNumber.size()];
            for (int i = 0; i < numberArray.length; i++) {
                Number n = listNumber.get(i);
                numberArray[i] = n;
            }
            return numberArray;
        }
        return new Number[0];
    }


    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
//        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
//        AppEventsLogger.deactivateApp(this);
    }

    ;

    int i=0;
   ProgressBar mProgressBar=(ProgressBar)findViewById(R.id.progressBar1);
    CountDownTimer mCountDownTimer= new CountDownTimer(5000,1000) {

        public void onTick(long millisUntilFinished) {

            mProgressBar.setVisibility(View.VISIBLE);

        }

        @Override
        public void onFinish() {
            //Do what you want
            i++;
            mProgressBar.setVisibility(View.GONE);
        }
    };
    mCountDownTimer.start();


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_itempage, menu);
        return true;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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

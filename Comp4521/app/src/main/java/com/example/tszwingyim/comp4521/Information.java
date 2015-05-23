package com.example.tszwingyim.comp4521;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;


public class Information extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_information);
        Button info = (Button) findViewById(R.id.button_info);
        Button comment = (Button) findViewById(R.id.button_comment);
        Button login = (Button) findViewById(R.id.button_login);
        Button map = (Button) findViewById(R.id.button_map);
        Button menu = (Button) findViewById(R.id.button_menu);
        Button promo = (Button) findViewById(R.id.button_promo);
        Button logout = (Button) findViewById(R.id.button_logout);
        Button howtogo = (Button) findViewById(R.id.button3);
        Button register = (Button) findViewById(R.id.button4);

        String facility = getIntent().getExtras().getString("Facilities");
        if (facility != null) {

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Information.this, Map.class);
                    startActivity(intent);
                }
            });
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Information.this, Comment.class);
                    startActivity(intent);
                }
            });
            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Information.this, CanteenFood.class);
                    startActivity(intent);

                }
            });
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Information.this, Information.class);
                    startActivity(intent);

                }
            });
            promo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Information.this, Commentform.class);
                    startActivity(intent);

                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = TabManager.getInstance().getIntent(Information.this, Login.class);
                    startActivity(intent);

                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Information.this, Information.class);
                    startActivity(intent);

                }
            });
            howtogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Information.this, Information.class);
                    startActivity(intent);

                }
            });
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = TabManager.getInstance().getIntent(Information.this, Register.class);
                    startActivity(intent);

                }
            });

            setUpView(facility);
        }


    }

    private void setUpView(String facilityName) {
        final TextView nameTextView = (TextView) findViewById(R.id.editText_name);
        final TextView infoTextView = (TextView) findViewById(R.id.editText_info);
        final TextView categoryTextView = (TextView) findViewById(R.id.editText_category);
        final RatingBar rankingView = (RatingBar) findViewById(R.id.ratingbar_Indicator);
        if (nameTextView != null &&
                infoTextView != null &&
                categoryTextView != null) {
                DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    if (result != null) {
                        MyStringTokenizer token = new MyStringTokenizer(result, "|");
                        if (token != null) {
                            String name = token.nextToken();
                            if (name != null) {
                                nameTextView.setText(name);
                            }
                            String info = token.nextToken();
                            if (info != null) {
                                infoTextView.setText(info);
                            }
                            String category = token.nextToken();
                            if (category != null) {
                                categoryTextView.setText(category);
                            }
                        }
                    }
                }
            };

            String sql = "SELECT Facilities.NAME, Facilities.INFO, Facilities.CATEGORIES FROM Facilities WHERE NAME = '" + facilityName + "'";
            dbManager.querySql(sql);
            dbManager.updateSql(sql);
        }

        if (rankingView != null) {
            DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    if (result != null) {
                        MyStringTokenizer token = new MyStringTokenizer(result, "|");
                        String rating = token.nextToken();
                        if (rating != null && !rating.equalsIgnoreCase("null")) {
                            int ratingInt = Integer.parseInt(rating);
                            rankingView.setNumStars(ratingInt);
                        }
                    }
                }
            };
            String sql = "SELECT ROUND(AVG(Rating),0) FROM Comments, Facilities WHERE Facilities.NAME = '" + facilityName + "' AND (Facilities.ID = Comments.FacilitiesID)";
            dbManager.querySql(sql);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sports, menu);
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

package com.example.tszwingyim.comp4521;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;


public class Commentform extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_commentform);
        Button confirm = (Button) findViewById(R.id.button_confirm);
        Button info = (Button) findViewById(R.id.button_info);
        Button comment = (Button) findViewById(R.id.button_comment);
        Button login = (Button) findViewById(R.id.button_login);
        Button map = (Button) findViewById(R.id.button_map);
        Button menu = (Button) findViewById(R.id.button_menu);
        Button promo = (Button) findViewById(R.id.button_promo);
        Button logout = (Button) findViewById(R.id.button_logout);
        Button howtogo = (Button) findViewById(R.id.button3);
        Button register = (Button) findViewById(R.id.button4);
        Button Confirm = (Button) findViewById(R.id.button_confirm);
        final String facility = getIntent().getExtras().getString("Facilities");

        final RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingbar);


        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Commentform.this, Canteen.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Commentform.this, Map.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);
            }
        });
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Commentform.this, Comment.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Commentform.this, CanteenFood.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);

            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Commentform.this, Information.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);

            }
        });
        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Commentform.this, Commentform.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Commentform.this, Login.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Commentform.this, Information.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);

            }
        });
        howtogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Commentform.this, Information.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Commentform.this, Register.class);
                intent.putExtra("Facilities", facility);
                startActivity(intent);

            }
        });


        if (facility != null && facility.length() > 0) {
            final String Facilities = getIntent().getExtras().getString("Facilities");
            if (Facilities != null && Facilities.length() > 0) {
                confirm.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {

                        EditText commentEditText = (EditText) findViewById(R.id.edittext_usercomment);
                        int rating = (int) ratingBar.getRating();

                        if (commentEditText != null) {
                            String comment = commentEditText.getText().toString();
                            if (comment != null && comment.length() > 0) {
                                String memberEmail = MySharedPreference.getMemberName(Commentform.this);
                                memberEmail = "kwokhh93@gmail.com";
                                if (memberEmail != null) {
                                    DBManager dbManager = new DBManager();
                                    dbManager.queryCallBack = new QueryCallBack() {
                                        @Override
                                        public void queryResult(String result) {
                                            if (result.equalsIgnoreCase(DBManager.SUCCESS)) {
                                                Intent intent = new Intent(Commentform.this, Information.class);
                                                intent.putExtra("Facilities", Facilities);
                                                startActivity(intent);
                                            }
                                        }
                                    };
                                    String insertCommentSQL = "INSERT INTO Comments (FacilitiesID, Comment, Email, Rating) VALUES ((SELECT ID FROM Facilities WHERE NAME = " +

                                            Facilities + ")," + comment + "','" + memberEmail + "'," + rating + ")";
                                    dbManager.updateSql(insertCommentSQL);
                                } else {
                                    MySharedPreference.displayDialog("You have not yet registered", Commentform.this);
                                }
                            }
                        }

                    }
                });
            }
        }

    }
        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_commentform, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
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


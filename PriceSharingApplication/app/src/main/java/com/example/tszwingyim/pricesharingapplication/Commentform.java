package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Commentform extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_commentform);
        Button recommend = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button member = (Button) findViewById(R.id.button_member);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button pricechart = (Button) findViewById(R.id.button_pricechart);
        Button sharepricelist = (Button) findViewById(R.id.button_shareprice);
        Button commentlist = (Button) findViewById(R.id.button_comment);
        Button itempage = (Button) findViewById(R.id.button_priceinfo);
        Button confirm = (Button) findViewById(R.id.button_confirm);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String memberEmail = MySharedPreference.getMemberName(Commentform.this);
                if (memberEmail != null && memberEmail.length() > 0) {
                    Intent intent = TabManager.getInstance().getIntent(Commentform.this, Memberpage.class);
                    startActivity(intent);
                } else {
                    Intent intent = TabManager.getInstance().getIntent(Commentform.this, Member.class);
                    startActivity(intent);
                }
            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentform.this, Recommendation.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentform.this, MainActivity.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentform.this, Barcode.class);
                startActivity(intent);
            }
        });
        commentlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentform.this, Commentlist.class);
                startActivity(intent);

            }
        });
        pricechart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentform.this, Pricechart.class);
                startActivity(intent);

            }
        });
        sharepricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentform.this, SharePricelist.class);
                startActivity(intent);
            }
        });
        itempage.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Commentform.this, Itempage.class);
                startActivity(intent);
            }
        });

        final String goodId = this.getIntent().getExtras().getString("ITEM_ID");
        if (goodId != null && goodId.length() > 0) {
            confirm.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    EditText commentEditText = (EditText) findViewById(R.id.editText_usercomment);
                    if (commentEditText != null) {
                        String comment = commentEditText.getText().toString();
                        if (comment != null && comment.length() > 0) {
                            String memberEmail = MySharedPreference.getMemberName(Commentform.this);
                            if (memberEmail != null) {
                                DBManager dbManager = new DBManager();
                                dbManager.queryCallBack = new QueryCallBack() {
                                    @Override
                                    public void queryResult(String result) {
                                        if (result.equalsIgnoreCase(DBManager.SUCCESS)) {
                                            Intent intent = new Intent(Commentform.this, Commentlist.class);
                                            intent.putExtra("ITEM_ID", goodId);
                                            startActivity(intent);
                                        }
                                    }
                                };
                                String insertCommentSQL = "INSERT INTO good_comments (good_id, comment, member_email) VALUES ('" +
                                        goodId + "','" + comment + "','" + memberEmail + "')";
                                dbManager.insertSql(insertCommentSQL);
                            } else {
                                MySharedPreference.displayDialog("You have not yet registered", Commentform.this);
                            }
                        }
                    }
                }
            });
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_commentform, menu);
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

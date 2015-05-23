package com.example.tszwingyim.comp4521;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Commentform extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commentform);
        Button confirm = (Button) findViewById(R.id.button_confirm);

        final String itemId = getIntent().getExtras().getString("ITEM_ID");
        if (itemId != null && itemId.length() > 0) {
            confirm.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    /*
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
                                            Intent intent = new Intent(Commentform.this, Comment.class);
                                            intent.putExtra("ITEM_ID", itemId);
                                            startActivity(intent);
                                        }
                                    }
                                };
                                String insertCommentSQL = "INSERT INTO good_comments (good_id, comment, member_email) VALUES ('" +
                                        itemId + "','" + comment + "','" + memberEmail + "')";
                                dbManager.updateSql(insertCommentSQL);
                            } else {
                                MySharedPreference.displayDialog("You have not yet registered", Commentform.this);
                            }
                        }
                    }
                    */
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

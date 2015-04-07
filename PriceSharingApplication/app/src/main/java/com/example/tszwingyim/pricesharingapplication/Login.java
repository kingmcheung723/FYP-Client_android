package com.example.tszwingyim.pricesharingapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);
        Button recommendation = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button search = (Button) findViewById(R.id.button_search);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button confirm = (Button) findViewById(R.id.button_login);
        EditText mEditText = (EditText) findViewById(R.id.EditText_Login_Password);

        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Login.this, Search.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Login.this, MainActivity.class);
                startActivity(intent);

            }
        });
        recommendation.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Login.this, Recommendation.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Login.this, Barcode.class);
                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new ConfirmButtonOnClickListener(this));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    private class ConfirmButtonOnClickListener implements View.OnClickListener {
        Activity activity;

        public ConfirmButtonOnClickListener(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(View v) {
            EditText emailEditText = (EditText) activity.findViewById(R.id.EditText_Login_Email);
            String emailStr = emailEditText.getText().toString();
            TextView emailvalid = (TextView) activity.findViewById(R.id.textView_checkvalid);
            if (this.isValidEmail(emailStr)) {
                EditText passwordEditText = (EditText) activity.findViewById(R.id.EditText_Login_Password);
                String passwordStr = passwordEditText.getText().toString();
                if (passwordStr.length() >= 6) if (passwordStr.length() <= 10) {
                    DBManager dbManager = new DBManager();
                    dbManager.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null) {
                                MyStringTokenizer token = new MyStringTokenizer(result, "|");
                                if (token.hasMoreTokens()) {
                                    String memberName = token.nextToken();
                                    MySharedPreference.saveMemberName(memberName, Login.this);
                                    Intent intent = TabManager.getInstance().getIntent(Login.this, Memberpage.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    };
                    String sql = "SELECT email FROM members WHERE email = " + "'" + emailStr + "' AND password = '" + passwordStr + "'";
                    dbManager.querySql(sql);
                } else {
                    MySharedPreference.displayDialog("Password must be less than 10 digits", Login.this);
                }
                else {
                    MySharedPreference.displayDialog("Password must be more than 6 digits", Login.this);
                }
            } else {
                MySharedPreference.displayDialog("The Email is not valid", Login.this);
            }
        }

        private boolean isValidEmail(CharSequence target) {
            if (TextUtils.isEmpty(target)) {
                return false;
            } else {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
            }
        }
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



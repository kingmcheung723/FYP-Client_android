package com.example.tszwingyim.pricesharingapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Register extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_register);
        Button recommendation = (Button) findViewById(R.id.button_recommend);
        Button category = (Button) findViewById(R.id.button_category);
        Button search = (Button) findViewById(R.id.button_search);
        Button barcode = (Button) findViewById(R.id.button_barcode);
        Button confirm = (Button) findViewById(R.id.button_confirmreg);

        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Register.this, Search.class);
                startActivity(intent);
            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
        recommendation.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Register.this, Recommendation.class);
                startActivity(intent);
            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Register.this, Barcode.class);
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
        getMenuInflater().inflate(R.menu.menu_register, menu);
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

    private class ConfirmButtonOnClickListener implements View.OnClickListener {
        Activity activity;

        public ConfirmButtonOnClickListener(Activity activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(View v) {
            EditText emailEditText = (EditText) activity.findViewById(R.id.EditText_Register_Email);
            String emailStr = emailEditText.getText().toString();
            TextView emailvalid = (TextView) activity.findViewById(R.id.textView_emailvalid);
            if (this.isValidEmail(emailStr)) {
                EditText passwordEditText = (EditText) activity.findViewById(R.id.EditText_Register_Password);
                EditText confirmPasswordEditText = (EditText) activity.findViewById(R.id.EditText_Register_ConfPassword);
                String passwordStr = passwordEditText.getText().toString();
                String confirmPasswordStr = passwordEditText.getText().toString();


                if (passwordStr.equalsIgnoreCase(confirmPasswordStr)) {
                    if (passwordStr.length() >= 6) if (passwordStr.length() <= 10) {
                        DBManager dbManager = new DBManager();
                        dbManager.queryCallBack = new QueryCallBack() {
                            @Override
                            public void queryResult(String result) {
                                if (result != null) {
                                    MySharedPreference.saveMemberName(result, Register.this);
                                    Intent intent = TabManager.getInstance().getIntent(Register.this, Memberpage.class);
                                    startActivity(intent);
                                }
                            }
                        };
                        String sql = "INSERT INTO `members`(`email`, `password`) VALUES ('" + emailStr + "','" + passwordStr + "')";
                        dbManager.querySql(sql);
                    } else {
                        emailvalid.setText("Password must be less than 10 digits");
                    }
                    else {
                        emailvalid.setText("Password must be more than 6 digits");
                    }
                } else {
                    emailvalid.setText("Confirm password does not match password");
                }
            } else {
                emailvalid.setText(" The Email is not valid");
            }
//            Intent intent = TabManager.getInstance().getIntent(Register.this, Memberpage.class);
//            startActivity(intent);
        }

        private boolean isValidEmail(CharSequence target) {
            if (TextUtils.isEmpty(target)) {
                return false;
            } else {
                return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
            }
        }
    }
}

package com.example.tszwingyim.comp4521;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Register extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button confirm = (Button) findViewById(R.id.button_confirmreg);
        Button info = (Button) findViewById(R.id.button_info);
        Button comment = (Button) findViewById(R.id.button_comment);
        Button member = (Button) findViewById(R.id.button_login);
        Button map = (Button) findViewById(R.id.button_map);
        Button menu = (Button) findViewById(R.id.button_menu);
        Button promo = (Button) findViewById(R.id.button_promo);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = TabManager.getInstance().getIntent(Register.this, Map.class);
                startActivity(intent);
            }
        });

        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Register.this,Comment.class);
                startActivity(intent);
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Register.this, CanteenFood.class);
                startActivity(intent);

            }
        });
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Register.this, Information.class);
                startActivity(intent);

            }
        });
        promo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Register.this, Promotion.class);
                startActivity(intent);

            }
        });
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Register.this, Promotion.class);
                startActivity(intent);

            }
        });
//        confirm.setOnClickListener(new ConfirmButtonOnClickListener(this));

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {EditText emailEditText = (EditText) findViewById(R.id.EditText_Register_Email);
                final String emailStr = emailEditText.getText().toString();

                //    if (this.isValidEmail(emailStr)) {
                EditText passwordEditText = (EditText) findViewById(R.id.EditText_Register_Password);
                EditText confirmPasswordEditText = (EditText) findViewById(R.id.EditText_Register_ConfPassword);
                String passwordStr = passwordEditText.getText().toString();
                String confirmPasswordStr = passwordEditText.getText().toString();


//                if (passwordStr.equalsIgnoreCase(confirmPasswordStr)) {
//                    if (passwordStr.length() >= 6) if (passwordStr.length() <= 10) {
                DBManager dbManager = new DBManager();
                dbManager.queryCallBack = new QueryCallBack() {
                    @Override
                    public void queryResult(String result) {
                        if (result != null) {
                            MySharedPreference.saveMemberName(emailStr, Register.this);
                            Intent intent = TabManager.getInstance().getIntent(Register.this, Information.class);
                            startActivity(intent);
                        }
                    }
                };
                String sql = "INSERT INTO Members (Email, Password) VALUES ('" + emailStr + "','" + passwordStr + "')";
                String sql1 ="SELECT MAX(MemberID) FROM Members";

                dbManager.updateSql(sql);
            }
        });

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
            final String emailStr = emailEditText.getText().toString();

            if (this.isValidEmail(emailStr)) {
                EditText passwordEditText = (EditText) activity.findViewById(R.id.EditText_Register_Password);
                EditText confirmPasswordEditText = (EditText) activity.findViewById(R.id.EditText_Register_ConfPassword);
                String passwordStr = passwordEditText.getText().toString();
                String confirmPasswordStr = passwordEditText.getText().toString();


//                if (passwordStr.equalsIgnoreCase(confirmPasswordStr)) {
//                    if (passwordStr.length() >= 6) if (passwordStr.length() <= 10) {
                DBManager dbManager = new DBManager();
                dbManager.queryCallBack = new QueryCallBack() {
                    @Override
                    public void queryResult(String result) {
                        if (result != null) {
                            MySharedPreference.saveMemberName(emailStr, Register.this);
                            Intent intent = TabManager.getInstance().getIntent(Register.this, Information.class);
                            startActivity(intent);
                        }
                    }
                };
                String sql = "INSERT INTO Members (Email, Password) VALUES ('" + emailStr + "','" + passwordStr + "')";
                String sql1 ="SELECT MAX(MemberID) FROM Members";

                dbManager.updateSql(sql);
            }
//                    } else {
//                        // emailvalid.setText("Password must be less than 10 digits");
//
//                        MySharedPreference.displayDialog("Password must be less than 10 digits", Register.this);
//                    }
//                    else {
//                        // emailvalid.setText("Password must be more than 6 digits");
//                        MySharedPreference.displayDialog("Password must be less than 6 digits", Register.this);
//                    }
//                } else {
//                    //emailvalid.setText("Confirm password does not match password");
//                    MySharedPreference.displayDialog("Confirm password does not match password", Register.this);
//                }
//            } else {
//                // emailvalid.setText(" The Email is not valid");
//                MySharedPreference.displayDialog(" The Email is not valid", Register.this);
//            }
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

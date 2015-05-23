package com.example.tszwingyim.comp4521;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Register extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_register);
        Button info = (Button) findViewById(R.id.button_info);
        Button comment = (Button) findViewById(R.id.button_comment);
        Button login = (Button) findViewById(R.id.button_login);
        Button map = (Button) findViewById(R.id.button_map);
        Button menu = (Button) findViewById(R.id.button_menu);
        Button promo = (Button) findViewById(R.id.button_promo);
        Button logout = (Button) findViewById(R.id.button_logout);
        Button howtogo = (Button) findViewById(R.id.button3);
        Button register = (Button) findViewById(R.id.button4);
        Button Confirm = (Button) findViewById(R.id.button_confirmreg);
        final String facility = getIntent().getExtras().getString("Facilities");
        if (facility != null) {

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Register.this, Map.class);
                    intent.putExtra("Facilities", facility);

                    startActivity(intent);
                }
            });

            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Register.this, Comment.class);
                    intent.putExtra("Facilities", facility);

                    startActivity(intent);
                }
            });

            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Register.this, Information.class);
                    intent.putExtra("Facilities", facility);

                    startActivity(intent);

                }
            });
            promo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Register.this, Commentform.class);
                    intent.putExtra("Facilities", facility);

                    startActivity(intent);

                }
            });

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String member = MySharedPreference.getMemberName(Register.this);
                    if (member != null && member.length() > 0) {
                        MySharedPreference.displayDialog("You have already logged in", Register.this);
                    } else {
                        Intent intent =new Intent(Register.this, Login.class);
                        intent.putExtra("Facilities", facility);
                        startActivity(intent);
                    }

                }
            });

            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String member = MySharedPreference.getMemberName(Register.this);
                    if (member != null && member.length() > 0) {
                        MySharedPreference.clearMemberName(Register.this);
                        MySharedPreference.displayDialog("Logout success", Register.this);
                    } else {
                        MySharedPreference.displayDialog("You haven't log in", Register.this);
                    }


                }
            });

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Register.this, Register.class);
                    startActivity(intent);

                }
            });
        }
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {EditText emailEditText = (EditText) findViewById(R.id.EditText_Register_Email);
                final String emailStr = emailEditText.getText().toString();

                if (!emailStr.isEmpty()) {
                    EditText passwordEditText = (EditText) findViewById(R.id.EditText_Register_Password);
                    EditText confirmPasswordEditText = (EditText) findViewById(R.id.EditText_Register_ConfPassword);
                    String passwordStr = passwordEditText.getText().toString();
                    String confirmPasswordStr = passwordEditText.getText().toString();


//                           if (passwordStr.equalsIgnoreCase(confirmPasswordStr)) {
                    if (passwordStr.length() >= 6) if (passwordStr.length() <= 10) {
                        DBManager dbManager = new DBManager();
                        dbManager.queryCallBack = new QueryCallBack() {
                            @Override
                            public void queryResult(String result) {
                                if (result != null) {
                                    MySharedPreference.saveMemberName(emailStr, Register.this);
                                    Intent intent = TabManager.getInstance().getIntent(Register.this, Commentform.class);
                                    startActivity(intent);
                                }
                            }
                        };
                        String sql = "INSERT INTO Members (Email, Password) VALUES ('" + emailStr + "','" + passwordStr + "')";
                        dbManager.updateSql(sql);
                    } else {
                        // emailvalid.setText("Password must be less than 10 digits");

                        MySharedPreference.displayDialog("Password must be less than 10 digits", Register.this);
                    }
                    else {
                        // emailvalid.setText("Password must be more than 6 digits");
                        MySharedPreference.displayDialog("Password must be less than 6 digits", Register.this);
                    }
                } else {
                    //emailvalid.setText("Confirm password does not match password");
                    MySharedPreference.displayDialog("Confirm password does not match password", Register.this);
                }
//        } else {
//            // emailvalid.setText(" The Email is not valid");
//            MySharedPreference.displayDialog(" The Email is not valid", Register.this);
//        }
//            Intent intent = TabManager.getInstance().getIntent(Register.this, Memberpage.class);
//            startActivity(intent);
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

            if (!emailStr.isEmpty()) {
                EditText passwordEditText = (EditText) activity.findViewById(R.id.EditText_Register_Password);
                EditText confirmPasswordEditText = (EditText) activity.findViewById(R.id.EditText_Register_ConfPassword);
                String passwordStr = passwordEditText.getText().toString();
                String confirmPasswordStr = passwordEditText.getText().toString();


//                           if (passwordStr.equalsIgnoreCase(confirmPasswordStr)) {
                if (passwordStr.length() >= 6) if (passwordStr.length() <= 10) {
                    DBManager dbManager = new DBManager();
                    dbManager.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null) {
                                MySharedPreference.saveMemberName(emailStr, Register.this);
                                Intent intent = TabManager.getInstance().getIntent(Register.this, Commentform.class);
                                startActivity(intent);
                            }
                        }
                    };
                    String sql = "INSERT INTO Members (Email, Password) VALUES ('" + emailStr + "','" + passwordStr + "')";
                    dbManager.updateSql(sql);
                } else {
                    // emailvalid.setText("Password must be less than 10 digits");

                    MySharedPreference.displayDialog("Password must be less than 10 digits", Register.this);
                }
                else {
                    // emailvalid.setText("Password must be more than 6 digits");
                    MySharedPreference.displayDialog("Password must be less than 6 digits", Register.this);
                }
            } else {
                //emailvalid.setText("Confirm password does not match password");
                MySharedPreference.displayDialog("Confirm password does not match password", Register.this);
            }
//        } else {
//            // emailvalid.setText(" The Email is not valid");
//            MySharedPreference.displayDialog(" The Email is not valid", Register.this);
//        }
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

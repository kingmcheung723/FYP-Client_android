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


public class Login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);
        Button info = (Button) findViewById(R.id.button_info);
        Button comment = (Button) findViewById(R.id.button_comment);
        Button login = (Button) findViewById(R.id.button_login);
        Button map = (Button) findViewById(R.id.button_map);
        Button promo = (Button) findViewById(R.id.button_promo);
        Button logout = (Button) findViewById(R.id.button_logout);
        Button register = (Button) findViewById(R.id.button4);
        Button confirm = (Button) findViewById(R.id.button_confirm);

        final String facility = getIntent().getExtras().getString("Facilities");

        if (facility != null) {

            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Map.class);
                    intent.putExtra("Facilities", facility);
                    startActivity(intent);
                }
            });
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Comment.class);
                    intent.putExtra("Facilities", facility);

                    startActivity(intent);
                }
            });
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Information.class);
                    intent.putExtra("Facilities", facility);

                    startActivity(intent);

                }
            });
            promo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Login.this, Commentform.class);
                    intent.putExtra("Facilities", facility);

                    startActivity(intent);

                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Login.this, Login.class);
                    intent.putExtra("Facilities", facility);

                    startActivity(intent);

                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String member = MySharedPreference.getMemberName(Login.this);
                    if (member != null && member.length() > 0) {
                        MySharedPreference.clearMemberName(Login.this);
                        MySharedPreference.displayDialog("Logout success", Login.this);
                    } else {
                        MySharedPreference.displayDialog("You haven't log in", Login.this);
                    }

                }
            });
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Login.this, Register.class);
                    startActivity(intent);

                }
            });
        }

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText emailEditText = (EditText) findViewById(R.id.EditText_Login_Email);
                String emailStr = emailEditText.getText().toString();
                EditText passwordEditText = (EditText) findViewById(R.id.EditText_Login_Password);
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
                                    Intent intent = new Intent(Login.this, Information.class);
                                    intent.putExtra("Facilities", facility);
                                    startActivity(intent);
                                }
                            }
                        }
                    };
                    String sql = "SELECT Email FROM Members WHERE Email = " + "'" + emailStr + "' AND Password = '" + passwordStr + "'";
                    dbManager.querySql(sql);
                } else {
                    MySharedPreference.displayDialog("Password must be less than 10 digits", Login.this);
                }
                else {
                    MySharedPreference.displayDialog("Password must be more than 6 digits", Login.this);
                }
            }
        });

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
                                    Intent intent = TabManager.getInstance().getIntent(Login.this, Information.class);
                                    startActivity(intent);
                                }
                            }
                        }
                    };
                    String sql = "SELECT Email FROM Members WHERE Email = " + "'" + emailStr + "' AND Password = '" + passwordStr + "'";
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

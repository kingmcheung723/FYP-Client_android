package com.example.tszwingyim.comp4521;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Comment extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_comment);

        ListView commentList = (ListView) findViewById(R.id.listView);
        if (commentList != null) {
            DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    if (result != null) {

                    }
                }
            };
            String sql = "SELECT * from Comments WHERE FacilitiesID = 1 AND ";
            dbManager.querySql(sql);
        }

        Button info = (Button) findViewById(R.id.button_info);
        Button comment = (Button) findViewById(R.id.button_comment);
        Button login = (Button) findViewById(R.id.button_login);
        Button map = (Button) findViewById(R.id.button_map);
        Button menu = (Button) findViewById(R.id.button_menu);
        Button promo = (Button) findViewById(R.id.button_promo);
        Button logout = (Button) findViewById(R.id.button_logout);
        Button howtogo = (Button) findViewById(R.id.button3);
        Button register = (Button) findViewById(R.id.button4);

        final String facility = getIntent().getExtras().getString("Facilities");
        if(facility != null) {
            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Comment.this, Map.class);
                    startActivity(intent);
                }
            });
            comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Comment.this, Comment.class);
                    intent.putExtra("Facilities", facility);
                    startActivity(intent);
                }
            });
            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Comment.this, CanteenFood.class);
                    startActivity(intent);

                }
            });
            info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Comment.this, Information.class);
                    startActivity(intent);

                }
            });
            promo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Comment.this, Commentform.class);
                    startActivity(intent);

                }
            });
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = TabManager.getInstance().getIntent(Comment.this, Login.class);
                    startActivity(intent);

                }
            });
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Comment.this, Information.class);
                    startActivity(intent);

                }
            });
            howtogo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = TabManager.getInstance().getIntent(Comment.this, Information.class);
                    startActivity(intent);

                }
            });
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = TabManager.getInstance().getIntent(Comment.this, Register.class);
                    startActivity(intent);

                }
            });

            SetUpListView(facility);
        }
    }

    private void SetUpListView(String facility) {
        final ListView listView = (ListView) findViewById(R.id.listView);
        if (listView != null) {
            DBManager dbManager = new DBManager();
            dbManager.queryCallBack = new QueryCallBack() {
                @Override
                public void queryResult(String result) {
                    if (result != null) {
                        MyStringTokenizer token = new MyStringTokenizer(result, "|");
                        String[] names = new String[token.countTokens()/2];
                        int count = 0;
                        while (token.hasMoreTokens()) {
                            String comment = null;
                            String name = null;
                            comment = token.nextToken();
                            name =  token.nextToken();

                            names[count] ='"' + comment + '"' + " \n By " + name;

                            count++;
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Comment.this, android.R.layout.simple_list_item_1, names);
                        listView.setAdapter(adapter);
//                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                if (view != null) {
//                                    String text = (String) ((TextView) view).getText();
//                                    if (text != null) {
//                                        Intent intent = new Intent(Comment.this, Comment.class);
//                                        intent.putExtra("Facilities", text);
//                                        startActivity(intent);
//                                    }
//                                }
//                            }
//                        });
                    }
                }
            };
            String sql = "SELECT Comment,Email FROM Comments WHERE FacilitiesID = (SELECT ID FROM Facilities WHERE NAME = '" + facility + "')";
            dbManager.querySql(sql);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_comment, menu);
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

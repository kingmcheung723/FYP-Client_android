package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Itempage extends ActionBarActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_itempage);
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button category = (Button)findViewById(R.id.button_category);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);
        Button pricechart = (Button)findViewById(R.id.button_pricechart);
        Button sharepricelist = (Button)findViewById(R.id.button_shareprice);
        Button commentlist = (Button)findViewById(R.id.button_comment);
        Button sharepriceform = (Button)findViewById(R.id.button_goshareprice);
        Button commentform = (Button)findViewById(R.id.button_givecomment);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Member.class);
                startActivity(intent);

            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Recommendation.class);
                startActivity(intent);

            }
        });
        category.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, MainActivity.class);
                startActivity(intent);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Barcode.class);
                startActivity(intent);
            }
        });
        commentlist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Commentlist.class);
                startActivity(intent);

            }
        });
        pricechart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Pricechart.class);
                startActivity(intent);

            }
        });
        sharepricelist.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, SharePricelist.class);
                startActivity(intent);
            }
        });
        sharepriceform.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Sharepriceform.class);
                startActivity(intent);
            }
        });
        commentform.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = TabManager.getInstance().getIntent(Itempage.this, Commentform.class);
                startActivity(intent);
            }
        });
        String itemname = "hihi1"; // scanResult.getContents();
        //this.Itempage= itemname;
        TextView textView = (TextView)this.findViewById(R.id.editText_name);
        textView.setText(itemname);

        String brandname = "hihi1"; // scanResult.getContents();
        //this.Itempage= itemname;
        TextView textView1 = (TextView)this.findViewById(R.id.editText_brand);
        textView1.setText(brandname);

        String categoryname = "hihi1"; // scanResult.getContents();
        //this.Itempage= itemname;
        TextView textView2 = (TextView)this.findViewById(R.id.editText_category);
        textView2.setText(categoryname);

        String parknshopprice = "hihi1"; // scanResult.getContents();
        //this.Itempage= itemname;
        TextView textView3 = (TextView)this.findViewById(R.id.editText_parknshop);
        textView3.setText(parknshopprice);

        String wellcomeprice = "hihi1"; // scanResult.getContents();
        //this.Itempage= itemname;
        TextView textView4 = (TextView)this.findViewById(R.id.editText_wellcome);
        textView4.setText(wellcomeprice);

        String aeonprice = "hihi1"; // scanResult.getContents();
        //this.Itempage= itemname;
        TextView textView5 = (TextView)this.findViewById(R.id.editText_aeon);
        textView5.setText(aeonprice);

        String daicheongprice = "hihi1"; // scanResult.getContents();
        //this.Itempage= itemname;
        TextView textView6 = (TextView)this.findViewById(R.id.editText_daicheong);
        textView6.setText(daicheongprice);

        String marketplaceprice = "hihi1"; // scanResult.getContents();
        //this.Itempage= itemname;
        TextView textView7 = (TextView)this.findViewById(R.id.editText_marketplace);
        textView7.setText(marketplaceprice);
        String nooflike = "hihi1"; // scanResult.getContents();
        //this.Itempage= itemname;
        TextView textView8 = (TextView)this.findViewById(R.id.textView_numoflike);
        textView8.setText(nooflike);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_itempage, menu);
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

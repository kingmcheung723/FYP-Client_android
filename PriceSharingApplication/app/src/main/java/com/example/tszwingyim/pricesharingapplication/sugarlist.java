package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


public class sugarlist extends ActionBarActivity {
    //int num = 0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_sugarlist);
        Button search = (Button)findViewById(R.id.button_search);
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent1 = TabManager.getInstance().getIntent(sugarlist.this, Member.class);
                startActivity(myintent1);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = TabManager.getInstance().getIntent(sugarlist.this, Search.class);
                startActivity(myintent2);

            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent3 = TabManager.getInstance().getIntent(sugarlist.this, Recommendation.class);
                startActivity(myintent3);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent4 = TabManager.getInstance().getIntent(sugarlist.this, Barcode.class);
                startActivity(myintent4);
            }
        });

        ListView searcharray = (ListView) this.findViewById(R.id.listView2);
//        Intent myIntent11 = getIntent();
        String[] itembread = new String[]{"蛋糕1","麵包","穀類早餐","麵包醬","蜂蜜 / 蜜糖 / 糖漿"};
        String[] itemdairy = new String[]{"牛奶 / 牛奶飲品","芝士 / 乳酪 / 乳酸產品","牛油 / 植物牛油 / 忌廉","冰凍甜點","豆漿 / 豆奶","豆腐製品","蛋類"};
        String[] itemcandy = new String[]{"糖果 / 甜品","乾果","能量棒 / 營養棒 / 點心棒","餅乾","小食"};
        String[] itemrice = new String[]{"米","食用油","罐頭食品","無菌紙盒包裝食品","新鮮蔬菜","新鮮水果","冰鮮/新鮮肉類","經急凍/解凍處理肉類","急凍肉類","急凍海產"};
        String[] itemnoodle = new String[]{"即食麵 / 快熟粉","烏冬麵","非即食粉麵","調味 / 煮食用料","急凍加工食品 / 冷藏加工食品"};
        String[] itemdrink = new String[]{"汽水","咖啡 / 茶包 / 即沖奶茶","果汁類飲品","東方特色飲品","樽裝水 / 運動飲品 / 能量飲品","麥片飲品 / 麥芽飲品 / 朱古力飲品"};
        String[] itemdaily = new String[]{"口腔護理","女士衛生用品","沐浴露 / 皂液 / 肥皂","頭髮護理","皮膚護理","女士脫毛用品 / 除毛用品","男士護理","香體用品","藥品","避孕","成人紙尿片 / 紙尿褲"};
        String[] itemclean = new String[]{"洗衣用品","清潔用品","紙品","保鮮紙 / 食物袋 / 錫紙","吸濕防霉","電器","寵物食品"};
        String[] itembaby = new String[]{"嬰幼兒奶粉 / 兒童奶粉","孕婦奶粉","成人奶粉","嬰幼兒食品 / 嬰兒食品 / 幼兒食品","嬰兒用品"};
        String[] itemalcohol = new String[]{"啤酒","仙地","紅酒","白酒","紹興酒","米酒"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,itemnoodle);
        searcharray.setAdapter(adapter);

//        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
//                R.array.searchbread_array, android.R.layout.simple_spinner_item);
//        ListView myList=
//                (ListView) findViewById(R.id.listView2);
//        myList.setAdapter(adapter2);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sugarlist, menu);
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

package com.example.tszwingyim.pricesharingapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.StringTokenizer;


public class Smallcategory extends ActionBarActivity {
    //int num = 0;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Hide the action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_smallcategory);
        Button search = (Button)findViewById(R.id.button_search);
        Button recommend = (Button)findViewById(R.id.button_recommend);
        Button member = (Button)findViewById(R.id.button_member);
        Button barcode = (Button)findViewById(R.id.button_barcode);

        member.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent1 = TabManager.getInstance().getIntent(Smallcategory.this, Member.class);
                startActivity(myintent1);

            }
        });
        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent2 = TabManager.getInstance().getIntent(Smallcategory.this, Search.class);
                startActivity(myintent2);

            }
        });
        recommend.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent3 = TabManager.getInstance().getIntent(Smallcategory.this, Recommendation.class);
                startActivity(myintent3);

            }
        });
        barcode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent myintent4 = TabManager.getInstance().getIntent(Smallcategory.this, Barcode.class);
                startActivity(myintent4);
            }
        });

        String bigCategory = this.getIntent().getExtras().getString("Category");
        String[] items = null;
        if (bigCategory.equalsIgnoreCase("sugar")) {
            items = new String[]{"即食麵 / 快熟粉","烏冬麵","非即食粉麵","調味 / 煮食用料","急凍加工食品 / 冷藏加工食品"};
        } else if (bigCategory.equalsIgnoreCase("bread")) {
            items = new String[]{"蛋糕1","麵包","穀類早餐","麵包醬","蜂蜜 / 蜜糖 / 糖漿"};
        }else if (bigCategory.equalsIgnoreCase("dairy")) {
            items = new String[]{"牛奶 / 牛奶飲品","芝士 / 乳酪 / 乳酸產品","牛油 / 植物牛油 / 忌廉","冰凍甜點","豆漿 / 豆奶","豆腐製品","蛋類"};
        }else if (bigCategory.equalsIgnoreCase("candy")) {
            items = new String[]{"糖果 / 甜品","乾果","能量棒 / 營養棒 / 點心棒","餅乾","小食"};
        }else if (bigCategory.equalsIgnoreCase("rice")) {
            items = new String[]{"米","食用油","罐頭食品","無菌紙盒包裝食品","新鮮蔬菜","新鮮水果","冰鮮/新鮮肉類","經急凍/解凍處理肉類","急凍肉類","急凍海產"};
        }else if (bigCategory.equalsIgnoreCase("drink")) {
            items = new String[]{"汽水","咖啡 / 茶包 / 即沖奶茶","果汁類飲品","東方特色飲品","樽裝水 / 運動飲品 / 能量飲品","麥片飲品 / 麥芽飲品 / 朱古力飲品"};
        }else if (bigCategory.equalsIgnoreCase("cleaning")) {
            items = new String[]{"洗衣用品","清潔用品","紙品","保鮮紙 / 食物袋 / 錫紙","吸濕防霉","電器","寵物食品"};
        }else if (bigCategory.equalsIgnoreCase("daily")) {
            items = new String[]{"口腔護理","女士衛生用品","沐浴露 / 皂液 / 肥皂","頭髮護理","皮膚護理","女士脫毛用品 / 除毛用品","男士護理","香體用品","藥品","避孕","成人紙尿片 / 紙尿褲"};
        }else if (bigCategory.equalsIgnoreCase("baby")) {
            items = new String[]{"嬰幼兒奶粉 / 兒童奶粉","孕婦奶粉","成人奶粉","嬰幼兒食品 / 嬰兒食品 / 幼兒食品","嬰兒用品"};
        }else if (bigCategory.equalsIgnoreCase("alcohol")) {
            items = new String[]{"啤酒","仙地","紅酒","白酒","紹興酒","米酒"};
        }

        ListView categoryListView = (ListView) this.findViewById(R.id.listView2);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,items);
        categoryListView.setAdapter(adapter);
        categoryListView.setOnItemClickListener(new MyOnItemClickListener(items));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_smallcategory, menu);
        return true;
    }

    public class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        String[] categories;

        public MyOnItemClickListener(String[] categories) {
            this.categories = categories;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (position <= categories.length && position >= 0) {
                String category = this.categories[position];

                if (category != null) {
                    final DBManager categoryIdDbManager = new DBManager();
                    categoryIdDbManager.queryCallBack = new QueryCallBack() {
                        @Override
                        public void queryResult(String result) {
                            if (result != null) {
                                StringTokenizer token = new StringTokenizer(result, "|");
                                if (token.hasMoreTokens()) {
                                    String categoryId = token.nextToken();
                                    if (categoryId != null) {
                                        DBManager goodsDbManager = new DBManager();
                                        goodsDbManager.queryCallBack = new QueryCallBack() {
                                            @Override
                                            public void queryResult(String result) {
                                                if (result != null) {
                                                    StringTokenizer token = new StringTokenizer(result, "|");
                                                    String[] goodNames = new String[token.countTokens()];
                                                    int count = 0;
                                                    while (token.hasMoreTokens()) {
                                                        String goodName = token.nextToken();
                                                        if (goodName != null) {
                                                            goodNames[count] = goodName;
                                                            count++;
                                                        }
                                                    }
                                                    Intent intent = new Intent(Smallcategory.this, GoodList.class);
                                                    intent.putExtra("GOOD_NAMES", goodNames);
                                                    startActivity(intent);
                                                }
                                            }
                                        };
                                        String goodsSQL = "SELECT name_zh FROM goods WHERE goods.category_id = '" + categoryId + "'";
                                        goodsDbManager.querySql(goodsSQL);
                                    }
                                }
                            }
                        }
                    };
                    String categorySQL = "SELECT id FROM categories WHERE categories.name_zh =  '" + category + "' OR categories.name_en = '" + category + "'";
                    categoryIdDbManager.querySql(categorySQL);
                }
            }
        }
    };
}

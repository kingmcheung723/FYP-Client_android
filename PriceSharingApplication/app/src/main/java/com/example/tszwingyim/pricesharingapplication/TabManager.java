package com.example.tszwingyim.pricesharingapplication;


import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

public class TabManager {

    private static TabManager instance = null;
    HashMap<Integer, Intent> intentMap = new HashMap<Integer, Intent>();

    public static enum Pages {
        MainPage,
        Barcode,
        Member,
        Recommendation,
        Search,
        ShopLocationSearch
    }

    public void setIntent(Intent intent, Pages page) {
        if (intent != null) {
            if (intentMap.get(page.ordinal()) == null) {
                intentMap.put(page.ordinal(), intent);
            }
        }
    }

    public Intent getPageIntent(Pages page) {
        Intent intent;
        switch (page) {
            case MainPage:
                intent = intentMap.get(Pages.MainPage.ordinal());
                break;
            case Barcode:
                intent = intentMap.get(Pages.Barcode.ordinal());
                break;
            case Member:
                intent = intentMap.get(Pages.Member.ordinal());
                break;
            case Recommendation:
                intent = intentMap.get(Pages.Recommendation.ordinal());
                break;
            case Search:
                intent = intentMap.get(Pages.Search.ordinal());
                break;
            case ShopLocationSearch:
                intent = intentMap.get(Pages.ShopLocationSearch.ordinal());
                break;
            default:
                intent = intentMap.get(Pages.MainPage.ordinal());
                break;
        }
        return intent;
    }

    public synchronized static TabManager getInstance() {
        if (instance == null) {
            instance = new TabManager();
        }
        return instance;
    }
}

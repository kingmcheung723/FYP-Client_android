package com.example.tszwingyim.pricesharingapplication;


import android.content.Context;
import android.content.Intent;

import java.util.HashMap;

public class TabManager {

    private static TabManager instance = null;
    HashMap<Class<?>, Intent> intentsMap = new HashMap<Class<?>, Intent>();

    public synchronized static TabManager getInstance() {
        if (instance == null) {
            instance = new TabManager();
        }
        return instance;
    }

    private void cacheIntent(Class<?> cls, Intent intent) {
        // Cache the intent
        intentsMap.put(cls, intent);
    }

    public Intent getIntent(Context packageContext, Class<?> cls) {
        Intent intent = intentsMap.get(cls);
        if (intent == null) {
            // If not yet cached intent, create a new one
            intent = new Intent(packageContext, cls);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);

            // Cache the intent
            cacheIntent(cls, intent);
        }
        return intent;
    }
}

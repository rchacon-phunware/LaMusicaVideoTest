package com.lamusica.lamusicavideo;

import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by Raul on 2/3/16.
 */
public class Application extends android.app.Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
    }

}

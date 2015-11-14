package com.example.rzhu.paymenttest;

import android.app.Application;
import android.content.Context;

/**
 * Created by rzhu on 11/12/2015.
 */
public class MyApp extends Application {
    private MyAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerMyAppComponent.builder()
                .myModule(new MyModule(this))
                .build();
    }

    public static MyAppComponent getComponent(Context context) {
        return ((MyApp) context.getApplicationContext()).component;
    }
}

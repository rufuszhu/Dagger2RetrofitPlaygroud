package com.example.rzhu.paymenttest;

import android.app.Application;

import com.example.rzhu.paymenttest.dagger.PerApp;
import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import dagger.Component;

/**
 * Created by rzhu on 11/12/2015.
 */
@PerApp
@Component(
        modules = {
                MyModule.class,
                DataModule.class,
                NetworkModule.class
        }
)

public interface MyAppComponent {
    Application application();

    Gson gson();

    OkHttpClient okHttpClient();
}
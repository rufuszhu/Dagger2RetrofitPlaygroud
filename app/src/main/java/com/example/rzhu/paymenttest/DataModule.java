package com.example.rzhu.paymenttest;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.rzhu.paymenttest.dagger.PerApp;
import com.google.gson.Gson;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

  static final String PREFS_DEFAULT = "nowdothis";

  @Provides
  @PerApp
  SharedPreferences provideSharedPrefs(Application app) {
    return app.getSharedPreferences(PREFS_DEFAULT, Context.MODE_PRIVATE);
  }

  @Provides
  @PerApp Gson provideGson() {
    return new Gson();
  }
}

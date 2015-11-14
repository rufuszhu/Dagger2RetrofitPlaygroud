package com.example.rzhu.paymenttest;

import android.app.Application;

import com.example.rzhu.paymenttest.dagger.PerApp;

import dagger.Module;
import dagger.Provides;

@Module
public class MyModule {

  final MyApp app;

  public MyModule(MyApp app) {
    this.app = app;
  }

  @Provides
  @PerApp
  MyApp provideNowDoThisApp() {
    return app;
  }

  @Provides
  @PerApp Application provideApplication(MyApp app) {
    return app;
  }

}

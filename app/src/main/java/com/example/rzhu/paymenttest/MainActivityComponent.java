package com.example.rzhu.paymenttest;

import com.example.rzhu.paymenttest.dagger.PerMainActivity;

import dagger.Component;
import retrofit.Retrofit;

@PerMainActivity
@Component(
        dependencies = MyAppComponent.class,
        modules = {
                NetworkModule.class
        }
)

public interface MainActivityComponent {
    Retrofit getRetrofit();
    void inject(MainActivity mainActivity);
}

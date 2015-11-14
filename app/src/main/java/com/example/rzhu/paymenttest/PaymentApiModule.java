package com.example.rzhu.paymenttest;

import com.example.rzhu.paymenttest.dagger.PerApp;
import com.example.rzhu.paymenttest.dagger.PerMainActivity;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module
public class PaymentApiModule {
    @Provides
    @PerMainActivity
    PaymentApi providePaymentApi(Retrofit retrofit) {
        return retrofit.create(PaymentApi.class);
    }
}

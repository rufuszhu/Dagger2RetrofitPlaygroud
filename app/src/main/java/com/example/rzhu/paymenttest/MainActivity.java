package com.example.rzhu.paymenttest;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import javax.inject.Inject;

import retrofit.*;
import retrofit.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    WebView mWebView;

    @Inject
    PaymentApi service;

    Context context;
    String timeStampID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainActivityComponent component = DaggerMainActivityComponent.builder().myAppComponent(MyApp.getComponent(this))
                .paymentApiModule(new PaymentApiModule())
                .build();
        component.inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mWebView = (WebView) findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(mWebView, url);
                Log.e(TAG, "onPageFinished");
                callCheckToken();
            }
        });


        callOTK();
    }

    private void callCheckToken() {
        Call<ResponseBody> call = service.CheckTokenRequest("1E46C6F2CE62560F338FEBFB8756AE65CCCB249444B8CBF9EDA2412B85DF5FEA9E52B850126F076995",
                timeStampID,
                "QUERY_PAYMENT",
                "CREDIT_CARD",
                "true");

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response, Retrofit retrofit) {
                try {
                    String[] arr = response.body().string().split("&");
                    for (String a : arr) {
                        if (!TextUtils.isEmpty(a)) {
                            String[] pair = a.split("=");
                            if (pair.length > 1) {
                                if (pair[0].equalsIgnoreCase("payer_identifier")) {
                                    Toast.makeText(context, pair[1], Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }


    private void callOTK() {
        timeStampID = String.valueOf(System.currentTimeMillis() / 1000);

        Call<OTKResponse> call = service.OTKRequest("1E46C6F2CE62560F338FEBFB8756AE65CCCB249444B8CBF9EDA2412B85DF5FEA9E52B850126F076995",
                "13",
                "CREDIT_CARD",
                "true",
                "AUTH",
                "0.01",
                "#f5f5f5;",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                timeStampID,
                "VISA,MC,AMEX,DISCOVER",
                "false",
                "true",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "false",
                "Register Card");

        call.enqueue(new Callback<OTKResponse>() {
            @Override
            public void onResponse(Response<OTKResponse> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    OTKResponse otk = response.body();
                    Log.e(TAG, otk.actionUrl + "\n" + otk.sealedSetupParameters);
                    mWebView.loadUrl(otk.actionUrl + otk.sealedSetupParameters);
                } else {
                    int statusCode = response.code();

                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.learn.maksymgromov.learnui.Model.Network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class MyRequest {

    private static String stringResponse;

    public static String getRequest(String baseUrl, String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build();

        retrofit.callFactory().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("GET", "FAIL");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("GET", "OK");
                stringResponse = response.body().string();
            }
        });

        return stringResponse;
    }

}

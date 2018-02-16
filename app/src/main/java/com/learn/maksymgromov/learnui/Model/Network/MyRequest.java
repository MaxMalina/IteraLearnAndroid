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

    private  static  final String url = "http://172.16.33.183:3000";

    private MyCallBack callBack;
    private Retrofit retrofit;

    public MyRequest(MyCallBack myCallBack) {
        this.callBack = myCallBack;
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
    }

    public void getRequest() {
        Request request = new Request.Builder().url(url + "/cars/").build();
        retrofit.callFactory().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("GET", "FAIL");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("GET", "OK");
                callBack.processResponce(response.body().string());
            }
        });
    }

    public interface MyCallBack {
        String processResponce(String response);
    }

}

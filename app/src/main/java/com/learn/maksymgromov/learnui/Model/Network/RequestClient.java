package com.learn.maksymgromov.learnui.Model.Network;

import android.util.Log;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class RequestClient {

    private  static  final String url = "http://172.16.33.176:3000";

    private Retrofit retrofit;

    public RequestClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .build();
    }

    public String getRequest() {
        Request request = new Request.Builder().url(url + "/cars/").build();
        try {
            Response response = retrofit.callFactory().newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            Log.e("MYREQUEST", "Unable connect to server");
        }
        return "";
    }
}

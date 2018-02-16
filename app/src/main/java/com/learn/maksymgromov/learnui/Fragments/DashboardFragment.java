package com.learn.maksymgromov.learnui.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learn.maksymgromov.learnui.Model.Network.MyRequest;
import com.learn.maksymgromov.learnui.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardFragment extends Fragment implements MyRequest.MyCallBack{
    @BindView(R.id.response) TextView mTextView;
    private MyRequest myRequest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container,
                false);

        ButterKnife.bind(this, view);
        myRequest = new MyRequest(this);
        myRequest.getRequest();

        //new MyTask().execute();

        return view;
    }

    @Override
    public String processResponce(String response) {
        try {
            JSONArray array = new JSONArray(response);
            final JSONObject obj = (JSONObject) array.get(0);
            final String text = obj.getString("model");
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTextView.setText(text);
                }
            });
            return obj.getString("model");
        } catch (JSONException e) {
            Log.e("TAG", "Unable to parse json");
        }
        return "";
    }

//    class MyTask extends AsyncTask<String, Integer, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            myRequest.getRequest();
//            return "OK";
//        }
//
//        @Override
//        protected void onPostExecute(String string) {
//            mTextView.setText(responseText);
//        }
//    }
}

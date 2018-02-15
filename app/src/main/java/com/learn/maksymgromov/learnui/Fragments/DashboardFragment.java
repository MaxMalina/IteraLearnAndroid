package com.learn.maksymgromov.learnui.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learn.maksymgromov.learnui.Model.Network.MyRequest;
import com.learn.maksymgromov.learnui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardFragment extends Fragment {
    @BindView(R.id.response) TextView mTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container,
                false);

        ButterKnife.bind(this, view);

        new MyTask().execute("http://172.16.33.183:3000/", "http://172.16.33.183:3000/cars/");

        return view;
    }

    class MyTask extends AsyncTask<String, Integer, String> {
        private String responseText;

        @Override
        protected String doInBackground(String... strings) {
            responseText = MyRequest.getRequest(strings[0], strings[1]);
            return "OK";
        }

        @Override
        protected void onPostExecute(String string) {
            mTextView.setText(responseText);
        }
    }
}

package com.learn.maksymgromov.learnui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeFragment extends Fragment{
    private RecyclerView mHomeRecyclerView;
    private HomeDataAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,
                false);
        mHomeRecyclerView = (RecyclerView) view
                .findViewById(R.id.home_recycler_view);
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager
                (getActivity()));


        //TODO: json file
        InputStream inputStream = getResources().openRawResource(R.raw.file);
        String jsonText = readTextFile(inputStream);

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        ArrayList<String>data = gson.fromJson(jsonText, ArrayList.class);

        mAdapter = new HomeDataAdapter(data);
        mHomeRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private class HomeDataHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;

        public HomeDataHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView;
        }
    }

    private class HomeDataAdapter extends RecyclerView.Adapter<HomeDataHolder> {
        private ArrayList<String> mDataList;

        public HomeDataAdapter(ArrayList<String> crimes) {
            mDataList = crimes;
        }

        @Override
        public HomeDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new HomeDataHolder(view);
        }

        @Override
        public void onBindViewHolder(HomeDataHolder holder, int position) {
            String data = mDataList.get(position);
            holder.mTextView.setText(data);
        }

        @Override
        public int getItemCount() {
            return mDataList.size();
        }
    }

    public String readTextFile(InputStream inputStream) {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        String x;
        StringBuilder total = new StringBuilder();
        try {
            x = r.readLine();

            while(x!= null){
                total.append(x);
                x = r.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }
}

package com.learn.maksymgromov.learnui.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learn.maksymgromov.learnui.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.learn.maksymgromov.learnui.Adapters.HomeDataAdapter;

public class HomeFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView mHomeRecyclerView = view.findViewById(R.id.home_recycler_view);
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        InputStream inputStream = getResources().openRawResource(R.raw.file);
        String jsonText = readTextFile(inputStream);
        ArrayList<String>data = parseJsonStringToArrayListString(jsonText);

        HomeDataAdapter mAdapter = new HomeDataAdapter(data);
        mHomeRecyclerView.setAdapter(mAdapter);

        return view;
    }

    private String readTextFile(InputStream inputStream) {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        String x;
        StringBuilder total = new StringBuilder();
        try {
            x = r.readLine();
            while(x!= null) {
                total.append(x);
                x = r.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }

    private ArrayList<String> parseJsonStringToArrayListString(String jsonText) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        return gson.fromJson(jsonText, ArrayList.class);
    }
}

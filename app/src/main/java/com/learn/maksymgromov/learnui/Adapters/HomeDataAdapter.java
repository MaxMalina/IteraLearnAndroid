package com.learn.maksymgromov.learnui.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.maksymgromov.learnui.Holders.HomeDataHolder;

import java.util.ArrayList;

public class HomeDataAdapter extends RecyclerView.Adapter<HomeDataHolder> {
    private ArrayList<String> mDataList;

    public HomeDataAdapter(ArrayList<String> data) {
        mDataList = data;
    }

    @Override
    public HomeDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
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

package com.learn.maksymgromov.learnui.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class HomeDataHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;

    public HomeDataHolder(View itemView) {
        super(itemView);

        mTextView = (TextView) itemView;
    }
}
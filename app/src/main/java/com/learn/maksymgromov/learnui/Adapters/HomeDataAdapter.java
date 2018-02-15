package com.learn.maksymgromov.learnui.Adapters;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learn.maksymgromov.learnui.FragmentFactory;
import com.learn.maksymgromov.learnui.Model.Car;
import com.learn.maksymgromov.learnui.R;

import java.util.ArrayList;
import java.util.List;

public class HomeDataAdapter extends RecyclerView.Adapter<HomeDataAdapter.HomeDataHolder> {
    private List<Car> mDataList;

    public HomeDataAdapter(ArrayList<Car> data) {
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
        Car car = mDataList.get(position);
        holder.bindCar(car);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class HomeDataHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private Car mCar;
        private TextView mTextView;

        public HomeDataHolder(View itemView) {
            super(itemView);

            mTextView = (TextView) itemView;
            mTextView.setOnClickListener(this);
            mTextView.setOnLongClickListener(this);
        }

        private void bindCar(Car car) {
            mCar = car;
            mTextView.setText(mCar.getModel());
        }

        @Override
        public void onClick(View v) {
            AppCompatActivity activity = (AppCompatActivity)v.getContext();

            Bundle args = new Bundle();
            args.putSerializable("CAR", mCar);

            Fragment fragment = FragmentFactory.newInstance("INFO");
            fragment.setArguments(args);

            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack("fragBack")
                    .commit();
        }

        @Override
        public boolean onLongClick(View v) {
            v.setBackgroundColor(Color.CYAN);
            return false;
        }
    }
}

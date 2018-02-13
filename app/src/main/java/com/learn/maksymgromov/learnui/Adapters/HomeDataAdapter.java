package com.learn.maksymgromov.learnui.Adapters;

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
    private Car currentCar;


    public HomeDataAdapter(ArrayList<Car> data) {
        mDataList = data;
    }

    @Override
    public HomeDataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(android.R.layout.simple_list_item_1, parent, false);

        return new HomeDataHolder(view, new HomeDataHolder.IMyViewHolderClicks() {
            public void onCarModel(View caller) {
                AppCompatActivity activity = (AppCompatActivity)caller.getContext();
                android.support.v4.app.FragmentManager fm = activity.getSupportFragmentManager();

                Bundle args = new Bundle();

                args.putSerializable("CAR", currentCar);
                Fragment fragment = FragmentFactory.newInstance("INFO");

                fragment.setArguments(args);

                fm.beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .addToBackStack("fragBack")
                        .commit();
            }
        });
    }

    @Override
    public void onBindViewHolder(HomeDataHolder holder, int position) {
        currentCar = mDataList.get(position);
        holder.mTextView.setText(currentCar.getModel());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public static class HomeDataHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        private IMyViewHolderClicks mListener;

        public HomeDataHolder(View itemView, IMyViewHolderClicks listener) {
            super(itemView);

            mTextView = (TextView) itemView;
            mListener = listener;
            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof TextView){
                mListener.onCarModel(v);
            }
        }

        public interface IMyViewHolderClicks {
            void onCarModel(View caller);
        }
    }
}

package com.learn.maksymgromov.learnui.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.maksymgromov.learnui.Model.Car;
import com.learn.maksymgromov.learnui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardHolder> {
    private List<Car> mCars;

    public DashboardAdapter (ArrayList<Car> data) {
        mCars = data;
    }

    @Override
    public DashboardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.list_item_car, parent, false);

        return new DashboardHolder(view);
    }

    @Override
    public void onBindViewHolder(DashboardHolder holder, int position) {
        holder.bindCar(mCars.get(position));
    }

    @Override
    public int getItemCount() {
        return mCars.size();
    }

    public class DashboardHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.car_model) TextView mCarModelView;
        @BindView(R.id.car_series) TextView mCarSeriesView;
        @BindView(R.id.car_photo) ImageView mCarPhotoView;

        public DashboardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindCar(Car car) {
            mCarModelView.setText(car.getModel());
            mCarSeriesView.setText(car.getSeries());

            Picasso.with(mCarPhotoView.getContext()).load(car.getPhotoLink()).into(mCarPhotoView);
        }

    }
}

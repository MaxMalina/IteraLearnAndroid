package com.learn.maksymgromov.learnui.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learn.maksymgromov.learnui.Model.Car;
import com.learn.maksymgromov.learnui.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoFragment extends Fragment {
    @BindView(R.id.vin) TextView mVin;
    @BindView(R.id.style) TextView mStyle;
    @BindView(R.id.model) TextView mModel;
    @BindView(R.id.series) TextView mSeries;
    @BindView(R.id.engineLitres) TextView mEngineLiters;
    @BindView(R.id.fuelType) TextView mFuelType;
    @BindView(R.id.driveLineType) TextView mDriveLineType;
    @BindView(R.id.color) TextView mColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container,
                false);

        ButterKnife.bind(this, view);

        if(getArguments()!= null) {
            Car car = (Car) getArguments().getSerializable("CAR");

            mVin.setText(car.getVin());
            mStyle.setText(car.getStyle().toString());
            mModel.setText(car.getModel());
            mSeries.setText(car.getSeries());
            mEngineLiters.setText(String.valueOf(car.getEngineLiters()));
            mFuelType.setText(car.getFuelType().toString());
            mDriveLineType.setText(car.getDriveLineType().toString());
            mColor.setText(car.getColor());
        }

        return view;
    }
}

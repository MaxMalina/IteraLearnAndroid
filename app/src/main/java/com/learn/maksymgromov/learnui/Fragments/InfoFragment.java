package com.learn.maksymgromov.learnui.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.learn.maksymgromov.learnui.Model.Car;
import com.learn.maksymgromov.learnui.R;

public class InfoFragment extends Fragment {
    private TextView mVin;
    private TextView mStyle;
    private TextView mModel;
    private TextView mSeries;
    private TextView mEngineLiters;
    private TextView mFuelType;
    private TextView mDriveLineType;
    private TextView mColor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container,
                false);

        if(getArguments()!= null)
        {
            Car car = (Car) getArguments().getSerializable("CAR");

            mVin = view.findViewById(R.id.vin);
            mStyle = view.findViewById(R.id.style);
            mModel = view.findViewById(R.id.model);
            mSeries = view.findViewById(R.id.series);
            mEngineLiters = view.findViewById(R.id.engineLitres);
            mFuelType = view.findViewById(R.id.fuelType);
            mDriveLineType = view.findViewById(R.id.driveLineType);
            mColor = view.findViewById(R.id.color);

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

package com.learn.maksymgromov.learnui;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.learn.maksymgromov.learnui.Model.Car;
import com.learn.maksymgromov.learnui.Model.CarAttributes.BodyStyle;
import com.learn.maksymgromov.learnui.Model.CarAttributes.DriveLineType;
import com.learn.maksymgromov.learnui.Model.CarAttributes.FuelType;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddDialog extends Dialog implements View.OnClickListener {
    @BindView(R.id.vin) EditText editTextVin;
    @BindView(R.id.style) EditText editTextStyle;
    @BindView(R.id.model) EditText editTextModel;
    @BindView(R.id.series) EditText editTextSeries;
    @BindView(R.id.engineLitres) EditText editTextEngineLiters;
    @BindView(R.id.fuelType) EditText editTextFuelType;
    @BindView(R.id.driveLineType) EditText editTextDriveLineType;
    @BindView(R.id.color) EditText editTextColor;
    @BindView(R.id.photoLink) EditText editTextPhotoLink;

    @BindView(R.id.ok) Button buttonOk;

    private OkCallBack okCallBack;


    public AddDialog(@NonNull Context context, OkCallBack callBack) {
        super(context);

        this.setContentView(R.layout.add_dialog);
        ButterKnife.bind(this);

        okCallBack = callBack;
        buttonOk.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Car car = new Car(
                editTextVin.getText().toString(),
                BodyStyle.valueOf(editTextStyle.getText().toString()),
                editTextModel.getText().toString(),
                editTextSeries.getText().toString(),
                Double.valueOf(editTextEngineLiters.getText().toString()),
                FuelType.valueOf(editTextFuelType.getText().toString()),
                DriveLineType.valueOf(editTextDriveLineType.getText().toString()),
                editTextColor.getText().toString(),
                editTextPhotoLink.getText().toString()
        );

        okCallBack.onOK(car);
        dismiss();
    }

    public interface OkCallBack {
        void onOK(Car car);
    }
}

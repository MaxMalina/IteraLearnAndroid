package com.learn.maksymgromov.learnui.Model;

import android.database.Cursor;

import com.google.gson.internal.LinkedTreeMap;
import com.learn.maksymgromov.learnui.Model.CarAttributes.BodyStyle;
import com.learn.maksymgromov.learnui.Model.CarAttributes.DriveLineType;
import com.learn.maksymgromov.learnui.Model.CarAttributes.FuelType;

import java.io.Serializable;
import java.net.URL;

public class Car implements Serializable {
    private String vin;
    private BodyStyle style;
    private String model;
    private String series;
    private double engineLiters;
    private FuelType fuelType;
    private DriveLineType driveLineType;
    private String color;
    private String photoLink;

    private Car() {}

    public Car(String vin, BodyStyle style, String model, String series, double engineLiters, FuelType fuelType, DriveLineType driveLineType, String color, String photoLink) {
        this.vin = vin;
        this.style = style;
        this.model = model;
        this.series = series;
        this.engineLiters = engineLiters;
        this.fuelType = fuelType;
        this.driveLineType = driveLineType;
        this.color = color;
        this.photoLink = photoLink;
    }

    public static Car convertToCar(LinkedTreeMap<String, String> linkedTreeMap) {
        Car car = new Car();

        car.setVin(linkedTreeMap.get("vin"));
        car.setStyle(BodyStyle.valueOf(linkedTreeMap.get("style")));
        car.setModel(linkedTreeMap.get("model"));
        car.setSeries(linkedTreeMap.get("series"));
        car.setEngineLiters((double) ((Object) linkedTreeMap.get("engineLiters")));
        car.setFuelType(FuelType.valueOf(linkedTreeMap.get("fuelType")));
        car.setDriveLineType(DriveLineType.valueOf(linkedTreeMap.get("driveLineType")));
        car.setColor(linkedTreeMap.get("color"));
        car.setPhotoLink(linkedTreeMap.get("photoLink"));

        return car;
    }

    public static Car convertToCar(Cursor cursor) {
        Car car = new Car();

        car.setVin(cursor.getString(0));
        car.setStyle(BodyStyle.valueOf(cursor.getString(1)));
        car.setModel(cursor.getString(2));
        car.setSeries(cursor.getString(3));
        car.setEngineLiters(cursor.getDouble(4));
        car.setFuelType(FuelType.valueOf(cursor.getString(5)));
        car.setDriveLineType(DriveLineType.valueOf(cursor.getString(6)));
        car.setColor(cursor.getString(7));
        car.setPhotoLink(cursor.getString(8));

        return car;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public BodyStyle getStyle() {
        return style;
    }

    public void setStyle(BodyStyle style) {
        this.style = style;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public double getEngineLiters() {
        return engineLiters;
    }

    public void setEngineLiters(double engineLiters) {
        this.engineLiters = engineLiters;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public DriveLineType getDriveLineType() {
        return driveLineType;
    }

    public void setDriveLineType(DriveLineType driveLineType) {
        this.driveLineType = driveLineType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

}

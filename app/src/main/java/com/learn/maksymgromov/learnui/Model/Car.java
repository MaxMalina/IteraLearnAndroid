package com.learn.maksymgromov.learnui.Model;

public class Car {
    private String vin;
    private BodyStyle style;
    private String model;
    private String series;
    private double engineLiters;
    private FuelType fuelType;
    private DriveLineType driveLineType;
    private String color;

    public Car(String vin, BodyStyle style, String model, String series, double engineLiters, FuelType fuelType, DriveLineType driveLineType, String color) {
        this.vin = vin;
        this.style = style;
        this.model = model;
        this.series = series;
        this.engineLiters = engineLiters;
        this.fuelType = fuelType;
        this.driveLineType = driveLineType;
        this.color = color;
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

}

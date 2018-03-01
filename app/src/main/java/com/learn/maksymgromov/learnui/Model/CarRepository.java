package com.learn.maksymgromov.learnui.Model;

import android.content.Context;

import com.learn.maksymgromov.learnui.Model.Network.RequestClient;

import java.util.List;

public class CarRepository implements Repository<Car> {

    private CarDataAccessObject carDataAccessObject;

    private RequestClient requestClient;

    public CarRepository(Context context) {
        requestClient = new RequestClient();
        carDataAccessObject = new CarDataAccessObject(context);
        carDataAccessObject.open();
    }

    @Override
    public void add(Car item) {
        carDataAccessObject.addCar(item);
    }

    @Override
    public void remove(Car item) {
        carDataAccessObject.removeCar(item);
    }

    @Override
    public void update(Car item, Car newItem) {
        carDataAccessObject.updateCar(item, newItem);
    }

    @Override
    public List<Car> getAllItems() {
        return getAllCars();
    }

    public void open() {
        carDataAccessObject.open();
    }

    public void close() {
        carDataAccessObject.close();
    }

    private List<Car> getAllCars () {
        String json = requestClient.getRequest();
        return Utils.parseJsonStringToArrayListString(json);
    }
}

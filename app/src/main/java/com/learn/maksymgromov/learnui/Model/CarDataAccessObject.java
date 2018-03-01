package com.learn.maksymgromov.learnui.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CarDataAccessObject {
    private SQLiteDatabase database;
    private CarSQLiteHelper dbHelper;
    private String[] allColumns = {
            CarSQLiteHelper.COLUMN_ID,
            CarSQLiteHelper.COLUMN_VIN,
            CarSQLiteHelper.COLUMN_STYLE,
            CarSQLiteHelper.COLUMN_MODEL,
            CarSQLiteHelper.COLUMN_SERIES,
            CarSQLiteHelper.COLUMN_ENGINE_LITERS,
            CarSQLiteHelper.COLUMN_FUEL_TYPE,
            CarSQLiteHelper.COLUMN_DRIVE_LINE_TYPE,
            CarSQLiteHelper.COLUMN_COLOR,
            CarSQLiteHelper.COLUMN_PHOTO_LINK
    };

    public CarDataAccessObject(Context context) {
        dbHelper = new CarSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addCar(Car car) {
        ContentValues values = new ContentValues();

        values.put(CarSQLiteHelper.COLUMN_VIN, car.getVin());
        values.put(CarSQLiteHelper.COLUMN_STYLE, car.getStyle().toString());
        values.put(CarSQLiteHelper.COLUMN_MODEL, car.getModel());
        values.put(CarSQLiteHelper.COLUMN_SERIES, car.getSeries());
        values.put(CarSQLiteHelper.COLUMN_ENGINE_LITERS, car.getEngineLiters());
        values.put(CarSQLiteHelper.COLUMN_FUEL_TYPE, car.getFuelType().toString());
        values.put(CarSQLiteHelper.COLUMN_DRIVE_LINE_TYPE, car.getDriveLineType().toString());
        values.put(CarSQLiteHelper.COLUMN_COLOR, car.getColor());
        values.put(CarSQLiteHelper.COLUMN_PHOTO_LINK, car.getPhotoLink());

        long insertId = database.insert(CarSQLiteHelper.TABLE_CARS, null, values);

        Cursor cursor = database.query(CarSQLiteHelper.TABLE_CARS,
                allColumns, CarSQLiteHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

    public void updateCar(Car car, Car newCar) {
        removeCar(car);
        addCar(newCar);
    }

    public void removeCar(Car car) {
        String vin = car.getVin();
        database.delete(CarSQLiteHelper.TABLE_CARS, CarSQLiteHelper.COLUMN_VIN
                + " = " + vin, null);
    }

    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<Car>();

        Cursor cursor = database.query(CarSQLiteHelper.TABLE_CARS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Car car = Car.convertToCar(cursor);
            cars.add(car);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return cars;
    }
}

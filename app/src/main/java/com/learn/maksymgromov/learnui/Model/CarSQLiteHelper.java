package com.learn.maksymgromov.learnui.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarSQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_CARS = "cars";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_VIN = "vin";
    public static final String COLUMN_STYLE = "style";
    public static final String COLUMN_MODEL = "model";
    public static final String COLUMN_SERIES = "series";
    public static final String COLUMN_ENGINE_LITERS = "engine_liters";
    public static final String COLUMN_FUEL_TYPE = "fuel_type";
    public static final String COLUMN_DRIVE_LINE_TYPE = "drive_line_type";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_PHOTO_LINK = "photo_link";

    private static final String DATABASE_NAME = "cars.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table "
            + TABLE_CARS + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_VIN + " text not null, "
            + COLUMN_STYLE + " text not null, "
            + COLUMN_MODEL + " text not null, "
            + COLUMN_SERIES + " text not null, "
            + COLUMN_ENGINE_LITERS + " real not null, "
            + COLUMN_FUEL_TYPE + " text not null, "
            + COLUMN_DRIVE_LINE_TYPE + " text not null, "
            + COLUMN_COLOR + " text not null, "
            + COLUMN_PHOTO_LINK + " text not null"
            + ");";

    public CarSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARS);
        onCreate(db);
    }
}

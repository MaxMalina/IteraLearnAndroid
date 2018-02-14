package com.learn.maksymgromov.learnui.Model;

import android.content.res.Resources;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Utils {
    public static ArrayList<Car> getCarsFromJsonRawFile(Resources resources, int fileId) {
        InputStream inputStream = resources.openRawResource(fileId);
        String jsonText = readTextFile(inputStream);

        return parseJsonStringToArrayListString(jsonText);
    }

    private static String readTextFile(InputStream inputStream) {
        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        String x;
        StringBuilder total = new StringBuilder();
        try {
            x = r.readLine();
            while(x!= null) {
                total.append(x);
                x = r.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }

    private static ArrayList<Car> parseJsonStringToArrayListString(String jsonText) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        ArrayList<LinkedTreeMap<String, String>>  linkedTreeMaps = gson.fromJson(jsonText, ArrayList.class);
        ArrayList<Car> cars = new ArrayList<>();

        for (LinkedTreeMap<String, String> linkedTreeMap : linkedTreeMaps) {
            cars.add(Car.convertToCar(linkedTreeMap));
        }

        return cars;
    }
}

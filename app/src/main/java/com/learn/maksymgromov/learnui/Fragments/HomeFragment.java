package com.learn.maksymgromov.learnui.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.maksymgromov.learnui.Model.Car;
import com.learn.maksymgromov.learnui.Model.Utils;
import com.learn.maksymgromov.learnui.R;

import java.util.ArrayList;

import com.learn.maksymgromov.learnui.Adapters.HomeDataAdapter;

public class HomeFragment extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView mHomeRecyclerView = view.findViewById(R.id.home_recycler_view);
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ArrayList<Car>data = Utils.getCarsFromJsonRawFile(getResources(), R.raw.file);

        HomeDataAdapter mAdapter = new HomeDataAdapter(data);
        mHomeRecyclerView.setAdapter(mAdapter);

        return view;
    }

}

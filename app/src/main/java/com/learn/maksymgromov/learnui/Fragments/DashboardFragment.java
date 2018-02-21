package com.learn.maksymgromov.learnui.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.learn.maksymgromov.learnui.Adapters.DashboardAdapter;
import com.learn.maksymgromov.learnui.Model.Car;
import com.learn.maksymgromov.learnui.Model.Network.RequestClient;
import com.learn.maksymgromov.learnui.Model.Utils;
import com.learn.maksymgromov.learnui.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    private RequestClient requestClient;
    private ArrayList<Car> mData;
    private DashboardAdapter mAdapter;

    @BindView(R.id.dashboard_recycler_view) RecyclerView mDashboardRecyclerView;
    @BindView(R.id.dashboard_layout) SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container,
                false);

        ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        mDashboardRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        requestClient = new RequestClient();
        new RequestTask().execute();

        return view;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);

        requestClient = new RequestClient();
        new RequestTask().execute();
    }

    class RequestTask extends AsyncTask<String, Integer, ArrayList<Car>>  {

        @Override
        protected ArrayList<Car> doInBackground(String... strings) {
            return Utils.parseJsonStringToArrayListString(requestClient.getRequest());
        }

        @Override
        protected void onPostExecute(ArrayList<Car> mData) {
            mAdapter = new DashboardAdapter(mData);
            mDashboardRecyclerView.setAdapter(mAdapter);

            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    public void beginSearch(String query) {
        mAdapter.getFilter().filter(query);
    }

}

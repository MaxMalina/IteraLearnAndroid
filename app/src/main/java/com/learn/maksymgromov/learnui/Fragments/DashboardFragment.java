package com.learn.maksymgromov.learnui.Fragments;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.learn.maksymgromov.learnui.Adapters.DashboardAdapter;
import com.learn.maksymgromov.learnui.AddDialog;
import com.learn.maksymgromov.learnui.ChangeDialog;
import com.learn.maksymgromov.learnui.Model.Car;
import com.learn.maksymgromov.learnui.Model.CarRepository;
import com.learn.maksymgromov.learnui.Model.Network.RequestClient;
import com.learn.maksymgromov.learnui.Model.Utils;
import com.learn.maksymgromov.learnui.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private List<Car> mData;
    private DashboardAdapter mAdapter;
    private CarRepository carRepository;

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

        carRepository = new CarRepository(getContext());

        new RequestTask().execute();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        carRepository.open();
    }

    @Override
    public void onPause() {
        super.onPause();
        carRepository.close();
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        new RequestTask().execute();
    }

    class RequestTask extends AsyncTask<String, Integer, List<Car>>  {

        @Override
        protected List<Car> doInBackground(String... strings) {
            return carRepository.getAllItems();
        }

        @Override
        protected void onPostExecute(List<Car> data) {
            mData = data;
            mAdapter = new DashboardAdapter(mData);
            mDashboardRecyclerView.setAdapter(mAdapter);

            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    public void beginSearch(String query) {
        mAdapter.getFilter().filter(query);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case DashboardAdapter.DashboardHolder.IDM_CHANGE:

                ChangeDialog changeDialog = new ChangeDialog(getContext(), (model, series) -> {
                    mData.get(item.getGroupId()).setModel(model);
                    mData.get(item.getGroupId()).setSeries(model);
                    mAdapter.setCars(mData);
                    mAdapter.notifyDataSetChanged();
                });

                changeDialog.show();

                return true;
            case DashboardAdapter.DashboardHolder.IDM_REMOVE:
                mData.remove(item.getGroupId());
                mAdapter.setCars(mData);
                mAdapter.notifyDataSetChanged();
                return true;

            case DashboardAdapter.DashboardHolder.IDM_ADD:

                AddDialog addDialog = new AddDialog(getContext(), car -> {
                    mData.add(car);
                    mAdapter.setCars(mData);
                    mAdapter.notifyDataSetChanged();
                    carRepository.add(car);
                });
                addDialog.show();
                return true;

            default: return super.onContextItemSelected(item);
        }
    }
}

package com.learn.maksymgromov.learnui.Adapters;

import android.app.Activity;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.learn.maksymgromov.learnui.Fragments.DashboardFragment;
import com.learn.maksymgromov.learnui.MainActivity;
import com.learn.maksymgromov.learnui.Model.Car;
import com.learn.maksymgromov.learnui.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardHolder> implements Filterable {
    private List<Car> mCars;
    private List<Car> mCarsListFiltered;

    public DashboardAdapter (List<Car> data) {
        mCars = data;
        mCarsListFiltered = data;
    }

    public void setCars(List<Car> cars) {
        mCars = cars;
        mCarsListFiltered = cars;
    }

    @Override
    public DashboardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater
                .inflate(R.layout.list_item_car, parent, false);

        return new DashboardHolder(view);
    }

    @Override
    public void onBindViewHolder(DashboardHolder holder, int position) {
        holder.bindCar(mCarsListFiltered.get(position));
    }

    @Override
    public int getItemCount() {
        return mCarsListFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String charString = constraint.toString();
                if (charString.isEmpty()) {
                    mCarsListFiltered = mCars;
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        mCarsListFiltered = mCars.stream()
                                            .filter(car -> car.getModel().toLowerCase().contains(charString))
                                            .collect(Collectors.toList());
                    } else {
                        List<Car> filteredList = new ArrayList<>();
                        for (Car car : mCars) {
                            if (car.getModel().toLowerCase().contains(charString)) {
                                filteredList.add(car);
                            }
                        }
                        mCarsListFiltered = filteredList;
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mCarsListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mCarsListFiltered = (ArrayList<Car>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class DashboardHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

        public static final int IDM_CHANGE = 101;
        public static final int IDM_REMOVE = 102;
        public static final int IDM_ADD = 103;

        @BindView(R.id.car_model) TextView mCarModelView;
        @BindView(R.id.car_series) TextView mCarSeriesView;
        @BindView(R.id.car_photo) ImageView mCarPhotoView;

        public DashboardHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bindCar(Car car) {
            mCarModelView.setText(car.getModel());
            mCarSeriesView.setText(car.getSeries());

            Picasso.with(mCarPhotoView.getContext()).load(car.getPhotoLink()).into(mCarPhotoView);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select The Action");
            menu.add(getAdapterPosition(), IDM_CHANGE, Menu.NONE, "Change");
            menu.add(getAdapterPosition(), IDM_REMOVE, Menu.NONE, "Remove");
            menu.add(getAdapterPosition(), IDM_ADD, Menu.NONE, "Add");
        }
    }
}

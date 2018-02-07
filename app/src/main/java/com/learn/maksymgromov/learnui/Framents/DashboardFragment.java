package com.learn.maksymgromov.learnui.Framents;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.learn.maksymgromov.learnui.R;

public class DashboardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container,
                false);
        LinearLayout mDashboardLayout = view.findViewById(R.id.dashboard_layout);


        return view;
    }
}

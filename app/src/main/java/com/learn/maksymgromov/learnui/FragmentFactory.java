package com.learn.maksymgromov.learnui;

import android.support.v4.app.Fragment;

import com.learn.maksymgromov.learnui.Framents.DashboardFragment;
import com.learn.maksymgromov.learnui.Framents.HomeFragment;

public class FragmentFactory {
    public static Fragment newInstance(String fragmentType) {
        if(fragmentType.equalsIgnoreCase("HOME")) {
            return new HomeFragment();
        } else if(fragmentType.equalsIgnoreCase("DASHBOARD")){
            return new DashboardFragment();
        } else {
            return new Fragment();
        }
    }
}


package com.learn.maksymgromov.learnui;

import android.support.v4.app.Fragment;

import com.learn.maksymgromov.learnui.Fragments.DashboardFragment;
import com.learn.maksymgromov.learnui.Fragments.HomeFragment;
import com.learn.maksymgromov.learnui.Fragments.InfoFragment;

public class FragmentFactory {
    public static Fragment newInstance(String fragmentType) {
        if(fragmentType.equalsIgnoreCase("HOME")) {
            return new HomeFragment();
        } else if(fragmentType.equalsIgnoreCase("DASHBOARD")) {
            return new DashboardFragment();
        } else if(fragmentType.equalsIgnoreCase("INFO")) {
            return new InfoFragment();
        } else {
            return new Fragment();
        }
    }
}


package com.learn.maksymgromov.learnui;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationManager {
    private static final float NORMAL_SIZE = 24;
    private static final float ACTIVE_SIZE = 31;

    private BottomNavigationViewEx mNavigation;

    public BottomNavigationManager(BottomNavigationViewEx bottomNavigationView) {
        mNavigation = bottomNavigationView;
    }

    public void changeIconSize(int positionToActive) {
        mNavigation.setIconSize(NORMAL_SIZE, NORMAL_SIZE);
        mNavigation.setIconSizeAt(positionToActive, ACTIVE_SIZE, ACTIVE_SIZE);
    }

    public void initBottomNavigationView() {
        mNavigation.getMenu().getItem(0).setIcon(R.drawable.loyalty_active);

        changeIconSize(0);

        mNavigation.enableAnimation(false);
        mNavigation.enableShiftingMode(false);
        mNavigation.enableItemShiftingMode(false);
        mNavigation.setTextVisibility(false);
        mNavigation.setItemIconTintList(null);

    }
}

package com.learn.maksymgromov.learnui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.learn.maksymgromov.learnui.Fragments.DashboardFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationViewEx.OnNavigationItemSelectedListener, DashboardFragment.SearchListener {

    @BindView(R.id.message) TextView mTextMessage;
    @BindView(R.id.navigation) BottomNavigationViewEx navigation;
    @BindView(R.id.search) SearchView mSearchView;

    private static final float NORMAL_SIZE = 24;
    private static final float ACTIVE_SIZE = 31;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initBottomNavigationViewEx();
        Fragment fragment = FragmentFactory.newInstance("HOME");

        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();

        mSearchView.setVisibility(View.INVISIBLE);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                DashboardFragment dashboardFragment = (DashboardFragment) getCurrentFragment();
                dashboardFragment.beginSearch(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                DashboardFragment dashboardFragment = (DashboardFragment) getCurrentFragment();
                dashboardFragment.beginSearch(newText);

                return false;
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                mSearchView.setVisibility(View.INVISIBLE);
                switchFragment(getResources().getString(R.string.title_home), getSupportFragmentManager());
                item.setIcon(R.drawable.loyalty_active);
                navigation.getMenu().getItem(1).setIcon(R.drawable.wallet_normal);
                navigation.getMenu().getItem(2).setIcon(R.drawable.more_actions_normal);

                changeIconsSize(0);
                return true;
            case R.id.navigation_dashboard:
                mSearchView.setVisibility(View.VISIBLE);
                switchFragment(getResources().getString(R.string.title_dashboard), getSupportFragmentManager());
                item.setIcon(R.drawable.wallet_active);
                navigation.getMenu().getItem(0).setIcon(R.drawable.loyalty_normal);
                navigation.getMenu().getItem(2).setIcon(R.drawable.more_actions_normal);

                changeIconsSize(1);
                return true;
            case R.id.navigation_notifications:
                mSearchView.setVisibility(View.INVISIBLE);
                switchFragment(getResources().getString(R.string.title_notifications), getSupportFragmentManager());
                item.setIcon(R.drawable.more_actions_active);
                navigation.getMenu().getItem(0).setIcon(R.drawable.loyalty_normal);
                navigation.getMenu().getItem(1).setIcon(R.drawable.wallet_normal);

                changeIconsSize(2);
                return true;
            default:
                return false;
        }
        //return false;
    }

    private void switchFragment(String itemTitle, FragmentManager fragmentManager) {
        mTextMessage.setText(itemTitle);

        Fragment fragment = FragmentFactory.newInstance(itemTitle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void initBottomNavigationViewEx() {
        navigation.getMenu().getItem(0).setIcon(R.drawable.loyalty_active);

        navigation.setIconSize(NORMAL_SIZE,NORMAL_SIZE);
        navigation.setIconSizeAt(0, ACTIVE_SIZE, ACTIVE_SIZE);

        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
        navigation.setTextVisibility(false);
        navigation.setItemIconTintList(null);

        navigation.setOnNavigationItemSelectedListener(this);
    }

    private void changeIconsSize(int positionToActive) {
        navigation.setIconSize(NORMAL_SIZE, NORMAL_SIZE);
        navigation.setIconSizeAt(positionToActive, ACTIVE_SIZE, ACTIVE_SIZE);
    }

    @Override
    public void onSearch() {

    }

    private Fragment getCurrentFragment() {
        Fragment result = null;
        for (Fragment f: getSupportFragmentManager().getFragments()) {
            if(f != null && f.isVisible()) {
                result = f;
            }
        }

        return  result;
    }
}

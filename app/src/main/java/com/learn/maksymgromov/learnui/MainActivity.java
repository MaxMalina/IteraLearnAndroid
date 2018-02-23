package com.learn.maksymgromov.learnui;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.learn.maksymgromov.learnui.Fragments.DashboardFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements BottomNavigationViewEx.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener, DrawerLayout.DrawerListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.message) TextView mTextMessage;
    @BindView(R.id.navigation) BottomNavigationViewEx navigation;
    @BindView(R.id.search) SearchView mSearchView;

    @BindView(R.id.drawer_layout) DrawerLayout mDrawerLayout;
    @BindView(R.id.my_toolbar) Toolbar toolbar;
    @BindView(R.id.nav_view) NavigationView mNavigationView;


    private BottomNavigationManager mNavigationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mNavigationManager = new BottomNavigationManager(navigation);

        mNavigationManager.initBottomNavigationView();
        navigation.setOnNavigationItemSelectedListener(this);

        Fragment fragment = FragmentFactory.newInstance("HOME");

        getSupportFragmentManager().beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();

        mSearchView.setVisibility(View.INVISIBLE);
        mSearchView.setOnQueryTextListener(this);

        setSupportActionBar(toolbar);

        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        mNavigationView.setItemIconTintList(null);
        mDrawerLayout.addDrawerListener(this);

        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.getMenu().getItem(0).setIcon(R.drawable.loyalty_active_without_dot);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.navigation_home:
                mSearchView.setVisibility(View.INVISIBLE);
                switchFragment(getResources().getString(R.string.title_home));
                navigation.getMenu().getItem(0).setIcon(R.drawable.loyalty_active);
                navigation.getMenu().getItem(1).setIcon(R.drawable.wallet_normal);
                navigation.getMenu().getItem(2).setIcon(R.drawable.more_actions_normal);

                mNavigationView.getMenu().getItem(0).setIcon(R.drawable.loyalty_active_without_dot);
                mNavigationView.getMenu().getItem(1).setIcon(R.drawable.wallet_normal);
                mNavigationView.getMenu().getItem(2).setIcon(R.drawable.more_actions_normal);
                //mNavigationManager.changeIconSize(0);
                return true;
            case R.id.navigation_dashboard:
                mSearchView.setVisibility(View.VISIBLE);
                switchFragment(getResources().getString(R.string.title_dashboard));
                navigation.getMenu().getItem(0).setIcon(R.drawable.loyalty_normal);
                navigation.getMenu().getItem(1).setIcon(R.drawable.wallet_active);
                navigation.getMenu().getItem(2).setIcon(R.drawable.more_actions_normal);


                mNavigationView.getMenu().getItem(0).setIcon(R.drawable.loyalty_normal);
                mNavigationView.getMenu().getItem(1).setIcon(R.drawable.wallet_active_without_dot);
                mNavigationView.getMenu().getItem(2).setIcon(R.drawable.more_actions_normal);
                //mNavigationManager.changeIconSize(1);
                return true;
            case R.id.navigation_notifications:
                mSearchView.setVisibility(View.INVISIBLE);
                switchFragment(getResources().getString(R.string.title_notifications));
                navigation.getMenu().getItem(0).setIcon(R.drawable.loyalty_normal);
                navigation.getMenu().getItem(1).setIcon(R.drawable.wallet_normal);
                navigation.getMenu().getItem(2).setIcon(R.drawable.more_actions_active);


                mNavigationView.getMenu().getItem(0).setIcon(R.drawable.loyalty_normal);
                mNavigationView.getMenu().getItem(1).setIcon(R.drawable.wallet_normal);
                mNavigationView.getMenu().getItem(2).setIcon(R.drawable.more_actions_active_without_dot);
                //mNavigationManager.changeIconSize(2);
                return true;
            default:
                return false;
        }
        //return false;
    }

    private void switchFragment(String itemTitle) {
        mTextMessage.setText(itemTitle);

        Fragment fragment = FragmentFactory.newInstance(itemTitle);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }



    private Fragment getCurrentFragment() {
        Fragment result = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = getSupportFragmentManager().getFragments().stream()
                    .filter(Fragment::isVisible)
                    .findFirst()
                    .get();
        } else {
            for (Fragment f : getSupportFragmentManager().getFragments()) {
                if (f != null && f.isVisible()) {
                    result = f;
                }
            }
        }

        return  result;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        makeSearch(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        makeSearch(newText);
        return false;
    }

    private void makeSearch(String query){
        DashboardFragment dashboardFragment = (DashboardFragment) getCurrentFragment();
        dashboardFragment.beginSearch(query);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        navigation.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        navigation.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        navigation.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}

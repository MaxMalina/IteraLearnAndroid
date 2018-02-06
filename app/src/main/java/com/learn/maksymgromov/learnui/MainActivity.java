package com.learn.maksymgromov.learnui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    fragment = new HomeFragment();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();

                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);

                    fm = getSupportFragmentManager();
                    fragment = new DashboardFragment();

                    fm.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    fm = getSupportFragmentManager();
                    fragment = new Fragment();
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new DashboardFragment();

        fm.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();
    }
}

package com.learn.maksymgromov.learnui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity implements BottomNavigationViewEx.OnNavigationItemSelectedListener {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationViewEx navigation = findViewById(R.id.navigation);
        ConfigBottomNavigationViewEx(navigation);

        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = FragmentFactory.newInstance("HOME");

        fm.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

        switch (item.getItemId()) {
            case R.id.navigation_home:
                SwitchFragment(getResources().getString(R.string.title_home), fm);
                return true;
            case R.id.navigation_dashboard:
                SwitchFragment(getResources().getString(R.string.title_dashboard), fm);
                return true;
            case R.id.navigation_notifications:
                SwitchFragment(getResources().getString(R.string.title_notifications), fm);
                return true;
        }
        return false;
    }

    private void SwitchFragment(String itemTitle, FragmentManager fragmentManager) {
        mTextMessage.setText(itemTitle);

        Fragment fragment = FragmentFactory.newInstance(itemTitle);
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void ConfigBottomNavigationViewEx(BottomNavigationViewEx navigation){
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.enableAnimation(false);
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
        navigation.setTextVisibility(false);
    }
}

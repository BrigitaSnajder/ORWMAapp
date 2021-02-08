package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setUpPager();
    }

    private void initViews(){
        mViewPager = findViewById(R.id.viewPager);
    }

    private void setUpPager(){
        PagerAdapter pagerAdapter = new SlideAdapter(getSupportFragmentManager(), mViewPager);
        mViewPager.setAdapter(pagerAdapter);
    }
}
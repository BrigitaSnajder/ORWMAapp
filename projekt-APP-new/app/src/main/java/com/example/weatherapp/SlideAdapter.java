package com.example.weatherapp;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.weatherapp.restapi.ResponseForecast;

import java.util.Locale;

public class SlideAdapter extends FragmentStatePagerAdapter {

    private static final int NUM_PAGES = 2;
    private static final String BASE_NAME = "Tab #%d";

    private Fragment mainFragment;
    private ForecastFragment forecastFragment;
    private ViewPager viewPager;

    public SlideAdapter(FragmentManager fm, ViewPager viewPager) {
        super(fm);
        mainFragment = new MainFragment(viewPager, this);
        forecastFragment = new ForecastFragment(viewPager);
        this.viewPager = viewPager;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return mainFragment;
            default:
                return forecastFragment;

        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return String.format(Locale.getDefault(), BASE_NAME, position + 1);
    }

    public int getCount(){
        return NUM_PAGES;
    }

    public void SetForecast(ResponseForecast body) {
        forecastFragment.setData(body);
    }
}

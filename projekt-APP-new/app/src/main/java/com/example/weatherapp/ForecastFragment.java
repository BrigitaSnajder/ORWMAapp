package com.example.weatherapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.weatherapp.recycler.RecyclerAdapter;
import com.example.weatherapp.restapi.Data;
import com.example.weatherapp.restapi.Hourly;
import com.example.weatherapp.restapi.Main;
import com.example.weatherapp.restapi.OpenWeatherHandler;
import com.example.weatherapp.restapi.ResponseForecast;
import com.example.weatherapp.restapi.ResponseToday;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForecastFragment extends Fragment {

    private TextView city;
    private Button back;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ViewPager viewPager;
    private String query;


    public ForecastFragment(ViewPager viewPager){
        this.viewPager = viewPager;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.forecast_fagment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        InitViews(view);
    }


    private void InitViews(View view) {
        city = view.findViewById(R.id.tvTown);
        back = view.findViewById(R.id.btnback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(0, true);
            }
        });
        recyclerView = view.findViewById(R.id.recyclerViewFrag);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);

    }

    public void setData(ResponseForecast body){
        recyclerAdapter.addData(body.getList());
        city.setText(body.getCity().getName());
    }
}

package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherapp.recycler.RecyclerAdapter;
import com.example.weatherapp.restapi.City;
import com.example.weatherapp.restapi.Data;
import com.example.weatherapp.restapi.Hourly;
import com.example.weatherapp.restapi.Main;
import com.example.weatherapp.restapi.OpenWeatherHandler;
import com.example.weatherapp.restapi.ResponseForecast;
import com.example.weatherapp.restapi.ResponseToday;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    private TextView day;
    private TextView temp;
    private TextView city;
    private TextView main;
    private TextView humidity;
    private TextView realFeel;

    private EditText enterCityName;
    private Button searchButton;
    private Button next;

    private SlideAdapter slideAdapter;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ImageView ivBackground;

    private String query;
    private View view;
    private ViewPager viewPager;

    public MainFragment(ViewPager viewPager, SlideAdapter slideAdapter){
        this.viewPager = viewPager;
        this.slideAdapter = slideAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        InitViews();
        InitHomeScreen();
    }

    private void setBg(Data data){
        ivBackground = view.findViewById(R.id.ivBackground);
        ivBackground.setAlpha(125);
        String icon=String.format("%s", data.getWeather().get(0).getIcon());
        if (icon.equals("01d")){
            Picasso.get().load(R.drawable.oneday).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("01n")){
            Picasso.get().load(R.drawable.onenight).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("02d")){
            Picasso.get().load(R.drawable.twoday).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("02n")){
            Picasso.get().load(R.drawable.twonight).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("03d")){
            Picasso.get().load(R.drawable.threeday).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("03n")){
            Picasso.get().load(R.drawable.threenight).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("04d")){
            Picasso.get().load(R.drawable.fourday).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("04n")){
            Picasso.get().load(R.drawable.fournight).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("09d")||icon.equals("09n")||icon.equals("10d")||icon.equals("10n")){
            Picasso.get().load(R.drawable.nine).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("11d")){
            Picasso.get().load(R.drawable.elevenday).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("11n")){
            Picasso.get().load(R.drawable.elevennight).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("13d")||icon.equals("13n")){
            Picasso.get().load(R.drawable.thirteen).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else if (icon.equals("50d")||icon.equals("50n")){
            Picasso.get().load(R.drawable.fifty).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
        else {
            Picasso.get().load(R.drawable.threeday).resize(ivBackground.getMeasuredWidth(),ivBackground.getMeasuredHeight()).centerCrop().into(ivBackground);
        }
    }

    private void RetrofitTest() {
        Call<ResponseForecast> call = OpenWeatherHandler.ForecastWeather(query);
        call.enqueue(new Callback<ResponseForecast>() {
            @Override
            public void onResponse(Call<ResponseForecast> call, Response<ResponseForecast> response) {

                ResponseForecast body = response.body();
                if(!response.isSuccessful()){
                    Toast.makeText(getContext(), String.format("Can't find city: %s.\nPlease enter valid city name!!!", query), Toast.LENGTH_LONG).show();
                    return;
                }
                Call<ResponseToday> call2 = OpenWeatherHandler.TodayWeather(body.getCity().getCoord().getLat(), body.getCity().getCoord().getLon());
                call2.enqueue(new Callback<ResponseToday>() {
                    @Override
                    public void onResponse(Call<ResponseToday> call, Response<ResponseToday> response){
                        ResponseToday body2 =response.body();
                        if(!response.isSuccessful()){
                            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
                        }
                        List<Data> dataList = new ArrayList<>();
                        List<Hourly> hourlyList = body2.getHourly();
                        for(Hourly hourly : hourlyList.subList(0, 24)){
                            dataList.add(new Data(hourly.getDt(), new Main(hourly.getTemp(), hourly.getFeels_like(), hourly.getHumidity()), hourly.getWeather()));
                        }
                        SetHomeScreen(dataList.get(0), body.getCity());
                        recyclerAdapter.addData(dataList);
                        slideAdapter.SetForecast(body);
                }
                    @Override
                    public void onFailure(Call<ResponseToday> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<ResponseForecast> call, Throwable t) {

            }
        });
    }

    private void SetHomeScreen(Data data, City city) {
        DateFormat formatter = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        day.setText(formatter.format(new Date(data.getDt()*1000)));
        temp.setText(String.format("%s°C",Integer.toString(Math.round(data.getMain().getTemp()))));
        this.city.setText(String.format("in %s", city.getName()));
        main.setText(data.getWeather().get(0).getMain());
        humidity.setText(String.format("Humidity: %s", Integer.toString(data.getMain().getHumidity())));
        realFeel.setText(String.format("Real-feel: %s", Integer.toString(Math.round(data.getMain().getFeels_like()))));
        setBg(data);
    }

    private void InitViews() {
        next = view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                viewPager.setCurrentItem(1, true);
            }
        });
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerAdapter = new RecyclerAdapter();
        recyclerView.setAdapter(recyclerAdapter);
        enterCityName = view.findViewById(R.id.etentercityname);
        enterCityName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    searchButton.performClick();
                    return true;
                }
                return false;
            }
        });
        searchButton = view.findViewById(R.id.search_button_main);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enterCityName.getText().toString().isEmpty()){
                    query = enterCityName.getText().toString();
                    RetrofitTest();
                    enterCityName.setText("");
                }
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(enterCityName.getWindowToken(), 0);
            }
        });
    }

    private void InitHomeScreen() {
        day = view.findViewById(R.id.tvDay);
        temp = view.findViewById(R.id.tvDegrees);
        city = view.findViewById(R.id.tvTown);
        main = view.findViewById(R.id.tvWeatherType);
        humidity = view.findViewById(R.id.tvHumidity);
        realFeel = view.findViewById(R.id.tvRealfeel);
    }

}
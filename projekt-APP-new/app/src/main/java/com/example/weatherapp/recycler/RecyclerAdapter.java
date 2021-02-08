package com.example.weatherapp.recycler;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.example.weatherapp.restapi.Data;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<Data> dataList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rvcomponents,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        DateFormat formatter = new SimpleDateFormat("EEEE HH:mm", Locale.ENGLISH);
        Data data = dataList.get(position);
        Date date = new Date(data.getDt()*1000);

        try {
            holder.setIvicon( data.getWeather().get(0).getIcon());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        holder.setTvdayntime(formatter.format(date));
        holder.setTvHumidity(data.getMain().getHumidity());
        holder.setTvrealfeel(data.getMain().getFeels_like());
        holder.setTvtemp(data.getMain().getTemp());
        holder.setTvWeatherType(data.getWeather().get(0).getMain());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<Data> dataList){
        this.dataList.clear();
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }
}

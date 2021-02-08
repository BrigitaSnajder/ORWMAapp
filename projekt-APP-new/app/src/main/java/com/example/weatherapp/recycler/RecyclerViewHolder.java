package com.example.weatherapp.recycler;

import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URISyntaxException;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    private ImageView ivicon;
    private TextView tvWeatherType;
    private TextView tvdayntime;
    private TextView tvtemp;
    private TextView tvrealfeel;
    private TextView tvHumidity;

    public RecyclerViewHolder (@NonNull View itemView){
        super(itemView);
        ivicon = itemView.findViewById(R.id.ivicon);
        tvWeatherType = itemView.findViewById(R.id.tvWeatherType);
        tvdayntime = itemView.findViewById(R.id.tvdayntime);
        tvtemp = itemView.findViewById(R.id.tvtemp);
        tvrealfeel = itemView.findViewById(R.id.tvrealfeel);
        tvHumidity = itemView.findViewById(R.id.tvHumidity);
    }

    public void setIvicon(String icon) throws URISyntaxException {
        Picasso.get().load(String.format("https://openweathermap.org/img/w/%s.png", icon)).into(ivicon);
    }

    public void setTvWeatherType(String weatherType) {
        this.tvWeatherType.setText(weatherType);
    }

    public void setTvdayntime(String dayntime) {
        this.tvdayntime.setText(dayntime);
    }

    public void setTvtemp(float temp) {
        this.tvtemp.setText(String.format("Temperature: %d°C", Math.round(temp)));
    }

    public void setTvrealfeel(float realfeel) {
        this.tvrealfeel.setText(String.format("Real-feel: %d°C", Math.round(realfeel)));
    }

    public void setTvHumidity(int Humidity) {
        this.tvHumidity.setText(String.format("Humidity: %d", Humidity));
    }
}

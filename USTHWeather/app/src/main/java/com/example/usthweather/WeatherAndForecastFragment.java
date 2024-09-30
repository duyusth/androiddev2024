package com.example.usthweather;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class WeatherAndForecastFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);

        // Add WeatherFragment and ForecastFragment dynamically
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_weather_container, new WeatherFragment());
        transaction.replace(R.id.fragment_forecast_container, new ForecastFragment());
        transaction.commit();

        return view;
    }
}

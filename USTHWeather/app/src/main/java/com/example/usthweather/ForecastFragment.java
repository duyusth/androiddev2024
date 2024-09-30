package com.example.usthweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;

public class ForecastFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment from fragment_forecast.xml
        View layout = inflater.inflate(R.layout.fragment_forecast, container, false);


        return layout; // Trả về layout đã inflate từ XML
    }
}

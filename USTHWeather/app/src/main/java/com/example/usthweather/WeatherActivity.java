package com.example.usthweather;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather); // Đảm bảo layout có FrameLayout với id là 'container'

        // Thêm ForecastFragment vào Activity
        ForecastFragment forecastFragment = new ForecastFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, forecastFragment) // 'container' là ID của FrameLayout trong layout
                .commit();
    }
}

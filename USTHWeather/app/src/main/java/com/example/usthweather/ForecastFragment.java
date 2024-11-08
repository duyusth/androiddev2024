package com.example.usthweather;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class ForecastFragment extends Fragment {

    private ImageView forecastLogo;

    private static final String USTH_LOGO_URL = "https://www.usth.edu.vn/uploads/2017/06/usth_logo.png";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // Initialize the ImageView
        forecastLogo = view.findViewById(R.id.weather_icon);

        // Download and display the USTH logo
        downloadUsthLogo();

        return view;
    }

    private void downloadUsthLogo() {
        // Show a Toast message indicating download start
        Toast.makeText(getContext(), "Downloading USTH logo...", Toast.LENGTH_SHORT).show();

        // Create a Volley ImageRequest
        ImageRequest imageRequest = new ImageRequest(
                USTH_LOGO_URL,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        // Set the downloaded image to the ImageView
                        forecastLogo.setImageBitmap(response);
                        Toast.makeText(getContext(), "Logo downloaded successfully!", Toast.LENGTH_SHORT).show();
                    }
                },
                0, // Max width (0 = original size)
                0, // Max height (0 = original size)
                ImageView.ScaleType.CENTER_CROP,
                Bitmap.Config.ARGB_8888,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors
                        Toast.makeText(getContext(), "Failed to download logo: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        // Add the request to the Volley request queue
        Volley.newRequestQueue(getContext()).add(imageRequest);
    }
}

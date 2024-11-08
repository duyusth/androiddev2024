package com.example.usthweather;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class WeatherFragment extends Fragment {

    private TextView cityTextView, temperatureTextView, conditionTextView;

    private static final String BASE_URL = "https://yahoo-weather5.p.rapidapi.com/weather";
    private static final String API_KEY = "d2b1e0d9b6794535ab91504cd3b6dcb4";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        // Initialize TextViews
        cityTextView = view.findViewById(R.id.city_text_view);
        temperatureTextView = view.findViewById(R.id.temperature_text_view);
        conditionTextView = view.findViewById(R.id.condition_text_view);

        // Fetch and display weather data
        fetchWeatherData("Hanoi");

        return view;
    }

    private void fetchWeatherData(String location) {
        // Create the request URL with the location
        String url = BASE_URL + "?location=" + location + "&format=json&u=c";

        // Create a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(requireContext());

        // Create a JSON object request
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Decode JSON data
                            String city = response.getJSONObject("location").getString("city");
                            String temperature = response.getJSONObject("current_observation").getJSONObject("condition").getString("temperature");
                            String condition = response.getJSONObject("current_observation").getJSONObject("condition").getString("text");

                            // Update UI
                            cityTextView.setText(city);
                            temperatureTextView.setText(temperature + "°C");
                            conditionTextView.setText(condition);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Error parsing JSON data.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(getContext(), "Failed to fetch weather data: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            public java.util.Map<String, String> getHeaders() {
                // Add required headers for API authentication
                java.util.Map<String, String> headers = new java.util.HashMap<>();
                headers.put("X-RapidAPI-Key", API_KEY);
                headers.put("X-RapidAPI-Host", "yahoo-weather5.p.rapidapi.com");
                return headers;
            }
        };

        // Add the request to the queue
        requestQueue.add(jsonObjectRequest);
    }
}

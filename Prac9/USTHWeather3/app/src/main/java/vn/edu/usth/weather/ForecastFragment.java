package vn.edu.usth.weather;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ForecastFragment extends Fragment {



    public ForecastFragment() {
        // Required empty public constructor
    }

    /
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);
//        View view = new View(getContext());
//        view.setBackgroundColor(Color.parseColor("#20FF0000"));
//
//        LinearLayout Layout = new LinearLayout(getContext());
//        Layout.setOrientation(LinearLayout.VERTICAL);
        //Layout.setBackgroundColor(Color.parseColor("#20FF0000"));
//        TextView textView = new TextView(getContext());
//        textView.setText("Thursday");
//        textView.setTextColor(Color.BLACK);
//        textView.setTextSize(20);

        // Create a new ImageView
//        ImageView imageView = new ImageView(getContext());
//        imageView.setImageResource(R.drawable.th); // Replace with your drawable

        // Add TextView and ImageView to the LinearLayout
//        Layout.addView(textView);
//        Layout.addView(imageView);
//        return Layout;
        return view;
    }
}
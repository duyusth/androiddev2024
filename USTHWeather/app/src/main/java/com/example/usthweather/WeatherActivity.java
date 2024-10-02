package com.example.usthweather;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);  // Quan trọng: Thiết lập Toolbar làm ActionBar
        // Initialize ViewPager2
        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab);

        // Initialize Adapter
        viewPagerAdapter = new ViewPagerAdapter(this);

        // Add 3 WeatherAndForecastFragments to ViewPager2
        viewPagerAdapter.addFragment(new WeatherAndForecastFragment(), "HANOI, VIETNAM");
        viewPagerAdapter.addFragment(new WeatherAndForecastFragment(), "PARIS, FRANCE");
        viewPagerAdapter.addFragment(new WeatherAndForecastFragment(), "TOULOUSE, FRANCE");

        viewPager.setAdapter(viewPagerAdapter);

        // Link TabLayout and ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(viewPagerAdapter.getFragmentTitle(position))
        ).attach();

        // Initialize Handler for handling UI updates from a thread
        handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.refresh) {
            simulateNetworkRequest();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Simulate a network request using Thread and Handler
    private void simulateNetworkRequest() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.post(() -> Toast.makeText(WeatherActivity.this, "Data refreshed!", Toast.LENGTH_SHORT).show());
        }).start();
    }

    // ViewPagerAdapter for managing fragments
    private class ViewPagerAdapter extends FragmentStateAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }

        public String getFragmentTitle(int position) {
            return fragmentTitleList.get(position);
        }
    }
}

package com.example.usthweather;

import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;

    private ViewPager2 viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

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

        mediaPlayer = MediaPlayer.create(this, R.raw.audio1);
        mediaPlayer.start();
        Toolbar Toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(Toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop(); //
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.search) {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
            return true;

        } else if (itemId == R.id.refresh) {

            Toast.makeText(this, "Refresh clicked", Toast.LENGTH_SHORT).show();

            return true;

        } else if (itemId == R.id.setting) {

            Toast.makeText(this, "Settings clicked", Toast.LENGTH_SHORT).show();

            return true;
        }

        return super.onOptionsItemSelected(item);}

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

package com.example.usthweather;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;  // Sửa lỗi import đúng Toolbar
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

        viewPager = findViewById(R.id.pager);
        tabLayout = findViewById(R.id.tab);

        viewPagerAdapter = new ViewPagerAdapter(this);

        viewPagerAdapter.addFragment(new WeatherAndForecastFragment(), "HANOI, VIETNAM");
        viewPagerAdapter.addFragment(new WeatherAndForecastFragment(), "PARIS, FRANCE");
        viewPagerAdapter.addFragment(new WeatherAndForecastFragment(), "TOULOUSE, FRANCE");

        mediaPlayer = MediaPlayer.create(this, R.raw.audio1);
        mediaPlayer.start();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(viewPagerAdapter.getFragmentTitle(position))
        ).attach();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
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
            new RefreshDataTask().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class RefreshDataTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(WeatherActivity.this, "Refreshing data...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(WeatherActivity.this, "Data refreshed!", Toast.LENGTH_SHORT).show();
        }
    }

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

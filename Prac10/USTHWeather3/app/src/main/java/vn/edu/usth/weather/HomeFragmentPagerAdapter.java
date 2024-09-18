package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class HomeFragmentPagerAdapter extends FragmentStateAdapter {
    private final String[] titles = new String[] { "Hanoi, VietName", "Paris,France", "Toulouse" };
    public HomeFragmentPagerAdapter(AppCompatActivity activity) {
        super(activity);
    }



    @Override
    public int getItemCount() {
        return 3; // number of pages for a ViewPager
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new WeatherAndForecastFragment(); // failsafe
    }
    public CharSequence getPageTitle(int page) {
// returns a tab title corresponding to the specified page
        return titles[page];
    }
}

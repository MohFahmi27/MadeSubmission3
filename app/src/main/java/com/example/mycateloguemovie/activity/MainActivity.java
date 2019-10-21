package com.example.mycateloguemovie.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.mycateloguemovie.fragment.TvFragment;
import com.example.mycateloguemovie.R;
import com.example.mycateloguemovie.adapter.TabAdapter;
import com.example.mycateloguemovie.fragment.MovieFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private TabAdapter adapter;
    private int[] tabIcons = {
            R.drawable.ic_movie_creation_black_48dp,
            R.drawable.ic_live_tv_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        String Movie = getResources().getString(R.string.movies);
        String Tvshow = getResources().getString(R.string.tv_show);

        adapter = new TabAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new MovieFragment(), Movie, tabIcons[0]);
        adapter.addFragment(new TvFragment(), Tvshow, tabIcons[1]);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        highLightCurrentTab(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void highLightCurrentTab(int position) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            assert tab != null;
            tab.setCustomView(null);
            tab.setCustomView(adapter.getTabView(i));
        }
        TabLayout.Tab tab = tabLayout.getTabAt(position);
        assert tab != null;
        tab.setCustomView(null);
        tab.setCustomView(adapter.getSelectedTabView(position));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_change_settings :
                Intent intentlang = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intentlang);
                break;
            case  R.id.favorite_action :
                Intent intentFav = new Intent(MainActivity.this, FavoriteActivity.class);
                startActivity(intentFav);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

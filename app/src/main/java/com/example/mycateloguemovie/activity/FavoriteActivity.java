package com.example.mycateloguemovie.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.mycateloguemovie.R;
import com.example.mycateloguemovie.adapter.TabAdapter;
import com.example.mycateloguemovie.fragment.FavoriteMovieFragment;
import com.example.mycateloguemovie.fragment.FavoriteTvshowFragment;
import com.example.mycateloguemovie.fragment.MovieFragment;
import com.example.mycateloguemovie.fragment.TvFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_favorite)
    Toolbar toolbarFavorite;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    private TabAdapter adapter;
    private int[] tabIcons = {
            R.drawable.ic_movie_creation_black_48dp,
            R.drawable.ic_live_tv_black_24dp
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        ButterKnife.bind(this);

        setSupportActionBar(toolbarFavorite);
        toolbarFavorite.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarFavorite.setNavigationOnClickListener(view -> finish());

        String Movie = getResources().getString(R.string.movies);
        String Tvshow = getResources().getString(R.string.tv_show);

        adapter = new TabAdapter(getSupportFragmentManager(), this);
        adapter.addFragment(new FavoriteMovieFragment(), Movie, tabIcons[0]);
        adapter.addFragment(new FavoriteTvshowFragment(), Tvshow, tabIcons[1]);
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

}

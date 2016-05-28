package com.example.rakshith.cricketapp.Fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakshith.cricketapp.Adapters.HomePagerAdapter;
import com.example.rakshith.cricketapp.R;

/**
 * Created by rakshith on 5/26/16.
 */
public class HomeFragment extends BaseFragment {

    ViewPager viewPager;
    TabLayout tabLayout;
    HomePagerAdapter homePagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_home_pager , container , false);
        viewPager = (ViewPager) view.findViewById(R.id.activity_home_pager_vp_view_pager);
        setUpViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.activity_home_pager_tb_tabs);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setUpViewPager(ViewPager viewPager) {
        homePagerAdapter = new HomePagerAdapter(getChildFragmentManager());
        homePagerAdapter.addFragment(new MatchesFragment() , getResources().getString(R.string.matches), new Bundle());
        homePagerAdapter.addFragment(new PointsFragment() , getResources().getString(R.string.points_table), new Bundle());
        viewPager.setAdapter(homePagerAdapter);
    }
}

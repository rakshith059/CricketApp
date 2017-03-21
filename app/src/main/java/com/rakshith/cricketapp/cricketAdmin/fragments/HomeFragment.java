package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.adapters.ViewPagerAdapter;

/**
 * Created by rakshith on 3/11/17.
 */
public class HomeFragment extends Fragment {
    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.fragment_home_vp_pager);
        setUpViewPager(viewPager);

        tabLayout = (TabLayout) view.findViewById(R.id.fragment_home_tl_tab);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setUpViewPager(ViewPager viewPager) {
        viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new TeamsFragment(), getResources().getString(R.string.teams), new Bundle());
        viewPagerAdapter.addFragment(new PoolsFragment(), getResources().getString(R.string.pools), new Bundle());
        viewPagerAdapter.addFragment(new MatchsFragment(), getResources().getString(R.string.matches), new Bundle());
        viewPagerAdapter.addFragment(new BatsmenFragment(), getResources().getString(R.string.batting), new Bundle());
        viewPagerAdapter.addFragment(new BowlerFragment(), getResources().getString(R.string.bowler), new Bundle());
        viewPagerAdapter.addFragment(new FieldersFragment(), getResources().getString(R.string.fielder), new Bundle());
        viewPagerAdapter.addFragment(new TeamsStandingFragment(), getResources().getString(R.string.team_standings), new Bundle());
        viewPager.setAdapter(viewPagerAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        viewPagerAdapter = null;
    }
}

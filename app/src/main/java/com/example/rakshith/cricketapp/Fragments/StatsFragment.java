package com.example.rakshith.cricketapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.rakshith.cricketapp.R;

/**
 * Created by rakshith on 5/28/16.
 */
public class StatsFragment extends BaseFragment implements View.OnClickListener{
    CardView    cvBattingStats;
    CardView    cvBowlingStats;
    CardView    cvValuablePlayer;
    LinearLayout llMainContainer;

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats_fragment, container, false);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        llMainContainer = (LinearLayout) getView().findViewById(R.id.stats_fragment_ll_main_container);
        cvBattingStats = (CardView) getView().findViewById(R.id.stats_fragment_batting_stats);
        cvBowlingStats = (CardView) getView().findViewById(R.id.stats_fragment_bowling_stats);
        cvValuablePlayer = (CardView) getView().findViewById(R.id.stats_fragment_valuable_player);

        checkIfInternetPresent();
    }

    private void checkIfInternetPresent() {
        if (isNetworkAvailable(getActivity())) {
            cvBattingStats.setOnClickListener(this);
            cvBowlingStats.setOnClickListener(this);
            cvValuablePlayer.setOnClickListener(this);
        } else {
            final Snackbar snackbar = Snackbar.make(llMainContainer, getResources().getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(getString(R.string.sm_retry), new View.OnClickListener
                    () {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                    checkIfInternetPresent();
                }
            });
            snackbar.show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.stats_fragment_batting_stats:
                fragmentCallbacks.replaceFragment(new BattingStatsFragment() , "battingStats" , null);
                break;
            case R.id.stats_fragment_bowling_stats:
                fragmentCallbacks.replaceFragment(new BowlingStatsFragment() , "bowlingStats" , null);
                break;
            case R.id.stats_fragment_valuable_player:
                fragmentCallbacks.replaceFragment(new ValuablePlayerFragment() , "valuablePlayer" , null);
                break;
            default:
                break;
        }
    }
}

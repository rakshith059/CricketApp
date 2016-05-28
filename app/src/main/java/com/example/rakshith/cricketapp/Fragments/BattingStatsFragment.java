package com.example.rakshith.cricketapp.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rakshith.cricketapp.Adapters.RecyclerMatchesAdapter;
import com.example.rakshith.cricketapp.Adapters.RecyclerStatsAdapter;
import com.example.rakshith.cricketapp.Models.BattingModel;
import com.example.rakshith.cricketapp.Models.MatchesModel;
import com.example.rakshith.cricketapp.R;
import com.example.rakshith.cricketapp.Utils.RecyclerItemDecorator;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rakshith on 5/28/16.
 */
public class BattingStatsFragment extends BaseFragment {
    TextView tvStatsTitle;
    TextView tvstatsPLayerName;
    TextView tvstatsBallsfacedOrBowled;
    TextView tvStatsRunsOrWicket;

    SwipeRefreshLayout srlRefreshLayout;
    RecyclerStatsAdapter statsAdapter;
    List<BattingModel> battingList = new ArrayList<>();

    Activity mActivity;
    RecyclerView rvRecyclerView;

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    ProgressBar progressBar;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_stats_fragment, container , false);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        return view;
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        srlRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.srl_refresh_container);
        rvRecyclerView = (RecyclerView) getView().findViewById(R.id.common_recycler_view_rv_view);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);

        tvStatsTitle = (TextView) getView().findViewById(R.id.common_stats_fragment_tv_title);
        tvstatsPLayerName = (TextView) getView().findViewById(R.id.stats_row_tv_player_name);
        tvstatsBallsfacedOrBowled = (TextView) getView().findViewById(R.id.stats_row_tv_ball);
        tvStatsRunsOrWicket = (TextView) getView().findViewById(R.id.stats_row_tv_runs_wicket);

        progressBar.setVisibility(View.VISIBLE);
        tvStatsTitle.setText(getResources().getString(R.string.batting_stats));
        tvstatsPLayerName.setText(getResources().getString(R.string.batsman_name));
        tvstatsBallsfacedOrBowled.setText(getResources().getString(R.string.balls_faced));
        tvStatsRunsOrWicket.setText(getResources().getString(R.string.runs_scored));

        getBattingStats();

        srlRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getBattingStats();
            }
        });

    }

    private void getBattingStats() {
        battingList.clear();
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Batting");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    progressBar.setVisibility(View.GONE);
                    srlRefreshLayout.setRefreshing(false);

                    for (ParseObject parseObject : list) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        String batsmanName = parseObject.getString("batsmanName");
                        String ballsFaced = parseObject.getString("balls");
                        int runsScored = parseObject.getInt("runs");

                        Log.d("Rakshith", " batsmanName " + batsmanName + " ballsFaced " + ballsFaced + " runsScored " + runsScored);

                        battingList.add(new BattingModel(batsmanName , ballsFaced , runsScored));
                    }

                    statsAdapter = new RecyclerStatsAdapter(mActivity , battingList);
                    rvRecyclerView.setLayoutManager(linearLayoutManager);
                    rvRecyclerView.addItemDecoration(new RecyclerItemDecorator(1));
                    rvRecyclerView.setAdapter(statsAdapter);
                } else {
//                    Snackbar.make(getView(), "something went wrong", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }

}

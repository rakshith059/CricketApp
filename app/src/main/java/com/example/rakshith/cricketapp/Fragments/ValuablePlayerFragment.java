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
import com.example.rakshith.cricketapp.Adapters.RecyclerValuablePlayerAdapter;
import com.example.rakshith.cricketapp.Models.BattingModel;
import com.example.rakshith.cricketapp.Models.ValuablePlayerModel;
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
public class ValuablePlayerFragment extends BaseFragment {
    TextView tvStatsTitle;
    TextView tvPlayerName;
    TextView tvRuns;
    TextView tvWicket;
    TextView tvCaught;
    TextView tvRunOut;
    TextView tvPoints;

    RecyclerValuablePlayerAdapter valuablePlayerAdapter;
    SwipeRefreshLayout srlRefreshLayout;
    Activity mActivity;

    RecyclerView rvRecyclerView;
    LinearLayoutManager linearLayoutManager;
    List<ValuablePlayerModel> valuablePlayerList = new ArrayList<>();

    ProgressBar progressBar;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.valuable_player_fragments, container, false);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tvStatsTitle = (TextView) getView().findViewById(R.id.common_stats_fragment_tv_title);
        tvPlayerName = (TextView) getView().findViewById(R.id.points_table_row_tv_team_name);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
        tvRuns = (TextView) getView().findViewById(R.id.points_table_row_tv_played);
        tvWicket = (TextView) getView().findViewById(R.id.points_table_row_tv_wins);
        tvCaught = (TextView) getView().findViewById(R.id.points_table_row_tv_loss);
        tvRunOut = (TextView) getView().findViewById(R.id.points_table_row_tv_points);
        tvPoints = (TextView) getView().findViewById(R.id.points_table_row_tv_nrr);

        rvRecyclerView = (RecyclerView) getView().findViewById(R.id.common_recycler_view_rv_view);
        srlRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.srl_refresh_container);

        progressBar.setVisibility(View.VISIBLE);

        tvStatsTitle.setText(getResources().getString(R.string.valuable_player_stats));
        tvPlayerName.setText(getResources().getString(R.string.player_name));
        tvRuns.setText(getResources().getString(R.string.r));
        tvWicket.setText(getResources().getString(R.string.w));
        tvCaught.setText(getResources().getString(R.string.c));
        tvRunOut.setText(getResources().getString(R.string.runout));
        tvPoints.setText(getResources().getString(R.string.pts));

        getValuablePlayer();

        srlRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getValuablePlayer();
            }
        });

    }

    private void getValuablePlayer() {
        valuablePlayerList.clear();
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("ValuablePlayer");
        parseQuery.addAscendingOrder("points");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    progressBar.setVisibility(View.GONE);
                    srlRefreshLayout.setRefreshing(false);

                    for (ParseObject parseObject : list) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        String playername = parseObject.getString("playerName");
                        String runsScored = parseObject.getString("runs");
                        String wicketTaken = parseObject.getString("wickets");
                        String caught = parseObject.getString("caughts");
                        String runOut = parseObject.getString("runouts");
                        int points = parseObject.getInt("points");

                        Log.d("Rakshith", " playername " + playername + " runsScored " + runsScored + " wicketTaken " + wicketTaken + " caught " + caught
                                + " runOut " + runOut + " points " + points);

                        valuablePlayerList.add(new ValuablePlayerModel(playername , runsScored , wicketTaken , caught , runOut , points));
                    }

                    valuablePlayerAdapter = new RecyclerValuablePlayerAdapter(mActivity , valuablePlayerList);
                    rvRecyclerView.setLayoutManager(linearLayoutManager);
                    rvRecyclerView.addItemDecoration(new RecyclerItemDecorator(1));
                    rvRecyclerView.setAdapter(valuablePlayerAdapter);
                } else {
//                    Snackbar.make(getView(), "something went wrong", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}

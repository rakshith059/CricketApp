//package com.rakshith.cricketapp.Fragments;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.rakshith.cricketapp.Adapters.RecyclerStatsAdapter;
//import com.rakshith.cricketapp.Models.BattingModel;
//import com.rakshith.cricketapp.R;
//import com.rakshith.cricketapp.Utils.RecyclerItemDecorator;
//import com.parse.FindCallback;
//import com.parse.ParseException;
//import com.parse.ParseObject;
//import com.parse.ParseQuery;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by rakshith on 5/28/16.
// */
//public class BowlingStatsFragment extends BaseFragment {
//    TextView tvStatsTitle;
//    TextView tvstatsPLayerName;
//    TextView tvstatsBallsfacedOrBowled;
//    TextView tvStatsRunsOrWicket;
//    SwipeRefreshLayout srlRefreshLayout;
//    RecyclerView rvRecyclerView;
//
//    RecyclerStatsAdapter statsAdapter;
//    List<BattingModel> battingList = new ArrayList<>();
//    Activity mActivity;
//    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
//    ProgressBar progressBar;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.common_stats_fragment, container , false);
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated( Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        srlRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.srl_refresh_container);
//        rvRecyclerView = (RecyclerView) getView().findViewById(R.id.common_recycler_view_rv_view);
//        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
//
//        tvStatsTitle = (TextView) getView().findViewById(R.id.common_stats_fragment_tv_title);
//        tvstatsPLayerName = (TextView) getView().findViewById(R.id.stats_row_tv_player_name);
//        tvstatsBallsfacedOrBowled = (TextView) getView().findViewById(R.id.stats_row_tv_ball);
//        tvStatsRunsOrWicket = (TextView) getView().findViewById(R.id.stats_row_tv_runs_wicket);
//
//        progressBar.setVisibility(View.VISIBLE);
//
//        tvStatsTitle.setText(getResources().getString(R.string.batting_stats));
//        tvstatsPLayerName.setText(getResources().getString(R.string.bowler_name));
//        tvstatsBallsfacedOrBowled.setText(getResources().getString(R.string.balls_bowled));
//        tvStatsRunsOrWicket.setText(getResources().getString(R.string.wickets_taken));
//
//        getBowlingStats();
//
//        srlRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getBowlingStats();
//            }
//        });
//
//    }
//
//    private void getBowlingStats() {
//        battingList.clear();
//        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Bowling");
//        parseQuery.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> list, ParseException e) {
//                if (e == null) {
//                    progressBar.setVisibility(View.GONE);
//                    srlRefreshLayout.setRefreshing(false);
//
//                    for (ParseObject parseObject : list) {
//                        // use dealsObject.get('columnName') to access the properties of the Deals object.
//                        String bowlerName = parseObject.getString("bowlerName");
//                        String ballsBowled = parseObject.getString("ballsBowled");
//                        int wicketsTaken = parseObject.getInt("wickets");
//
//                        Log.d("Rakshith", " bowlerName " + bowlerName + " ballsBowled " + ballsBowled + " wicketsTaken " + wicketsTaken);
//
//                        battingList.add(new BattingModel(bowlerName , ballsBowled , wicketsTaken));
//                    }
//
//                    statsAdapter = new RecyclerStatsAdapter(mActivity , battingList);
//                    rvRecyclerView.setLayoutManager(linearLayoutManager);
//                    rvRecyclerView.addItemDecoration(new RecyclerItemDecorator(1));
//                    rvRecyclerView.setAdapter(statsAdapter);
//                } else {
////                    Snackbar.make(getView(), "something went wrong", Snackbar.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//}

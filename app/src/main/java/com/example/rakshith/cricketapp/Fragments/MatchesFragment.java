package com.example.rakshith.cricketapp.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.rakshith.cricketapp.Adapters.RecyclerMatchesAdapter;
import com.example.rakshith.cricketapp.Models.MatchesModel;
import com.example.rakshith.cricketapp.R;
import com.example.rakshith.cricketapp.Utils.DataBaseHelper;
import com.example.rakshith.cricketapp.Utils.RecyclerItemDecorator;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rakshith on 5/26/16.
 */
public class MatchesFragment extends BaseFragment {

    List<MatchesModel> matchesList = new ArrayList<>();
    RecyclerMatchesAdapter matchesAdapter;
    SwipeRefreshLayout srlRefreshLayout;
    Activity mActivity;
    RelativeLayout llMainContainer;

    RecyclerView rvRecyclerView;
    LinearLayoutManager linearLayoutManager;

    DataBaseHelper dataBaseHelper;
    ProgressBar progressBar;

//    AdView mAdView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.matches_fragment, container, false);
        dataBaseHelper = new DataBaseHelper(getActivity());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        llMainContainer = (RelativeLayout) getView().findViewById(R.id.matches_fragment_ll_main_container);
        rvRecyclerView = (RecyclerView) getView().findViewById(R.id.common_recycler_view_rv_view);
        srlRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.macthes_fragment_srl_refresh_container);
        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);

//        mAdView = (AdView) getView().findViewById(R.id.adView);

        progressBar.setVisibility(View.VISIBLE);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecyclerView.addItemDecoration(new RecyclerItemDecorator(5));
        rvRecyclerView.setLayoutManager(linearLayoutManager);

        getMatchInfoIfInternetPresent();

//        AdRequest adRequest = new AdRequest.Builder()
////                .addTestDevice("847D2C1EBF23C6270BCD6AA0BE9380BC")
//                .build();
//        mAdView.loadAd(adRequest);

        srlRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMatchInfo();
            }
        });
    }

    private void getMatchInfoIfInternetPresent() {
        if (isNetworkAvailable(getActivity())) {
            getMatchInfo();
        } else {
            final Snackbar snackbar = Snackbar.make(llMainContainer, getResources().getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(getString(R.string.sm_retry), new View.OnClickListener
                    () {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                    getMatchInfoIfInternetPresent();
                }
            });
            snackbar.show();
        }
    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (mAdView != null) {
//            mAdView.resume();
//        }
//    }
//
//    @Override
//    public void onPause() {
//        if (mAdView != null) {
//            mAdView.pause();
//        }
//        super.onPause();
//    }
//
//    @Override
//    public void onDestroy() {
//        if (mAdView != null) {
//            mAdView.destroy();
//        }
//        super.onDestroy();
//    }

    private void getMatchInfo() {
        matchesList.clear();
        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Matches");
        parseQuery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    progressBar.setVisibility(View.GONE);
                    srlRefreshLayout.setRefreshing(false);

                    matchesList.add(new MatchesModel());

                    for (ParseObject parseObject : list) {
                        // use dealsObject.get('columnName') to access the properties of the Deals object.
                        String matchNum = parseObject.getString("matchNo");
                        String teamOne = parseObject.getString("team1");
                        String teamSecond = parseObject.getString("team2");
                        String winner = parseObject.getString("winner");
                        String toss = parseObject.getString("toss");
                        String team1Score = parseObject.getString("teamOneScore");
                        String team2Score = parseObject.getString("teamtwoScore");
                        String mom = parseObject.getString("MOM");

                        Log.d("Rakshith", " matchNum " + matchNum + " teamOne " + teamOne + " teamSecond " + teamSecond + " toss " + toss + " winner " + winner
                        + " team1Score " + team1Score + " team2Score " + team2Score);

                        MatchesModel matchesModel = new MatchesModel(matchNum, teamOne, teamSecond, toss, winner , team1Score , team2Score , mom);
                        matchesList.add(matchesModel);
                    }

                    matchesAdapter = new RecyclerMatchesAdapter(mActivity, matchesList , fragmentCallbacks);
                    rvRecyclerView.setAdapter(matchesAdapter);
                } else {
//                    Snackbar.make(getView(), "something went wrong", Snackbar.LENGTH_SHORT).show();
                }
            }
        });
    }
}

//package com.rakshith.cricketapp.Fragments;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.design.widget.Snackbar;
//import android.support.v4.widget.SwipeRefreshLayout;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//
//import com.rakshith.cricketapp.Adapters.RecyclerPointsAdapter;
//import com.rakshith.cricketapp.Models.PointsTableModel;
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
// * Created by rakshith on 5/27/16.
// */
//public class PointsFragment extends BaseFragment {
//    Activity mActivity;
//    RecyclerView rvRecyclerView;
//    RecyclerPointsAdapter pointsAdapter;
//    RelativeLayout llMainContainer;
//
//    SwipeRefreshLayout srlRefreshLayout;
//
//    LinearLayoutManager linearLayoutManager;
//
//    List<PointsTableModel> pointsTableList = new ArrayList<>();
//    ProgressBar progressBar;
//
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        this.mActivity = activity;
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = LayoutInflater.from(mActivity).inflate(R.layout.points_fragments , container , false);
//        rvRecyclerView = (RecyclerView) view.findViewById(R.id.common_recycler_view_rv_view);
//        srlRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.points_fragment_srl_refresh_container);
//
//        return view;
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        llMainContainer = (RelativeLayout) getView().findViewById(R.id.points_fragment_ll_main_container);
//        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);
//
//        progressBar.setVisibility(View.VISIBLE);
//
//        linearLayoutManager = new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        rvRecyclerView.addItemDecoration(new RecyclerItemDecorator(5));
//        rvRecyclerView.setLayoutManager(linearLayoutManager);
//
//        getMatchInfoIfInternetPresent();
//
//        srlRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                getMatchInfoIfInternetPresent();
//            }
//        });
//    }
//
//    private void getMatchInfoIfInternetPresent() {
//        if (isNetworkAvailable(getActivity())) {
//            getPointsTable();
//        } else {
//            final Snackbar snackbar = Snackbar.make(llMainContainer, getResources().getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE);
//            snackbar.setAction(getString(R.string.sm_retry), new View.OnClickListener
//                    () {
//                @Override
//                public void onClick(View v) {
//                    snackbar.dismiss();
//                    getMatchInfoIfInternetPresent();
//                }
//            });
//            snackbar.show();
//        }
//    }
//
//    private void getPointsTable() {
//        pointsTableList.clear();
//        srlRefreshLayout.setRefreshing(false);
//        ParseQuery<ParseObject> parseQuery = new ParseQuery<ParseObject>("Team");
//        parseQuery.addAscendingOrder("teamPosition");
//        parseQuery.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> list, ParseException e) {
//                if (e == null) {
//                    for (ParseObject parseObject : list) {
//                        progressBar.setVisibility(View.GONE);
//                        srlRefreshLayout.setRefreshing(false);
//
//                        // use dealsObject.get('columnName') to access the properties of the Deals object.
//                        String teamName = parseObject.getString("teamName");
//                        int playedMatches = parseObject.getInt("played");
//                        int matchesWin = parseObject.getInt("wins");
//                        int matchesLost = parseObject.getInt("loss");
//                        int points = parseObject.getInt("points");
//                        String netRunRate = parseObject.getString("Nrr");
//                        Log.d("Rakshith", " teamName " + teamName + " playedMatches " + playedMatches + " matchesWin " + matchesWin + " matchesLost " + matchesLost
//                                + " points " + points + " netRunRate " + netRunRate);
//
//                        pointsTableList.add(new PointsTableModel(teamName , playedMatches , matchesWin , matchesLost , points , netRunRate));
//                    }
//
//                    pointsAdapter = new RecyclerPointsAdapter(mActivity , pointsTableList);
//                    rvRecyclerView.setAdapter(pointsAdapter);
//                } else {
////                    Snackbar.make(getView(), "something went wrong", Snackbar.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mActivity = null;
//    }
//}

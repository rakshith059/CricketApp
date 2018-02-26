package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.Utils.RecyclerItemDecorator;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.adapters.PointsAdapter;
import com.rakshith.cricketapp.cricketAdmin.models.TeamScore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rakshith on 3/10/17.
 */
public class TeamsStandingFragment extends BaseFragment implements View.OnClickListener {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    RecyclerView rvTeamsScores;
    ProgressBar pbProgressBar;

    TextView tvTeamName;
    TextView tvPlayedMatches;
    TextView tvWins;
    TextView tvLoss;
    TextView tvPoints;

    Bundle bundle;
    private AdView mAdView;
    private String year = Constants.PARAM_YEAR_2018;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_standing, container, false);
        bundle = new Bundle();

        rvTeamsScores = (RecyclerView) view.findViewById(R.id.common_recycler_view_rv);
        pbProgressBar = (ProgressBar) view.findViewById(R.id.common_recycler_view_pb_progress);

        tvTeamName = (TextView) view.findViewById(R.id.player_stats_item_tv_player_name);
        tvPlayedMatches = (TextView) view.findViewById(R.id.player_stats_item_tv_matches);
        tvWins = (TextView) view.findViewById(R.id.player_stats_item_tv_balls_faced);
        tvLoss = (TextView) view.findViewById(R.id.player_stats_item_tv_runs_scored);
        tvPoints = (TextView) view.findViewById(R.id.player_stats_item_tv_fours);

        tvTeamName.setText(getResources().getString(R.string.team_name));
        tvPlayedMatches.setText(getResources().getString(R.string.p));
        tvWins.setText(getResources().getString(R.string.w));
        tvLoss.setText(getResources().getString(R.string.l));
        tvPoints.setText(getResources().getString(R.string.pts));

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        mAdView = (AdView) view.findViewById(R.id.common_recycler_view_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (bundle != null && ((HomeActivity) mActivity) != null) {
                bundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_TEAM_STANDINGS);
                ((HomeActivity) getActivity()).fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTeamsScores.addItemDecoration(new RecyclerItemDecorator(2));
        rvTeamsScores.setLayoutManager(layoutManager);

        year = Constants.getSharedPrefrenceString(mActivity, Constants.PARAM_YEAR);

        getTeamStandingsIfInternetAvailable(Constants.DB_TEAMS_SCORE_CHILD_TOTAL_POINT);

        tvPlayedMatches.setOnClickListener(this);
        tvWins.setOnClickListener(this);
        tvLoss.setOnClickListener(this);
        tvPoints.setOnClickListener(this);
    }

    private void getTeamStandingsIfInternetAvailable(String orderByChildValue) {
        pbProgressBar.setVisibility(View.VISIBLE);
        databaseReference.child(year).child(Constants.DB_TEAMS_SCORE).orderByChild(orderByChildValue)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        pbProgressBar.setVisibility(View.GONE);
                        if (dataSnapshot.getChildrenCount() > 0) {
                            List<TeamScore> teamScoreList = new ArrayList<TeamScore>();
                            for (DataSnapshot noteSnapshot : dataSnapshot.getChildren()) {
                                TeamScore teamScore = noteSnapshot.getValue(TeamScore.class);

                                teamScoreList.add(teamScore);
                            }
                            Collections.reverse(teamScoreList);

                            PointsAdapter pointsAdapter = new PointsAdapter(mActivity, teamScoreList);
                            rvTeamsScores.setAdapter(pointsAdapter);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.player_stats_item_tv_matches:
                getTeamStandingsIfInternetAvailable(Constants.DB_TEAMS_SCORE_CHILD_MATCHES_PLAYED);

                bundle.putString(Constants.PARAM_ORDER_BY, Constants.PARAM_ORDER_BY_MATCHES_PLAYED);
                ((HomeActivity) getActivity()).fireBaseAnalyticsEvents(Constants.EVENT_CLICKED, bundle);
                break;
            case R.id.player_stats_item_tv_balls_faced:
                getTeamStandingsIfInternetAvailable(Constants.DB_TEAMS_SCORE_CHILD_WINS);

                bundle.putString(Constants.PARAM_ORDER_BY, Constants.PARAM_ORDER_BY_MATCHES_WINS);
                ((HomeActivity) getActivity()).fireBaseAnalyticsEvents(Constants.EVENT_CLICKED, bundle);
                break;
            case R.id.player_stats_item_tv_runs_scored:
                getTeamStandingsIfInternetAvailable(Constants.DB_TEAMS_SCORE_CHILD_LOSS);

                bundle.putString(Constants.PARAM_ORDER_BY, Constants.PARAM_ORDER_BY_MATCHES_LOST);
                ((HomeActivity) getActivity()).fireBaseAnalyticsEvents(Constants.EVENT_CLICKED, bundle);
                break;
            case R.id.player_stats_item_tv_fours:
                getTeamStandingsIfInternetAvailable(Constants.DB_TEAMS_SCORE_CHILD_TOTAL_POINT);

                bundle.putString(Constants.PARAM_ORDER_BY, Constants.PARAM_ORDER_BY_TOTAL_POINTS);
                ((HomeActivity) getActivity()).fireBaseAnalyticsEvents(Constants.EVENT_CLICKED, bundle);
                break;
            default:
                break;
        }
    }
}

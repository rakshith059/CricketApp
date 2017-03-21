package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.adapters.PlayerStatsAdapter;
import com.rakshith.cricketapp.cricketAdmin.models.MemberStats;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rakshith on 3/10/17.
 */
public class BowlerFragment extends BaseFragment implements View.OnClickListener {

    TextView tvPlayerName;
    TextView tvMatchesPlayed;
    TextView tvBallsOverCatches;
    TextView tvRunsWicketsRunOuts;
    TextView tvFoursMaidenStump;

    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    PlayerStatsAdapter playerStatsAdapter;
    RecyclerView rvPlayerStats;
    ProgressBar pbProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.player_fragment_stats, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        rvPlayerStats = (RecyclerView) view.findViewById(R.id.common_recycler_view_rv);
        pbProgress = (ProgressBar) view.findViewById(R.id.common_recycler_view_pb_progress);

        tvPlayerName = (TextView) view.findViewById(R.id.player_stats_item_tv_player_name);
        tvMatchesPlayed = (TextView) view.findViewById(R.id.player_stats_item_tv_matches);
        tvBallsOverCatches = (TextView) view.findViewById(R.id.player_stats_item_tv_balls_faced);
        tvRunsWicketsRunOuts = (TextView) view.findViewById(R.id.player_stats_item_tv_runs_scored);
        tvFoursMaidenStump = (TextView) view.findViewById(R.id.player_stats_item_tv_fours);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPlayerStats.setLayoutManager(linearLayoutManager);

        tvBallsOverCatches.setText(getResources().getString(R.string.overs_bowled));
        tvRunsWicketsRunOuts.setText(getResources().getString(R.string.wickets_took));
        tvFoursMaidenStump.setText(getResources().getString(R.string.maidens));

        tvBallsOverCatches.setOnClickListener(this);
        tvRunsWicketsRunOuts.setOnClickListener(this);
        tvFoursMaidenStump.setOnClickListener(this);
        getPlayerStats(Constants.DB_PLAYER_STATS_CHILD_WICKETS);
    }

    private void getPlayerStats(String orderByChildValue) {
        databaseReference.child(Constants.DB_PLAYER_STATS).orderByChild(orderByChildValue).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<MemberStats> memberStatsList = new ArrayList<MemberStats>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    MemberStats memberStats = snapshot.getValue(MemberStats.class);
                    memberStatsList.add(memberStats);
                }
                Collections.reverse(memberStatsList);
                playerStatsAdapter = new PlayerStatsAdapter(mActivity, Constants.ROLE_BOWLER, memberStatsList);
                rvPlayerStats.setAdapter(playerStatsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.player_stats_item_tv_balls_faced:
                getPlayerStats(Constants.DB_PLAYER_STATS_CHILD_OVERS_BOWLED);
                break;
            case R.id.player_stats_item_tv_runs_scored:
                getPlayerStats(Constants.DB_PLAYER_STATS_CHILD_WICKETS);
                break;
            case R.id.player_stats_item_tv_fours:
                getPlayerStats(Constants.DB_PLAYER_STATS_CHILD_MAIDENS);
                break;
            default:
                break;
        }
    }
}

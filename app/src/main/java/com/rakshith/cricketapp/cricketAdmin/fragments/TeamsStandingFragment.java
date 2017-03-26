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
import com.rakshith.cricketapp.cricketAdmin.Utils.RecyclerItemDecorator;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_standing, container, false);
        rvTeamsScores = (RecyclerView) view.findViewById(R.id.common_recycler_view_rv);
        pbProgressBar = (ProgressBar) view.findViewById(R.id.common_recycler_view_pb_progress);

        tvTeamName = (TextView) view.findViewById(R.id.points_table_row_tv_team_name);
        tvPlayedMatches = (TextView) view.findViewById(R.id.points_table_row_tv_played);
        tvWins = (TextView) view.findViewById(R.id.points_table_row_tv_wins);
        tvLoss = (TextView) view.findViewById(R.id.points_table_row_tv_loss);
        tvPoints = (TextView) view.findViewById(R.id.points_table_row_tv_points);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTeamsScores.addItemDecoration(new RecyclerItemDecorator(2));
        rvTeamsScores.setLayoutManager(layoutManager);

        getTeamStandingsIfInternetAvailable(Constants.DB_TEAMS_SCORE_CHILD_TOTAL_POINT);

        tvPlayedMatches.setOnClickListener(this);
        tvWins.setOnClickListener(this);
        tvLoss.setOnClickListener(this);
        tvPoints.setOnClickListener(this);
    }

    private void getTeamStandingsIfInternetAvailable(String orderByChildValue) {
        pbProgressBar.setVisibility(View.VISIBLE);
        databaseReference.child(Constants.DB_TEAMS_SCORE).orderByChild(orderByChildValue)
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
            case R.id.points_table_row_tv_played:
                getTeamStandingsIfInternetAvailable(Constants.DB_TEAMS_SCORE_CHILD_MATCHES_PLAYED);
                break;
            case R.id.points_table_row_tv_wins:
                getTeamStandingsIfInternetAvailable(Constants.DB_TEAMS_SCORE_CHILD_WINS);
                break;
            case R.id.points_table_row_tv_loss:
                getTeamStandingsIfInternetAvailable(Constants.DB_TEAMS_SCORE_CHILD_LOSS);
                break;
            case R.id.points_table_row_tv_points:
                getTeamStandingsIfInternetAvailable(Constants.DB_TEAMS_SCORE_CHILD_TOTAL_POINT);
                break;
            default:
                break;
        }
    }
}

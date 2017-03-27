package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.Utils.RecyclerItemDecorator;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.adapters.TeamsAdapter;
import com.rakshith.cricketapp.cricketAdmin.models.TeamList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rakshith on 3/10/17.
 */
public class TeamsFragment extends BaseFragment implements View.OnClickListener {
    TextView tvAddTeam;
    RecyclerView rvTeamsList;

    TeamsAdapter teamsAdapter;
    ProgressBar pbProgressBar;
    TextView tvCreatePool;

    List teams;
    private List pollATeams;
    private List pollBTeams;
    private String userIdPollA;
    private String userIdPollB;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams_info, container, false);

        bundle = new Bundle();

        teamsAdapter = new TeamsAdapter();
        tvAddTeam = (TextView) view.findViewById(R.id.fragment_teams_info_tv_add_teams);
        tvCreatePool = (TextView) view.findViewById(R.id.fragment_teams_info_tv_create_pools);
        rvTeamsList = (RecyclerView) view.findViewById(R.id.common_recycler_view_rv);
        pbProgressBar = (ProgressBar) view.findViewById(R.id.common_recycler_view_pb_progress);
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            bundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_TEAMS);
            ((HomeActivity) getActivity()).fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTeamsList.addItemDecoration(new RecyclerItemDecorator(10));
        rvTeamsList.setLayoutManager(linearLayoutManager);
        getTeamsListIfInternetAvailable();

        tvAddTeam.setOnClickListener(this);
        tvCreatePool.setOnClickListener(this);
    }

    private void getTeamsListIfInternetAvailable() {
        pbProgressBar.setVisibility(View.VISIBLE);
        if (((HomeActivity) getActivity()).isNetworkAvailable(mActivity)) {
            getTeamsList();
            checkPoolsCreatedOrNot();
        } else {
            pbProgressBar.setVisibility(View.GONE);

            Snackbar mSnackbar = Snackbar.make(getView(), getResources().getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getTeamsListIfInternetAvailable();
                        }
                    });
            mSnackbar.show();
        }
    }

    private void checkPoolsCreatedOrNot() {
        firebaseDatabase.getReference().child(Constants.DB_POLL_A).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long poolTeamCount = dataSnapshot.getChildrenCount();
                if (poolTeamCount > 0) {
                    tvCreatePool.setVisibility(View.GONE);
                } else
                    tvCreatePool.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getTeamsList() {
        pbProgressBar.setVisibility(View.VISIBLE);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.child(Constants.DB_TEAM).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pbProgressBar.setVisibility(View.GONE);
                teams = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    TeamList teamList = noteDataSnapshot.getValue(TeamList.class);
                    teams.add(teamList);
                    Log.d("Rakshith", "team name " + teamList.getTeamName() + " " + "captain name " + teamList.getTeamMembers().get(1).getName() + teamList.getTeamMembers().get(1).getRole());
                }
//                adapter.updateList(notes);
                teamsAdapter.updateTeamsList(mActivity, teams);
                rvTeamsList.setAdapter(teamsAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pbProgressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_teams_info_tv_add_teams:
                ((HomeActivity) getActivity()).replaceFragment(new AddNewTeamFragment(), getResources().getString(R.string.add_new_teams), null);
                break;
            case R.id.fragment_teams_info_tv_create_pools:
                shuffleTeamsAndCreatePools();
                break;
        }
    }

    private void shuffleTeamsAndCreatePools() {
        if (teams.size() > 0) {
            Collections.shuffle(teams);

            if (teams != null) {
                int teamsSize = teams.size();
                if (teamsSize > 1) {
                    int pollASize = teamsSize / 2;

                    pollATeams = teams.subList(0, pollASize);
                    pollBTeams = teams.subList(pollASize, teamsSize);

                    DatabaseReference databasePollA = firebaseDatabase.getReference(Constants.DB_POLL_A);
                    DatabaseReference databasePollB = firebaseDatabase.getReference(Constants.DB_POLL_B);


                    if (TextUtils.isEmpty(userIdPollA)) {
                        userIdPollA = databasePollA.push().getKey();
                    }
                    databasePollA.child(userIdPollA).setValue(pollATeams);

                    if (TextUtils.isEmpty(userIdPollB)) {
                        userIdPollB = databasePollB.push().getKey();
                    }
                    databasePollB.child(userIdPollB).setValue(pollBTeams).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        tvCreatePool.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
            }
        }
    }
}

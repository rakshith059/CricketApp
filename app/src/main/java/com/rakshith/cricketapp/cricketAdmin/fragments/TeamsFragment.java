package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
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

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.Utils.RecyclerItemDecorator;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.adapters.TeamsAdapter;
import com.rakshith.cricketapp.cricketAdmin.models.TeamList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rakshith on 3/10/17.
 */
public class TeamsFragment extends BaseFragment implements View.OnClickListener {
    //    TextView tvAddTeam;
    RecyclerView rvTeamsList;

    TeamsAdapter teamsAdapter;
    ProgressBar pbProgressBar;

    List teams;
    private List pollATeams;
    private List pollBTeams;
    private String userIdPollA;
    private String userIdPollB;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    Bundle bundle = new Bundle();
    private FloatingActionButton fabAddTeam;
    private int teamSize;
    private FloatingActionButton fabCreatePool;
    FirebaseRemoteConfig remoteConfig;
    private AdView mAdView;

    String year = Constants.PARAM_YEAR_2018;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams_info, container, false);

        remoteConfig = FirebaseRemoteConfig.getInstance();
        remoteConfig.setConfigSettings(new FirebaseRemoteConfigSettings.Builder().setDeveloperModeEnabled(true)
                .build());

        year = Constants.getSharedPrefrenceString(mActivity, Constants.PARAM_YEAR);

        HashMap<String, Object> defaults = new HashMap<>();
        defaults.put(getResources().getString(R.string.team_size), 8);
        remoteConfig.setDefaults(defaults);

        teamsAdapter = new TeamsAdapter();
//        tvAddTeam = (TextView) view.findViewById(R.id.fragment_teams_info_tv_add_teams);
        fabAddTeam = (FloatingActionButton) view.findViewById(R.id.fragment_teams_info_fab_add_teams);
        fabCreatePool = (FloatingActionButton) view.findViewById(R.id.fragment_teams_info_fab_create_pools);
        rvTeamsList = (RecyclerView) view.findViewById(R.id.common_recycler_view_rv);
        pbProgressBar = (ProgressBar) view.findViewById(R.id.common_recycler_view_pb_progress);

        mAdView = (AdView) view.findViewById(R.id.common_recycler_view_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        return view;
    }

    @Override
    public void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mAdView.resume();
        super.onResume();
    }

    private void displayHideLayoutAfterReachingMaxTeam(int teamSize) {
        if (!TextUtils.isEmpty(isUserLoggedIn) && isUserLoggedIn.equalsIgnoreCase(Constants.TRUE)) {
            if (teams != null && teams.size() >= 0) {
                if (teams.size() == teamSize) {
                    fabAddTeam.setVisibility(View.GONE);
                    checkPoolsCreatedOrNot();
                } else {
                    fabAddTeam.setVisibility(View.VISIBLE);
                    fabCreatePool.setVisibility(View.GONE);
                }
            }
        } else {
            fabAddTeam.setVisibility(View.GONE);
            fabCreatePool.setVisibility(View.GONE);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (bundle != null && ((HomeActivity) mActivity) != null) {
                bundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_TEAMS);
                ((HomeActivity) mActivity).fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);
            }
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
        fabCreatePool.setOnClickListener(this);
        fabAddTeam.setOnClickListener(this);
    }

    private void getTeamsListIfInternetAvailable() {
        pbProgressBar.setVisibility(View.VISIBLE);
        if (((HomeActivity) getActivity()).isNetworkAvailable(mActivity)) {
            getTeamsList();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    fetchFromRemoteConfig();
                }
            }, 500);
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

    private void fetchFromRemoteConfig() {
        final Task<Void> fetch = remoteConfig.fetch(0);
        fetch.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                remoteConfig.activateFetched();
                teamSize = (int) remoteConfig.getLong(getResources().getString(R.string.team_size));
                displayHideLayoutAfterReachingMaxTeam(teamSize);
            }
        });
    }

    private void checkPoolsCreatedOrNot() {
        firebaseDatabase.getReference().child(year).child(Constants.DB_POLL_A).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                long poolTeamCount = dataSnapshot.getChildrenCount();
                if (poolTeamCount > 0) {
                    fabCreatePool.setVisibility(View.GONE);
                } else
                    fabCreatePool.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void getTeamsList() {
        pbProgressBar.setVisibility(View.VISIBLE);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        database.child(year).child(Constants.DB_TEAM).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pbProgressBar.setVisibility(View.GONE);
                teams = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    TeamList teamList = noteDataSnapshot.getValue(TeamList.class);
                    teams.add(teamList);
//                    Log.d("Rakshith", "team name " + teamList.getTeamName() + " " + "captain name " + teamList.getTeamMembers().get(1).getName() + teamList.getTeamMembers().get(1).getRole());
                }
//                adapter.updateList(notes);
                fetchFromRemoteConfig();
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
            case R.id.fragment_teams_info_fab_add_teams:
                ((HomeActivity) getActivity()).replaceFragment(new AddNewTeamFragment(), getResources().getString(R.string.add_new_teams), null);
                break;
            case R.id.fragment_teams_info_fab_create_pools:
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

                    DatabaseReference databasePollA = firebaseDatabase.getReference(year);
                    DatabaseReference databasePollB = firebaseDatabase.getReference(year);


                    if (TextUtils.isEmpty(userIdPollA)) {
                        userIdPollA = databasePollA.push().getKey();
                    }
                    databasePollA.child(Constants.DB_POLL_A).child(userIdPollA).setValue(pollATeams);

                    if (TextUtils.isEmpty(userIdPollB)) {
                        userIdPollB = databasePollB.push().getKey();
                    }
                    databasePollB.child(Constants.DB_POLL_B).child(userIdPollB).setValue(pollBTeams).
                            addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        fabCreatePool.setVisibility(View.GONE);
                                    }
                                }
                            });
                }
            }
        }
    }
}

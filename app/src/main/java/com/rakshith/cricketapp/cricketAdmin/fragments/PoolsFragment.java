package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.Utils.RecyclerItemDecorator;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.adapters.PoolAAdapter;
import com.rakshith.cricketapp.cricketAdmin.adapters.PoolBAdapter;
import com.rakshith.cricketapp.cricketAdmin.models.TeamList;

import java.util.List;
import java.util.Map;

/**
 * Created by rakshith on 3/10/17.
 */
public class PoolsFragment extends BaseFragment {

    private RecyclerView rvTeamsListPoolA;
    private RecyclerView rvTeamsListPoolB;

    PoolAAdapter poolAAdapter;
    PoolBAdapter poolBAdapter;
    private LinearLayout llMainContainer;
    private RelativeLayout rlPoolsNotGenerated;
    private ProgressBar pbMainProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pools, container, false);

        poolAAdapter = new PoolAAdapter();
        poolBAdapter = new PoolBAdapter();
        rvTeamsListPoolA = (RecyclerView) view.findViewById(R.id.fragment_pools_rv_poll_a);
        rvTeamsListPoolB = (RecyclerView) view.findViewById(R.id.fragment_pools_rv_poll_b);
        llMainContainer = (LinearLayout) view.findViewById(R.id.fragment_pools_ll_main_container);
        rlPoolsNotGenerated = (RelativeLayout) view.findViewById(R.id.fragment_pools_rl_pools_not_ready);
        pbMainProgress = (ProgressBar) view.findViewById(R.id.fragment_pools_pb_main_progress);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        StaggeredGridLayoutManager linearLayoutManagerPoolA = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvTeamsListPoolA.addItemDecoration(new RecyclerItemDecorator(5));
        rvTeamsListPoolA.setLayoutManager(linearLayoutManagerPoolA);

        StaggeredGridLayoutManager linearLayoutManagerPoolB = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvTeamsListPoolB.addItemDecoration(new RecyclerItemDecorator(5));
        rvTeamsListPoolB.setLayoutManager(linearLayoutManagerPoolB);
        getPoolListIfInternetAvailable();
    }

    private void getPoolListIfInternetAvailable() {
        if (((HomeActivity) getActivity()).isNetworkAvailable(mActivity)) {
            getPoolTeamsList();
        } else {
            Snackbar mSnackbar = Snackbar.make(getView(), getResources().getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getResources().getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            getPoolListIfInternetAvailable();
                        }
                    });
            mSnackbar.show();
        }
    }

    private void getPoolTeamsList() {
        pbMainProgress.setVisibility(View.VISIBLE);

        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child(Constants.DB_POLL_A).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        pbMainProgress.setVisibility(View.GONE);

                        List<TeamList> teamLists = null;
                        GenericTypeIndicator<Map<String, List<TeamList>>> genericTypeIndicator = new GenericTypeIndicator<Map<String, List<TeamList>>>() {
                        };
                        Map<String, List<TeamList>> hashMap = dataSnapshot.getValue(genericTypeIndicator);

                        if (hashMap != null) {
                            llMainContainer.setVisibility(View.VISIBLE);
                            rlPoolsNotGenerated.setVisibility(View.GONE);

                            for (Map.Entry<String, List<TeamList>> entry : hashMap.entrySet()) {
                                teamLists = entry.getValue();
                                for (TeamList teamList : teamLists) {
                                    Log.d("Rakshith", "teams from poll A " + teamList.getTeamName());
                                }
                            }
                            poolAAdapter.updatePoolAList(mActivity, teamLists);
                            rvTeamsListPoolA.setAdapter(poolAAdapter);
                        } else {
                            llMainContainer.setVisibility(View.GONE);
                            rlPoolsNotGenerated.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }

        );
        database.child(Constants.DB_POLL_B).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        pbMainProgress.setVisibility(View.GONE);

                        List<TeamList> teamLists = null;
                        GenericTypeIndicator<Map<String, List<TeamList>>> genericTypeIndicator = new GenericTypeIndicator<Map<String, List<TeamList>>>() {
                        };
                        Map<String, List<TeamList>> hashMap = dataSnapshot.getValue(genericTypeIndicator);

                        if (hashMap != null) {
                            llMainContainer.setVisibility(View.VISIBLE);
                            rlPoolsNotGenerated.setVisibility(View.GONE);
                            for (Map.Entry<String, List<TeamList>> entry : hashMap.entrySet()) {
                                teamLists = entry.getValue();
                                for (TeamList teamList : teamLists) {
                                    Log.d("Rakshith", "Teams from pool B " + teamList.getTeamName());
                                }
                            }
                            poolBAdapter.updatePoolBList(mActivity, teamLists);
                            rvTeamsListPoolB.setAdapter(poolBAdapter);
                        } else {
                            llMainContainer.setVisibility(View.GONE);
                            rlPoolsNotGenerated.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                }

        );
    }


}

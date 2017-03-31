package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

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
import com.rakshith.cricketapp.cricketAdmin.adapters.MatchesAdapter;
import com.rakshith.cricketapp.cricketAdmin.models.MatchList;
import com.rakshith.cricketapp.cricketAdmin.models.TeamList;

import java.util.ArrayList;

/**
 * Created by rakshith on 3/12/17.
 */
public class MatchsFragment extends BaseFragment implements View.OnClickListener {
    RecyclerView rvMatches;
    ProgressBar progressBar;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    int totalMatches;
    private ArrayList<MatchList> matchesList;
    private MatchesAdapter matchesAdapter;
    Bundle bundle = new Bundle();
    private FloatingActionButton fabEnterMatches;
    private AdView mAdView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        rvMatches = (RecyclerView) view.findViewById(R.id.common_recycler_view_rv);
        progressBar = (ProgressBar) view.findViewById(R.id.common_recycler_view_pb_progress);
        fabEnterMatches = (FloatingActionButton) view.findViewById(R.id.fragment_matches_fab_enter_matches);

        mAdView = (AdView) view.findViewById(R.id.common_recycler_view_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMatches.addItemDecoration(new RecyclerItemDecorator(20));
        rvMatches.setLayoutManager(linearLayoutManager);

        getMatches();

        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (bundle != null && ((HomeActivity) mActivity) != null) {
                bundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_MATCHES);
                ((HomeActivity) getActivity()).fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);
            }
        }
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

    private void getMatches() {
        progressBar.setVisibility(View.VISIBLE);
        databaseReference.child(Constants.DB_MATCHES).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(View.GONE);
                totalMatches = Integer.parseInt(String.valueOf(dataSnapshot.getChildrenCount())) + 1;
                matchesList = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    MatchList matchList = noteDataSnapshot.getValue(MatchList.class);
                    matchesList.add(matchList);
                }

                matchesAdapter = new MatchesAdapter(mActivity, matchesList);
                rvMatches.setAdapter(matchesAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        fabEnterMatches.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_matches_fab_enter_matches:
                Bundle bundle = new Bundle();
                bundle.putString(Constants.MATCH_NUM, "Match " + totalMatches);

                ((HomeActivity) getActivity()).replaceFragment(new EnterMatchesFragment(), getResources().getString(R.string.enter_matches), bundle);
                break;
            default:
                break;
        }
    }
}

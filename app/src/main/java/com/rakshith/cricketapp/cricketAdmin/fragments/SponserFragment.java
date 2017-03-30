package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.rakshith.cricketapp.cricketAdmin.adapters.SponsorAdapter;
import com.rakshith.cricketapp.cricketAdmin.models.SponsorModel;

import java.util.ArrayList;

/**
 * Created by rakshith on 3/31/17.
 */

public class SponserFragment extends BaseFragment {
    RecyclerView rvSponserList;
    ProgressBar pbProgressBar;
    AdView mAdView;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private ArrayList<SponsorModel> sponsorList;

    SponsorAdapter sponsorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sponsors, container, false);

        rvSponserList = (RecyclerView) view.findViewById(R.id.common_recycler_view_rv);
        pbProgressBar = (ProgressBar) view.findViewById(R.id.common_recycler_view_pb_progress);

        mAdView = (AdView) view.findViewById(R.id.common_recycler_view_ad_view);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSponserList.setLayoutManager(layoutManager);

        databaseReference.child(Constants.DB_SPONSORS).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sponsorList = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    SponsorModel sponsorModel = noteDataSnapshot.getValue(SponsorModel.class);
                    sponsorList.add(sponsorModel);

                    Log.d("Rakshith", "name " + sponsorModel.getName() + " detail " + sponsorModel.getDetail());
                }
                sponsorAdapter = new SponsorAdapter(mActivity, sponsorList);
                rvSponserList.setAdapter(sponsorAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}

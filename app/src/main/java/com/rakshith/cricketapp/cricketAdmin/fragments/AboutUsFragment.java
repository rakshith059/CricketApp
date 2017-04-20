package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdView;
import com.rakshith.cricketapp.R;

import java.util.List;

/**
 * Created by rakshith on 5/28/16.
 */
public class AboutUsFragment extends BaseFragment implements View.OnClickListener {
    LinearLayout llPhone1, llPhone2, llPhone3, llPhone4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.about_us_row, container, false);

//        mAdView = (AdView) view.findViewById(R.id.common_recycler_view_ad_view);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        llPhone1 = (LinearLayout) view.findViewById(R.id.about_us_row_ll_phone1);
        llPhone2 = (LinearLayout) view.findViewById(R.id.about_us_row_ll_phone2);
        llPhone3 = (LinearLayout) view.findViewById(R.id.about_us_row_ll_phone3);
        llPhone4 = (LinearLayout) view.findViewById(R.id.about_us_row_ll_phone4);

        llPhone1.setOnClickListener(this);
        llPhone2.setOnClickListener(this);
        llPhone3.setOnClickListener(this);
        llPhone4.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        rvRecyclerView = (RecyclerView) getView().findViewById(R.id.common_recycler_view_rv);
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        rvRecyclerView.setLayoutManager(linearLayoutManager);
//        rvRecyclerView.addItemDecoration(new RecyclerItemDecorator(5));
//        aboutUsList = new ArrayList<>();
//        aboutUsList();
//
//        Collections.sort(aboutUsList, String.CASE_INSENSITIVE_ORDER);
//
//        aboutUsAdapter = new RecyclerAboutUsAdapter(getActivity(), aboutUsList);
//        rvRecyclerView.setAdapter(aboutUsAdapter);
    }

    private void setPhoneCall(String phoneNumber) {
        try {
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(callIntent);
        } catch (Exception e) {
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_us_row_ll_phone1:
                setPhoneCall(getResources().getString(R.string.phone_karthik));
                break;
            case R.id.about_us_row_ll_phone2:
                setPhoneCall(getResources().getString(R.string.phone_natraj));
                break;
            case R.id.about_us_row_ll_phone3:
                setPhoneCall(getResources().getString(R.string.phone_mohan));
                break;
            case R.id.about_us_row_ll_phone4:
                setPhoneCall(getResources().getString(R.string.phone_nithin));
                break;
        }
    }
}

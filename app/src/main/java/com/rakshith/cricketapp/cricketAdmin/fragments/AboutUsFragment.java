package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.rakshith.cricketapp.R;

/**
 * Created by rakshith on 5/28/16.
 */
public class AboutUsFragment extends BaseFragment implements View.OnClickListener {
    private static final int READ_PHONE_STATE_PERMISSION = 1001;
    LinearLayout llPhone1, llPhone2, llPhone3, llPhone4, llPhone5;

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
        llPhone5 = (LinearLayout) view.findViewById(R.id.about_us_row_ll_phone5);

        llPhone1.setOnClickListener(this);
        llPhone2.setOnClickListener(this);
        llPhone3.setOnClickListener(this);
        llPhone4.setOnClickListener(this);
        llPhone5.setOnClickListener(this);
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
        if (checkPermisssion()) {
            try {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(callIntent);
            } catch (Exception e) {
            }
        } else requestPermission();
    }

    private boolean checkPermisssion() {
        int result = ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_PHONE_STATE);
        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && mActivity.checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_PERMISSION);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case READ_PHONE_STATE_PERMISSION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Permission granted do what you want from this point
                } else {
                    //Permission denied, manage this usecase
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.about_us_row_ll_phone1:
                setPhoneCall(getResources().getString(R.string.phone_mohan));
                break;
            case R.id.about_us_row_ll_phone2:
                setPhoneCall(getResources().getString(R.string.phone_sachin));
                break;
            case R.id.about_us_row_ll_phone3:
                setPhoneCall(getResources().getString(R.string.phone_sharath));
                break;
            case R.id.about_us_row_ll_phone4:
                setPhoneCall(getResources().getString(R.string.phone_soma));
                break;
            case R.id.about_us_row_ll_phone5:
                setPhoneCall(getResources().getString(R.string.phone_subbi));
                break;
        }
    }
}

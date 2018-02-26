package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.adapters.TeamMemberDetailAdapter;
import com.rakshith.cricketapp.cricketAdmin.models.TeamList;

/**
 * Created by rakshith on 3/12/17.
 */
public class TeamsDetailFragment extends BaseFragment implements View.OnClickListener {

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;

    RecyclerView rvPlayersList;
    TeamList teamDetail;
    private ImageView ivCollapseImage;

    TeamMemberDetailAdapter memberDetailAdapter;
    private int backgroundImageId;
    private TextView tvEditTeamScore;

    Bundle analyticsBundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.from(mActivity).inflate(R.layout.fragment_teams_detail, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            teamDetail = bundle.getParcelable(Constants.TEAM_DETAIL);
        }

        analyticsBundle = new Bundle();
        analyticsBundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_TEAM_DETAIL);
        ((HomeActivity) mActivity).fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);


        ivCollapseImage = (ImageView) view.findViewById(R.id.fragment_teams_detail_iv_collapse_image);
        toolbar = (Toolbar) view.findViewById(R.id.fragment_teams_detail_toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapse_toolbar);
        rvPlayersList = (RecyclerView) view.findViewById(R.id.common_recycler_view_rv);
        tvEditTeamScore = (TextView) view.findViewById(R.id.fragment_teams_detail_tv_edit_team_score);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPlayersList.setLayoutManager(linearLayoutManager);

        if (!TextUtils.isEmpty(isUserLoggedIn) && isUserLoggedIn.equalsIgnoreCase(Constants.TRUE)) {
            tvEditTeamScore.setVisibility(View.VISIBLE);
        } else
            tvEditTeamScore.setVisibility(View.GONE);

        tvEditTeamScore.setOnClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.white));
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.white));

        String teamName = teamDetail.getTeamName();
        String teamImageUrl = teamDetail.getTeamImageUrl();
        if (!TextUtils.isEmpty(teamName)) {
            collapsingToolbar.setTitle(teamName);
        }
        if (!TextUtils.isEmpty(teamImageUrl)) {
            Glide.with(mActivity)
                    .load(teamImageUrl)
                    .asBitmap()
                    .placeholder(R.drawable.gradient_background)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(new BitmapImageViewTarget(ivCollapseImage));
        }
//        setBackgroundImage(teamDetail.getCityName());

        memberDetailAdapter = new TeamMemberDetailAdapter(mActivity, teamName, teamDetail.getTeamMembers());
        rvPlayersList.setAdapter(memberDetailAdapter);
    }

//    private void setBackgroundImage(String cityName) {
//        if (cityName.equalsIgnoreCase(Constants.TARIKERE)) {
//            backgroundImageId = R.drawable.ic_tarikere;
//        } else if (cityName.equalsIgnoreCase(Constants.BHADRAVATHI)) {
//            backgroundImageId = R.drawable.ic_bhadravati;
//        } else if (cityName.equalsIgnoreCase(Constants.KADUR)) {
//            backgroundImageId = R.drawable.ic_kadur;
//        } else if (cityName.equalsIgnoreCase(Constants.SHIVAMOGA)) {
//            backgroundImageId = R.drawable.ic_shimoga;
//        } else if (cityName.equalsIgnoreCase(Constants.ARSIKERE)) {
//            backgroundImageId = R.drawable.ic_arsikere;
//        } else if (cityName.equalsIgnoreCase(Constants.TIPTUR)) {
//            backgroundImageId = R.drawable.ic_tiptur;
//        } else if (cityName.equalsIgnoreCase(Constants.HASSAN)) {
//            backgroundImageId = R.drawable.ic_hassan;
//        } else {
//            backgroundImageId = R.drawable.gradient_bg;
//        }
//        Glide.with(mActivity)
//                .load(backgroundImageId)
//                .into(ivCollapseImage);
//    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.TEAM_NAME, teamDetail.getTeamName());
        bundle.putString(Constants.CITY_NAME, teamDetail.getCityName());
        ((HomeActivity) getActivity()).replaceFragment(new EditTeamScoreFragment(), getResources().getString(R.string.edit_team), bundle);
    }
}

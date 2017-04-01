package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.models.TeamScore;

/**
 * Created by rakshith on 4/1/17.
 */

public class TeamsStandingDetailFragment extends BaseFragment implements View.OnClickListener {
    RelativeLayout rlMainContainer;

    TextView tvTeamName;
    TextView tvMacthesPlayed;
    TextView tvMacthesWin;
    TextView tvMatchesLost;
    TextView tvRunsScored;
    TextView tvRunsGave;
    TextView tvWicketsLost;
    TextView tvWicketsTook;
    TextView tvTeamValue;
    ImageView ivTeamValueRulesInfo;

    TeamScore teamScore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_standing_detail, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            teamScore = bundle.getParcelable(Constants.TEAM_DETAIL);
        }

        Bundle analyticsBundle = new Bundle();
        analyticsBundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_TEAM_STANDING_DETAIL);
        ((HomeActivity) mActivity).fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);

        rlMainContainer = (RelativeLayout) view.findViewById(R.id.team_standing_detail_rl_main_container);

        tvTeamName = (TextView) view.findViewById(R.id.team_standing_detail_tv_team_name);
        tvMacthesPlayed = (TextView) view.findViewById(R.id.team_standing_detail_tv_matches_played);
        tvMacthesWin = (TextView) view.findViewById(R.id.team_standing_detail_tv_matches_win);
        tvMatchesLost = (TextView) view.findViewById(R.id.team_standing_detail_tv_matches_lost);
        tvRunsScored = (TextView) view.findViewById(R.id.team_standing_detail_tv_runs_scored);
        tvRunsGave = (TextView) view.findViewById(R.id.team_standing_detail_tv_runs_gave);
        tvWicketsLost = (TextView) view.findViewById(R.id.team_standing_detail_tv_wickets_lost);
        tvWicketsTook = (TextView) view.findViewById(R.id.team_standing_detail_tv_wickets_took);
        tvTeamValue = (TextView) view.findViewById(R.id.team_standing_detail_tv_team_value);

        ivTeamValueRulesInfo = (ImageView) view.findViewById(R.id.team_standing_detail_iv_team_value_rules);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String mTeamName = teamScore.getTeamName();
        int mMatchesPlayed = teamScore.getMatchesPlayed();
        int mMatchesWin = teamScore.getWins();
        int mMatchesLost = teamScore.getLost();
        int mRunScored = teamScore.getRunsFor();
        int mRunsGave = teamScore.getRunsAgainst();
        int mWicketsLost = teamScore.getWicketsLost();
        int mWicketsTook = teamScore.getWicketsTook();

        int mTeamValue = (mRunScored - (mWicketsLost * Constants.RUNS_FOR_WICKET)) -
                (mRunsGave - (mWicketsTook * Constants.RUNS_FOR_WICKET));

        if (!TextUtils.isEmpty(mTeamName))
            tvTeamName.setText(mTeamName);

        tvMacthesPlayed.setText(getResources().getString(R.string.total_matches_played) + " " + mMatchesPlayed);
        tvMacthesWin.setText(getResources().getString(R.string.total_matches_won) + " " + mMatchesWin);
        tvMatchesLost.setText(getResources().getString(R.string.total_matches_lost) + " " + mMatchesLost);

        tvRunsScored.setText(String.valueOf(mRunScored));
        tvRunsGave.setText(String.valueOf(mRunsGave));
        tvWicketsLost.setText(String.valueOf(mWicketsLost));
        tvWicketsTook.setText(String.valueOf(mWicketsTook));

        tvTeamValue.setText(String.valueOf(mTeamValue));

        ivTeamValueRulesInfo.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.team_standing_detail_iv_team_value_rules:
                Snackbar.make(rlMainContainer, getResources().getString(R.string.rules_for_valuable_player), Snackbar.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}

package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.models.MatchList;

/**
 * Created by rakshith on 4/1/17.
 */

public class DisplayMatchesFragment extends BaseFragment {
    TextView tvMatchNo;
    TextView tvTeamOneName;
    TextView tvTeamOneScore;
    TextView tvTeamTwoName;
    TextView tvTeamTwoScore;
    TextView tvTossWinBy;
    TextView tvMatchWinBy;
    TextView tvManOfTheMatch;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private MatchList matchList;


    private String teamOneName;
    private String teamOneRuns;
    private String teamOneWickets;
    private String teamTwoName;
    private String teamTwoRuns;
    private String teamTwoWickets;
    private String tossWinByTeam;
    private String matchWinByTeam;
    private String winByRunsWickets;
    private String runsWicketsFrom;
    private String manOfTheMatch;
    private String matchNumber;
    private Bundle analyticsBundle;
    private ImageView ivTrophy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matches_detail, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Bundle bundle = getArguments();
        if (bundle != null) {
            matchList = bundle.getParcelable(Constants.MATCH_DETAIL);
        }

        analyticsBundle = new Bundle();
        analyticsBundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_MATCH_DETAIL);
        ((HomeActivity) mActivity).fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);

        tvMatchNo = (TextView) view.findViewById(R.id.matches_row_item_tv_match_number);
        tvTeamOneName = (TextView) view.findViewById(R.id.matches_row_item_tv_team_one);
        tvTeamOneScore = (TextView) view.findViewById(R.id.matches_row_item_tv_team_one_score);
        tvTeamTwoName = (TextView) view.findViewById(R.id.matches_row_item_tv_team_two);
        tvTeamTwoScore = (TextView) view.findViewById(R.id.matches_row_item_tv_team_two_score);
        tvTossWinBy = (TextView) view.findViewById(R.id.fragment_matches_detail_tv_toss_win_by);
        tvMatchWinBy = (TextView) view.findViewById(R.id.fragment_matches_detail_tv_match_win_by);
        tvManOfTheMatch = (TextView) view.findViewById(R.id.fragment_matches_detail_tv_mom);

        ivTrophy = (ImageView) view.findViewById(R.id.fragment_matches_detail_iv_trophy);
        Glide.with(mActivity)
                .load(R.drawable.ic_trophy)
                .asGif()
                .into(ivTrophy);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        matchNumber = matchList.getMatchNumber();
        teamOneName = matchList.getTeamOneName();
        teamOneRuns = matchList.getTeamOneRuns();
        teamOneWickets = matchList.getTeamOneWickets();
        teamTwoName = matchList.getTeamTwoName();
        teamTwoRuns = matchList.getTeamTwoRuns();
        teamTwoWickets = matchList.getTeamTwoWickets();
        tossWinByTeam = matchList.getTossWinByTeam();
        matchWinByTeam = matchList.getMatchWinByTeam();
        winByRunsWickets = matchList.getWinByRunsWickets();
        runsWicketsFrom = matchList.getRunsWickets();
        manOfTheMatch = matchList.getManOfTheMatch();
        matchWinByTeam = matchList.getMatchWinByTeam();

        if (!TextUtils.isEmpty(matchNumber))
            tvMatchNo.setText(matchNumber);
        if (!TextUtils.isEmpty(teamOneName))
            tvTeamOneName.setText(teamOneName);
        if (!TextUtils.isEmpty(teamOneRuns) && !TextUtils.isEmpty(teamOneWickets)) {
            tvTeamOneScore.setVisibility(View.VISIBLE);
            tvTeamOneScore.setText(teamOneRuns + "/" + teamOneWickets);
        } else {
            tvTeamOneScore.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(teamTwoName))
            tvTeamTwoName.setText(teamTwoName);
        if (!TextUtils.isEmpty(teamTwoRuns) && !TextUtils.isEmpty(teamTwoWickets)) {
            tvTeamTwoScore.setText(teamTwoRuns + "/" + teamTwoWickets);
            tvTeamTwoScore.setVisibility(View.VISIBLE);
        } else {
            tvTeamTwoScore.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(matchWinByTeam) && !TextUtils.isEmpty(winByRunsWickets) && !TextUtils.isEmpty(runsWicketsFrom))
            tvMatchWinBy.setText(matchWinByTeam + " won by " + winByRunsWickets + " " + runsWicketsFrom);
        else {
            if (!TextUtils.isEmpty(teamOneRuns) && !TextUtils.isEmpty(teamTwoRuns) && Integer.parseInt(teamOneRuns) == Integer.parseInt(teamTwoRuns))
                tvMatchWinBy.setText(getResources().getString(R.string.match_tied_between) + " " + teamOneName + " & " + teamTwoName);
        }
        if (!TextUtils.isEmpty(manOfTheMatch))
            tvManOfTheMatch.setText(getResources().getString(R.string.man_of_the_match) + " " + manOfTheMatch);
        if (!TextUtils.isEmpty(tossWinByTeam)) {
            tvTossWinBy.setText(getResources().getString(R.string.toss_win_by) + " " + tossWinByTeam);
        }
    }
}

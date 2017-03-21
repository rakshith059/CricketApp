package com.rakshith.cricketapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakshith.cricketapp.Models.MatchesModel;
import com.rakshith.cricketapp.R;

/**
 * Created by rakshith on 5/28/16.
 */
public class MatchDetailFragment extends Fragment {
    MatchesModel matchesModel;
    String matchNo;
    String teamOne;
    String teamSecond;
    String toss;
    String winner;
    String teamOneScore;
    String teamTwoScore;
    String mom;

    TextView tvMatchNo;
    TextView tvToss;
    TextView tvTeamOneName;
    TextView tvTeamTwoName;
    TextView tvTeamOneScore;
    TextView tvTeamTwoScore;
    TextView tvWinner;
    TextView tvMom;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.match_detail_fragment, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            matchesModel = bundle.getParcelable("matchDetail");
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvMatchNo = (TextView) getView().findViewById(R.id.match_detail_match_no);
        tvToss = (TextView) getView().findViewById(R.id.match_detail_toss);
        tvTeamOneName = (TextView) getView().findViewById(R.id.match_detail_team_one_name);
        tvTeamOneScore = (TextView) getView().findViewById(R.id.match_detail_team_one_score);
        tvTeamTwoName = (TextView) getView().findViewById(R.id.match_detail_team_two_name);
        tvTeamTwoScore = (TextView) getView().findViewById(R.id.match_detail_team_two_score);
        tvWinner = (TextView) getView().findViewById(R.id.match_detail_winner);
        tvMom = (TextView) getView().findViewById(R.id.match_detail_mom_player);

        if (matchesModel != null) {
            matchNo = matchesModel.getMatchNo();
            teamOne = matchesModel.getTeamOne();
            teamOneScore = matchesModel.getTeamOneScore();
            teamSecond = matchesModel.getTeamSecond();
            teamTwoScore = matchesModel.getTeamTwoScore();
            toss = matchesModel.getToss();
            winner = matchesModel.getWinner();
            mom = matchesModel.getMom();

            if (!TextUtils.isEmpty(matchNo)) {
                tvMatchNo.setText(matchNo);
            }if (!TextUtils.isEmpty(toss)) {
                tvToss.setText(toss);
            }if (!TextUtils.isEmpty(teamOne)) {
                tvTeamOneName.setText(teamOne);
            }if (!TextUtils.isEmpty(teamSecond)) {
                tvTeamTwoName.setText(teamSecond);
            }if (!TextUtils.isEmpty(teamOneScore)) {
                tvTeamOneScore.setText(teamOneScore);
            }if (!TextUtils.isEmpty(teamTwoScore)) {
                tvTeamTwoScore.setText(teamTwoScore);
            }if (!TextUtils.isEmpty(winner)) {
                tvWinner.setText(winner);
            }if (!TextUtils.isEmpty(mom)) {
                tvMom.setText(mom);
            }
        }
    }
}

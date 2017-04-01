package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.models.MemberStats;

/**
 * Created by rakshith on 3/27/17.
 */
public class PlayerDetailFragment extends BaseFragment {
    TextView tvBattingMatchesPlayed;
    TextView tvBallsFaced;
    TextView tvRunsScored;
    TextView tvFours;
    TextView tvOversBowled;
    TextView tvWicketsTook;
    TextView tvMaidens;
    TextView tvCatches;
    TextView tvRunOuts;
    TextView tvStumps;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    MemberStats memberDetail;
    private Bundle analyticsBundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player_detail, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(Constants.DB_PLAYER_STATS);

        Bundle bundle = getArguments();
        if (bundle != null) {
            memberDetail = bundle.getParcelable(Constants.PLAYER_DETAIL);
        }

        analyticsBundle = new Bundle();
        analyticsBundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_PLAYER_DETAIL);
        ((HomeActivity) mActivity).fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);

        tvBattingMatchesPlayed = (TextView) view.findViewById(R.id.fragment_player_detail_tv_batting_matches_played);
        tvBallsFaced = (TextView) view.findViewById(R.id.fragment_player_detail_tv_balls_faced);
        tvRunsScored = (TextView) view.findViewById(R.id.fragment_player_detail_tv_runs_scored);
        tvFours = (TextView) view.findViewById(R.id.fragment_player_detail_tv_fours);
        tvOversBowled = (TextView) view.findViewById(R.id.fragment_player_detail_tv_overs_bowled);
        tvWicketsTook = (TextView) view.findViewById(R.id.fragment_player_detail_tv_wickets_took);
        tvMaidens = (TextView) view.findViewById(R.id.fragment_player_detail_tv_maidens);
        tvCatches = (TextView) view.findViewById(R.id.fragment_player_detail_tv_catches);
        tvRunOuts = (TextView) view.findViewById(R.id.fragment_player_detail_tv_run_outs);
        tvStumps = (TextView) view.findViewById(R.id.fragment_player_detail_tv_stumps);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (memberDetail != null) {
            int mMatchesPlayed = memberDetail.getiMatchesPlayed();
            int mBallsFaced = memberDetail.getiBallsFaced();
            int mRunsScored = memberDetail.getiRunsScored();
            int mFours = memberDetail.getiFours();
            int mOversBowled = memberDetail.getiOversBowled();
            int mWicketsTook = memberDetail.getiWicketsTook();
            int mMaidens = memberDetail.getiMaindens();
            int mCatches = memberDetail.getiCatches();
            int mRunOuts = memberDetail.getiRunouts();
            int mStumps = memberDetail.getiStumps();

            tvBattingMatchesPlayed.setText(String.valueOf(mMatchesPlayed));
            tvBallsFaced.setText(String.valueOf(mBallsFaced));
            tvRunsScored.setText(String.valueOf(mRunsScored));
            tvFours.setText(String.valueOf(mFours));
            tvOversBowled.setText(String.valueOf(mOversBowled));
            tvWicketsTook.setText(String.valueOf(mWicketsTook));
            tvMaidens.setText(String.valueOf(mMaidens));
            tvCatches.setText(String.valueOf(mCatches));
            tvRunOuts.setText(String.valueOf(mRunOuts));
            tvStumps.setText(String.valueOf(mStumps));
        }
    }
}

package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.models.MemberStats;

/**
 * Created by rakshith on 3/14/17.
 */
public class EditTeamMemberStatsFragment extends BaseFragment implements View.OnClickListener {
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    TextView tvPlayerName;
    TextView tvMatchesPlayed;
    TextView tvBallsFaced;
    TextView tvRunsScored;
    TextView tvFours;
    TextView tvOversBowled;
    TextView tvWicketsTook;
    TextView tvMaindens;
    TextView tvCatches;
    TextView tvRunouts;
    TextView tvStumps;

    TextView tvSubmit;

    TextInputLayout tilMatchesPlayed;
    TextInputLayout tilBallsFaced;
    TextInputLayout tilRunsScored;
    TextInputLayout tilFours;
    TextInputLayout tilOversBowled;
    TextInputLayout tilWicketsTook;
    TextInputLayout tilMaindens;
    TextInputLayout tilCatches;
    TextInputLayout tilRunouts;
    TextInputLayout tilStumps;

    EditText etMatchesPlayed;
    EditText etBallsFaced;
    EditText etRunsScored;
    EditText etFours;
    EditText etOversBowled;
    EditText etWicketsTook;
    EditText etMaindens;
    EditText etCatches;
    EditText etRunouts;
    EditText etStumps;

    String mTeamName;
    String mPlayerName;

    int iMatchesPlayed = 0;
    int iBallsFaced = 0;
    int iRunsScored = 0;
    int iFours = 0;
    int iOversBowled = 0;
    int iWicketsTook = 0;
    int iMaindens = 0;
    int iCatches = 0;
    int iRunouts = 0;
    int iStumps = 0;

    int iPreviousMatchesPlayed = 0;
    int iPreviousBallsFaced = 0;
    int iPreviousRunsScored = 0;
    int iPreviousFours = 0;
    int iPreviousOversBowled = 0;
    int iPreviousWicketsTook = 0;
    int iPreviousMaindens = 0;
    int iPreviousCatches = 0;
    int iPreviousRunouts = 0;
    int iPreviousStumps = 0;
    private String year = Constants.PARAM_YEAR_2018;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_member_stats, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mTeamName = bundle.getString(Constants.TEAM_NAME);
            mPlayerName = bundle.getString(Constants.PLAYER_NAME);
        }

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        tvPlayerName = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_player_name);
        tvMatchesPlayed = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_matches_played);
        tvBallsFaced = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_balls_faced);
        tvRunsScored = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_runs_scored);
        tvFours = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_fours);
        tvOversBowled = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_overs_bowled);
        tvWicketsTook = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_wickets);
        tvMaindens = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_maidens);
        tvCatches = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_catches);
        tvRunouts = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_run_outs);
        tvStumps = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_stumps);
        tvSubmit = (TextView) view.findViewById(R.id.fragment_edit_member_stats_tv_submit);

        tilMatchesPlayed = (TextInputLayout) view.findViewById(R.id.fragment_edit_member_stats_til_matches_played);
        tilBallsFaced = (TextInputLayout) view.findViewById(R.id.fragment_edit_member_stats_til_balls_faced);
        tilRunsScored = (TextInputLayout) view.findViewById(R.id.fragment_edit_member_stats_til_runs_scored);
        tilFours = (TextInputLayout) view.findViewById(R.id.fragment_edit_member_stats_til_fours);
        tilOversBowled = (TextInputLayout) view.findViewById(R.id.fragment_edit_member_stats_til_overs_bowled);
        tilWicketsTook = (TextInputLayout) view.findViewById(R.id.fragment_edit_member_stats_til_wickets);
        tilMaindens = (TextInputLayout) view.findViewById(R.id.fragment_edit_member_stats_til_maidens);
        tilCatches = (TextInputLayout) view.findViewById(R.id.fragment_edit_member_stats_til_catches);
        tilRunouts = (TextInputLayout) view.findViewById(R.id.fragment_edit_member_stats_til_run_outs);
        tilStumps = (TextInputLayout) view.findViewById(R.id.fragment_edit_member_stats_til_stumps);

        etMatchesPlayed = (EditText) view.findViewById(R.id.fragment_edit_member_stats_et_matches_played);
        etBallsFaced = (EditText) view.findViewById(R.id.fragment_edit_member_stats_et_balls_faced);
        etRunsScored = (EditText) view.findViewById(R.id.fragment_edit_member_stats_et_runs_scored);
        etFours = (EditText) view.findViewById(R.id.fragment_edit_member_stats_et_fours);
        etOversBowled = (EditText) view.findViewById(R.id.fragment_edit_member_stats_et_overs_bowled);
        etWicketsTook = (EditText) view.findViewById(R.id.fragment_edit_member_stats_et_wickets);
        etMaindens = (EditText) view.findViewById(R.id.fragment_edit_member_stats_et_maidens);
        etCatches = (EditText) view.findViewById(R.id.fragment_edit_member_stats_et_catches);
        etRunouts = (EditText) view.findViewById(R.id.fragment_edit_member_stats_et_run_outs);
        etStumps = (EditText) view.findViewById(R.id.fragment_edit_member_stats_et_stumps);

        tvSubmit.setOnClickListener(this);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        year = Constants.getSharedPrefrenceString(mActivity, Constants.PARAM_YEAR);

        if (!TextUtils.isEmpty(mPlayerName) && !TextUtils.isEmpty(mTeamName)) {
            tvPlayerName.setText(mPlayerName);

            String playerNameTeamName = mPlayerName + "_" + mTeamName;
            databaseReference.child(year).child(Constants.DB_PLAYER_STATS).child(playerNameTeamName)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            MemberStats memberStats = dataSnapshot.getValue(MemberStats.class);
                            setPreviousStats(memberStats);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }
    }

    private void setPreviousStats(MemberStats memberStats) {
        if (memberStats != null) {
            iPreviousMatchesPlayed = memberStats.getiMatchesPlayed();
            iPreviousBallsFaced = memberStats.getiBallsFaced();
            iPreviousRunsScored = memberStats.getiRunsScored();
            iPreviousFours = memberStats.getiFours();
            iPreviousOversBowled = memberStats.getiOversBowled();
            iPreviousWicketsTook = memberStats.getiWicketsTook();
            iPreviousMaindens = memberStats.getiMaindens();
            iPreviousCatches = memberStats.getiCatches();
            iPreviousRunouts = memberStats.getiRunouts();
            iPreviousStumps = memberStats.getiStumps();

            String mPreviousMatchesPlayed = String.valueOf(iPreviousMatchesPlayed);
            String mPreviousBallsFaced = String.valueOf(iPreviousBallsFaced);
            String mPreviousRunsScored = String.valueOf(iPreviousRunsScored);
            String mPreviousFours = String.valueOf(iPreviousFours);
            String mPreviousOversBowled = String.valueOf(iPreviousOversBowled);
            String mPreviousWicketsTook = String.valueOf(iPreviousWicketsTook);
            String mPreviousMaindens = String.valueOf(iPreviousMaindens);
            String mPreviousCatches = String.valueOf(iPreviousCatches);
            String mPreviousRunouts = String.valueOf(iPreviousRunouts);
            String mPreviousStumps = String.valueOf(iPreviousStumps);

            if (!TextUtils.isEmpty(mPlayerName))
                tvPlayerName.setText(mPlayerName);
            if (!TextUtils.isEmpty(mPreviousMatchesPlayed))
                tvMatchesPlayed.setText(mPreviousMatchesPlayed);
            if (!TextUtils.isEmpty(mPreviousBallsFaced))
                tvBallsFaced.setText(mPreviousBallsFaced);
            if (!TextUtils.isEmpty(mPreviousRunsScored))
                tvRunsScored.setText(mPreviousRunsScored);
            if (!TextUtils.isEmpty(mPreviousFours))
                tvFours.setText(mPreviousFours);
            if (!TextUtils.isEmpty(mPreviousOversBowled))
                tvOversBowled.setText(mPreviousOversBowled);
            if (!TextUtils.isEmpty(mPreviousWicketsTook))
                tvWicketsTook.setText(mPreviousWicketsTook);
            if (!TextUtils.isEmpty(mPreviousMaindens))
                tvMaindens.setText(mPreviousMaindens);
            if (!TextUtils.isEmpty(mPreviousCatches))
                tvCatches.setText(mPreviousCatches);
            if (!TextUtils.isEmpty(mPreviousRunouts))
                tvRunouts.setText(mPreviousRunouts);
            if (!TextUtils.isEmpty(mPreviousStumps))
                tvStumps.setText(mPreviousStumps);
        }
    }

    @Override
    public void onClick(View v) {
        submitPlayerStats();
        ((HomeActivity) mActivity).hideKeyBoard();
    }

    private void submitPlayerStats() {
        if (!TextUtils.isEmpty(mTeamName) && !TextUtils.isEmpty(mPlayerName)) {
            iMatchesPlayed = Integer.valueOf(etMatchesPlayed.getText().toString().trim()) + iPreviousMatchesPlayed;
            iBallsFaced = Integer.valueOf(etBallsFaced.getText().toString().trim()) + iPreviousBallsFaced;
            iRunsScored = Integer.valueOf(etRunsScored.getText().toString().trim()) + iPreviousRunsScored;
            iFours = Integer.valueOf(etFours.getText().toString().trim()) + iPreviousFours;
            iOversBowled = Integer.valueOf(etOversBowled.getText().toString().trim()) + iPreviousOversBowled;
            iWicketsTook = Integer.valueOf(etWicketsTook.getText().toString().trim()) + iPreviousWicketsTook;
            iMaindens = Integer.valueOf(etMaindens.getText().toString().trim()) + iPreviousMaindens;
            iCatches = Integer.valueOf(etCatches.getText().toString().trim()) + iPreviousCatches;
            iRunouts = Integer.valueOf(etRunouts.getText().toString().trim()) + iPreviousRunouts;
            iStumps = Integer.valueOf(etStumps.getText().toString().trim()) + iPreviousStumps;


            final MemberStats memberStats = new MemberStats(mPlayerName, mTeamName, iMatchesPlayed, iBallsFaced
                    , iRunsScored, iFours, iOversBowled, iWicketsTook, iMaindens, iCatches, iRunouts, iStumps);

            String playerNameTeamName = mPlayerName + "_" + mTeamName;

            databaseReference.child(year).child(Constants.DB_PLAYER_STATS).child(playerNameTeamName).setValue(memberStats).addOnCompleteListener(
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            if (task.isSuccessful()) {
                                ((HomeActivity) mActivity).popCurrentFragment();
                            }
                        }
                    }
            );
        }
    }
}

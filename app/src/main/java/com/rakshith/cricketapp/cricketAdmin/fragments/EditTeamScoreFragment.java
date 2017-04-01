package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.models.TeamScore;

import static com.rakshith.cricketapp.R.string.draw;
import static com.rakshith.cricketapp.R.string.wins;

/**
 * Created by rakshith on 3/12/17.
 */
public class EditTeamScoreFragment extends BaseFragment implements View.OnClickListener {
    TextView tvTeamName;
    TextInputLayout tilMatchesPlayed;
    //    TextInputLayout tilMatchesWin;
//    TextInputLayout tilMatchesLost;
    TextInputLayout tilScoreFor;
    TextInputLayout tilScoreAgainst;
    TextInputLayout tilTotalPoints;

    EditText etMatchesPlayed;
    //    EditText etMatchesWin;
//    EditText etMatchesLost;
    EditText etScoreFor;
    EditText etScoreAgainst;
    EditText etTotalPoints;

    TextView tvTotalRunsFor;
    TextView tvTotalWicketsLost;
    TextView tvTotalRunsAgainst;
    TextView tvTotalWicketsTook;
    TextView tvTotalPoints;

    TextView tvSubmit;
    private String teamName;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private TextInputLayout tilWicketsLost;
    private TextInputLayout tilWicketsTook;
    private EditText etWicketsLost;
    private EditText etWicketsTook;
    private int matchesPlayed = -1;
    private int wins = -1;
    private int lost = -1;
    private int scoreFor = -1;
    private int wicketsLost = -1;
    private int scoreAgainst = -1;
    private int wicketsTook = -1;
    private int totalPoints = -1;
    private TeamScore teamScore;

    private int previousWins;
    private int previousLost;
    private int previousRunsFor = 0;
    private int previousWicketsLost = 0;
    private int previousRunsAgainst = 0;
    private int previousWicketsTook = 0;
    private int previousPoints = 0;
    private RadioGroup rgWinLost;
    private String winLost;
    private String cityName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_team_score, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            teamName = bundle.getString(Constants.TEAM_NAME);
            cityName = bundle.getString(Constants.CITY_NAME);
        }

        tvTeamName = (TextView) view.findViewById(R.id.fragment_edit_team_score_tv_team_name);
        tilMatchesPlayed = (TextInputLayout) view.findViewById(R.id.fragment_edit_team_score_til_matches_played);
//        tilMatchesWin = (TextInputLayout) view.findViewById(R.id.fragment_edit_team_score_til_wins);
//        tilMatchesLost = (TextInputLayout) view.findViewById(R.id.fragment_edit_team_score_til_losses);
        tilScoreFor = (TextInputLayout) view.findViewById(R.id.fragment_edit_team_score_til_runs_for);
        tilWicketsLost = (TextInputLayout) view.findViewById(R.id.fragment_edit_team_score_til_wickets_lost);
        tilScoreAgainst = (TextInputLayout) view.findViewById(R.id.fragment_edit_team_score_til_runs_against);
        tilWicketsTook = (TextInputLayout) view.findViewById(R.id.fragment_edit_team_score_til_wickets_took);
        tvTotalPoints = (TextView) view.findViewById(R.id.fragment_edit_team_score_tv_points);

        etMatchesPlayed = (EditText) view.findViewById(R.id.fragment_edit_team_score_et_matches_played);
//        etMatchesWin = (EditText) view.findViewById(R.id.fragment_edit_team_score_et_wins);
//        etMatchesLost = (EditText) view.findViewById(R.id.fragment_edit_team_score_et_losses);
        etScoreFor = (EditText) view.findViewById(R.id.fragment_edit_team_score_et_runs_for);
        etWicketsLost = (EditText) view.findViewById(R.id.fragment_edit_team_score_et_wickets_lost);
        etScoreAgainst = (EditText) view.findViewById(R.id.fragment_edit_team_score_et_runs_against);
        etWicketsTook = (EditText) view.findViewById(R.id.fragment_edit_team_score_et_wickets_took);

        tvTotalRunsFor = (TextView) view.findViewById(R.id.fragment_edit_team_score_tv_runs_for);
        tvTotalWicketsLost = (TextView) view.findViewById(R.id.fragment_edit_team_score_tv_wickets_lost);
        tvTotalRunsAgainst = (TextView) view.findViewById(R.id.fragment_edit_team_score_tv_runs_against);
        tvTotalWicketsTook = (TextView) view.findViewById(R.id.fragment_edit_team_score_tv_wickets_took);
        tvTotalPoints = (TextView) view.findViewById(R.id.fragment_edit_team_score_tv_total_points);

        rgWinLost = (RadioGroup) view.findViewById(R.id.fragment_edit_team_score_rg_win_lost);

        tvSubmit = (TextView) view.findViewById(R.id.fragment_edit_team_score_tv_submit);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        getTeamScore();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                tvTeamName.setText(teamName);
                if (teamScore != null) {
                    previousWins = teamScore.getWins();
                    previousLost = teamScore.getLost();
                    previousRunsFor = teamScore.getRunsFor();
                    previousWicketsLost = teamScore.getWicketsLost();
                    previousRunsAgainst = teamScore.getRunsAgainst();
                    previousWicketsTook = teamScore.getWicketsTook();
                    previousPoints = teamScore.getTotalPoints();

                    tvTotalRunsFor.setText(String.valueOf(previousRunsFor));
                    tvTotalWicketsLost.setText(String.valueOf(previousWicketsLost));
                    tvTotalRunsAgainst.setText(String.valueOf(previousRunsAgainst));
                    tvTotalWicketsTook.setText(String.valueOf(previousWicketsTook));
                    tvTotalPoints.setText(String.valueOf(previousPoints));
                }
            }
        }, 500);

        rgWinLost.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.fragment_edit_team_score_rb_win:
                        winLost = Constants.WIN;
                        break;
                    case R.id.fragment_edit_team_score_rb_lost:
                        winLost = Constants.LOST;
                        break;
                    case R.id.fragment_edit_team_score_rb_draw:
                        winLost = Constants.DRAW;
                        break;
                    default:
                        break;
                }
            }
        });

        tvSubmit.setOnClickListener(this);
    }

    private void getTeamScore() {
        databaseReference.child(Constants.DB_TEAMS_SCORE).child(teamName).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildren() != null) {
                    teamScore = dataSnapshot.getValue(TeamScore.class);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        SubmitTeamScore();
    }

    private void SubmitTeamScore() {
        String matchesPlayedText = etMatchesPlayed.getText().toString();
//        String winsText = etMatchesWin.getText().toString();
//        String lostText = etMatchesLost.getText().toString();
        String scoreForText = etScoreFor.getText().toString();
        String wicketsLostText = etWicketsLost.getText().toString();
        String scoreAgainstText = etScoreAgainst.getText().toString();
        String wicketsTookText = etWicketsTook.getText().toString();

        if (!TextUtils.isEmpty(matchesPlayedText))
            matchesPlayed = Integer.parseInt(matchesPlayedText);
        if (!TextUtils.isEmpty(winLost)) {
            if (winLost == Constants.WIN) {
                wins = previousWins + 1;
                lost = previousLost;
                totalPoints = previousPoints + 2;
            } else if (winLost == Constants.LOST) {
                lost = previousLost + 1;
                wins = previousWins;
                totalPoints = previousPoints;
            } else if (winLost == Constants.DRAW) {
                wins = previousWins;
                lost = previousLost;
                totalPoints = previousPoints + 1;
            }
        }
        if (!TextUtils.isEmpty(scoreForText))
            scoreFor = Integer.parseInt(scoreForText);
        if (!TextUtils.isEmpty(wicketsLostText))
            wicketsLost = Integer.parseInt(wicketsLostText);
        if (!TextUtils.isEmpty(scoreAgainstText))
            scoreAgainst = Integer.parseInt(scoreAgainstText);
        if (!TextUtils.isEmpty(wicketsTookText))
            wicketsTook = Integer.parseInt(wicketsTookText);

        if (validate()) {
            updateScore();
        }
    }

    private void updateScore() {
        scoreFor = previousRunsFor + scoreFor;
        wicketsLost = previousWicketsLost + wicketsLost;
        scoreAgainst = previousRunsAgainst + scoreAgainst;
        wicketsTook = previousWicketsTook + wicketsTook;
//        totalPoints = previousPoints + totalPoints;

        TeamScore teamScore = new TeamScore(teamName, cityName, matchesPlayed, wins, lost, scoreFor, wicketsLost, scoreAgainst,
                wicketsTook, totalPoints);
        databaseReference.child(Constants.DB_TEAMS_SCORE).child(teamName).setValue(teamScore);

        ((HomeActivity) getActivity()).popCurrentFragment();
    }

    private boolean validate() {
        if (matchesPlayed == -1) {
            tilMatchesPlayed.setError(getResources().getString(R.string.hint_matches_played));
            return false;
        }
        if (scoreFor == -1) {
            tilScoreFor.setError(getResources().getString(R.string.hint_runs_for));
            return false;
        }
        if (wicketsLost == -1) {
            tilWicketsLost.setError(getResources().getString(R.string.hint_wickets_lost));
            return false;
        }
        if (scoreAgainst == -1) {
            tilScoreAgainst.setError(getResources().getString(R.string.hint_runs_against));
            return false;
        }
        if (wicketsTook == -1) {
            tilWicketsTook.setError(getResources().getString(R.string.hint_wickets_took));
            return false;
        }
        if (totalPoints == -1) {
            tilTotalPoints.setError(getResources().getString(R.string.hint_total_points));
            return false;
        }
        return true;
    }
}

package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.models.MatchList;
import com.rakshith.cricketapp.cricketAdmin.models.TeamList;

import java.util.ArrayList;

/**
 * Created by rakshith on 3/26/17.
 */
public class EditMatchesFragment extends BaseFragment implements View.OnClickListener {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView tvMatchNumber;
    CardView cvSubmit;

    TextInputLayout tilTeamOneRuns;
    TextInputLayout tilTeamOneWickets;
    TextInputLayout tilTeamTwoRuns;
    TextInputLayout tilTeamTwoWickets;
    TextInputLayout tilWinByRunsWickets;
    TextInputLayout tilManOfTheMatch;

    EditText etTeamOneRuns;
    EditText etTeamOneWickets;
    EditText etTeamTwoRuns;
    EditText etTeamTwoWickets;
    EditText etWinByRunsWickets;
    EditText etManOfTheMatch;

    TextView tvTeamOneName;
    TextView tvTeamTwoName;

    Spinner spTeamTossWinTeam;
    Spinner spTeamMatchWinTeam;
    Spinner spRunsWickets;
    private ArrayList<String> teamNames;

    private String teamOneName;
    private String teamOneRuns;
    private String teamOneWickets;
    private String teamTwoName;
    private String teamTwoRuns;
    private String teamTwoWickets;
    private String tossWinByTeam;
    private String matchWinByTeam;
    private String winByRunsWickets;
    private String runsWickets;
    private String manOfTheMatch;
    private String matchNumber;
    private LinearLayout llTossWin;

    MatchList matchList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_matches, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Bundle bundle = getArguments();
        if (bundle != null) {
            matchList = bundle.getParcelable(Constants.MATCH_DETAIL);
        }

        getTeams();

        tvMatchNumber = (TextView) view.findViewById(R.id.fragment_enter_matches_tv_match_num);
        cvSubmit = (CardView) view.findViewById(R.id.fragment_enter_matches_cv_submit);

        tilTeamOneRuns = (TextInputLayout) view.findViewById(R.id.fragment_enter_matches_til_team_one_runs);
        tilTeamOneWickets = (TextInputLayout) view.findViewById(R.id.fragment_enter_matches_til_team_one_wickets);
        tilTeamTwoRuns = (TextInputLayout) view.findViewById(R.id.fragment_enter_matches_til_team_two_runs);
        tilTeamTwoWickets = (TextInputLayout) view.findViewById(R.id.fragment_enter_matches_til_team_two_wickets);
        tilWinByRunsWickets = (TextInputLayout) view.findViewById(R.id.fragment_enter_matches_til_runs_wickets);
        tilManOfTheMatch = (TextInputLayout) view.findViewById(R.id.fragment_enter_matches_til_mom);

        etTeamOneRuns = (EditText) view.findViewById(R.id.fragment_enter_matches_et_team_one_runs);
        etTeamOneWickets = (EditText) view.findViewById(R.id.fragment_enter_matches_et_team_one_wickets);
        etTeamTwoRuns = (EditText) view.findViewById(R.id.fragment_enter_matches_et_team_two_runs);
        etTeamTwoWickets = (EditText) view.findViewById(R.id.fragment_enter_matches_et_team_two_wickets);
        etWinByRunsWickets = (EditText) view.findViewById(R.id.fragment_enter_matches_et_runs_wickets);
        etManOfTheMatch = (EditText) view.findViewById(R.id.fragment_enter_matches_et_mom);

        tvTeamOneName = (TextView) view.findViewById(R.id.fragment_enter_matches_tv_team_one);
        tvTeamTwoName = (TextView) view.findViewById(R.id.fragment_enter_matches_tv_team_two);

        spTeamTossWinTeam = (Spinner) view.findViewById(R.id.fragment_enter_matches_sp_team_toss_win);
        spTeamMatchWinTeam = (Spinner) view.findViewById(R.id.fragment_enter_matches_sp_team_match_win);
        spRunsWickets = (Spinner) view.findViewById(R.id.fragment_enter_matches_sp_runs_wickets);

        llTossWin = (LinearLayout) view.findViewById(R.id.fragment_enter_matches_ll_toss_win);

        cvSubmit.setOnClickListener(this);

        return view;
    }

    private void getTeams() {
        databaseReference.child(Constants.DB_TEAM).addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        teamNames = new ArrayList<>();
                        for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                            TeamList teamList = noteDataSnapshot.getValue(TeamList.class);
                            teamNames.add(teamList.getTeamName());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
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
        manOfTheMatch = matchList.getManOfTheMatch();

        if (!TextUtils.isEmpty(matchNumber))
            tvMatchNumber.setText(matchNumber);
        if (!TextUtils.isEmpty(teamOneName))
            tvTeamOneName.setText(teamOneName);
        if (!TextUtils.isEmpty(teamOneRuns))
            etTeamOneRuns.setText(teamOneRuns);
        if (!TextUtils.isEmpty(teamOneWickets))
            etTeamOneWickets.setText(teamOneWickets);
        if (!TextUtils.isEmpty(teamTwoName))
            tvTeamTwoName.setText(teamTwoName);
        if (!TextUtils.isEmpty(teamTwoRuns))
            etTeamTwoRuns.setText(teamTwoRuns);
        if (!TextUtils.isEmpty(teamTwoWickets))
            etTeamTwoWickets.setText(teamTwoWickets);
        if (!TextUtils.isEmpty(winByRunsWickets))
            etWinByRunsWickets.setText(winByRunsWickets);
        if (!TextUtils.isEmpty(manOfTheMatch))
            etManOfTheMatch.setText(manOfTheMatch);

        displayTossWinLayout();

        ArrayList<String> runsWickets = new ArrayList<>();
        runsWickets.add(Constants.RUN);
        runsWickets.add(Constants.WICKET);
        runsWickets.add(Constants.RUNS);
        runsWickets.add(Constants.WICKETS);

        ArrayAdapter<String> runsWicketsAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, runsWickets);
        // Drop down layout style - list view with radio button
        runsWicketsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spRunsWickets.setAdapter(runsWicketsAdapter);

        setOnItemSelectedForSpinner();
    }

    private void setOnItemSelectedForSpinner() {
        spTeamTossWinTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tossWinByTeam = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spTeamMatchWinTeam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matchWinByTeam = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spRunsWickets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                runsWickets = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void displayTossWinLayout() {
        if (!TextUtils.isEmpty(teamOneName) && !TextUtils.isEmpty(teamTwoName)) {
            llTossWin.setVisibility(View.VISIBLE);

            ArrayList<String> teamsPlayingInMatchList = new ArrayList<>();
            teamsPlayingInMatchList.add(teamOneName);
            teamsPlayingInMatchList.add(teamTwoName);
            ArrayAdapter<String> teamsPlayingAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, teamsPlayingInMatchList);
            teamsPlayingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spTeamTossWinTeam.setAdapter(teamsPlayingAdapter);
            spTeamMatchWinTeam.setAdapter(teamsPlayingAdapter);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_enter_matches_cv_submit:
//                if (validate()) {
                getScores();
                submitMatches();
//                }
                break;
            default:
                break;
        }
    }

    private void getScores() {
        String mTeamOneRun = etTeamOneRuns.getText().toString();
        String mTeamOneWickets = etTeamOneWickets.getText().toString();
        String mTeamTwoRun = etTeamTwoRuns.getText().toString();
        String mTeamTwoWickets = etTeamTwoWickets.getText().toString();
        String mWinByRunsWickets = etWinByRunsWickets.getText().toString();
        String mManOfTheMatch = etManOfTheMatch.getText().toString();

        if (!TextUtils.isEmpty(mTeamOneRun))
            teamOneRuns = mTeamOneRun;
        if (!TextUtils.isEmpty(mTeamOneWickets))
            teamOneWickets = mTeamOneWickets;
        if (!TextUtils.isEmpty(mTeamTwoRun))
            teamTwoRuns = mTeamTwoRun;
        if (!TextUtils.isEmpty(mTeamTwoWickets))
            teamTwoWickets = mTeamTwoWickets;
        if (!TextUtils.isEmpty(mWinByRunsWickets))
            winByRunsWickets = mWinByRunsWickets;
        if (!TextUtils.isEmpty(mManOfTheMatch))
            manOfTheMatch = mManOfTheMatch;
    }

    private void submitMatches() {
        final MatchList matchList = new MatchList(matchNumber, teamOneName, teamOneRuns, teamOneWickets, teamTwoName, teamTwoRuns, teamTwoWickets, tossWinByTeam, matchWinByTeam, winByRunsWickets, manOfTheMatch);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                databaseReference.child(Constants.DB_MATCHES).child(matchNumber).setValue(matchList);
                ((HomeActivity) getActivity()).popCurrentFragment();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Rakshith", "database error " + databaseError.getMessage());
            }
        });
    }

    private boolean validate() {
        if (TextUtils.isEmpty(teamOneName)) {
            return false;
        } else if (TextUtils.isEmpty(teamTwoName)) {
            return false;
        } else if (TextUtils.isEmpty(teamOneRuns)) {
            tilTeamOneRuns.setError(getResources().getString(R.string.hint_team_score));
            return false;
        } else if (TextUtils.isEmpty(teamOneWickets)) {
            tilTeamOneWickets.setError(getResources().getString(R.string.hint_wickets_lost));
            return false;
        } else if (TextUtils.isEmpty(teamTwoRuns)) {
            tilTeamTwoRuns.setError(getResources().getString(R.string.hint_team_score));
            return false;
        } else if (TextUtils.isEmpty(teamTwoWickets)) {
            tilTeamTwoWickets.setError(getResources().getString(R.string.hint_wickets_lost));
            return false;
        } else if (TextUtils.isEmpty(tossWinByTeam)) {
            return false;
        } else if (TextUtils.isEmpty(matchWinByTeam)) {
            return false;
        } else if (TextUtils.isEmpty(runsWickets)) {
            return false;
        }
        return true;
    }
}
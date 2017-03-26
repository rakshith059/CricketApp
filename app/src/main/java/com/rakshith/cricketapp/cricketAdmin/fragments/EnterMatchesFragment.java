package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
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
import java.util.List;

/**
 * Created by rakshith on 3/26/17.
 */
public class EnterMatchesFragment extends BaseFragment implements View.OnClickListener {
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    TextView tvMatchNumber;
    CardView cvSubmit;

    Spinner spTeamOneName;
    Spinner spTeamTwoName;
    private ArrayList<String> teamNames;

    private String teamOneName;
    private String teamTwoName;
    private String matchNumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_enter_matches, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        Bundle bundle = getArguments();
        if (bundle != null) {
            matchNumber = bundle.getString(Constants.MATCH_NUM);
        }

        getTeams();

        tvMatchNumber = (TextView) view.findViewById(R.id.fragment_enter_matches_tv_match_num);
        cvSubmit = (CardView) view.findViewById(R.id.fragment_enter_matches_cv_submit);

        spTeamOneName = (Spinner) view.findViewById(R.id.fragment_enter_matches_sp_team_one_name);
        spTeamTwoName = (Spinner) view.findViewById(R.id.fragment_enter_matches_sp_team_two_name);
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

        if (!TextUtils.isEmpty(matchNumber))
            tvMatchNumber.setText(matchNumber);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (teamNames != null && teamNames.size() > 0) {
                    ArrayAdapter<String> teamAdapter = new ArrayAdapter<String>(mActivity, android.R.layout.simple_spinner_item, teamNames);
                    // Drop down layout style - list view with radio button
                    teamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // attaching data adapter to spinner
                    spTeamOneName.setAdapter(teamAdapter);
                    spTeamTwoName.setAdapter(teamAdapter);
                }
            }
        }, 500);

        setOnItemSelectedForSpinner();
    }

    private void setOnItemSelectedForSpinner() {
        spTeamOneName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teamOneName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spTeamTwoName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teamTwoName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_enter_matches_cv_submit:
//                if (validate()) {
                submitMatches();
//                }
                break;
            default:
                break;
        }
    }

    private void submitMatches() {
        final MatchList matchList = new MatchList(matchNumber, teamOneName, "", "", teamTwoName, "", "", "", "", "", "");

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
        }
        return true;
    }
}

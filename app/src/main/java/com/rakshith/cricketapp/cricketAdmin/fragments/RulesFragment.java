package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakshith.cricketapp.R;

/**
 * Created by rakshith on 5/28/16.
 */
public class RulesFragment extends Fragment {

    TextView tvValuablePlayerRules;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rules_fragment , container , false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tvValuablePlayerRules = (TextView) getView().findViewById(R.id.rules_fragment_valuable_player_rules);
    }
}

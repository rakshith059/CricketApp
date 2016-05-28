package com.example.rakshith.cricketapp.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rakshith.cricketapp.R;

/**
 * Created by rakshith on 5/28/16.
 */
public class RulesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rules_fragment , container , false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}

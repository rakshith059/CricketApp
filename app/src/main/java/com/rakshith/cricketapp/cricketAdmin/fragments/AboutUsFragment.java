package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rakshith.cricketapp.cricketAdmin.Utils.RecyclerItemDecorator;
import com.rakshith.cricketapp.cricketAdmin.adapters.RecyclerAboutUsAdapter;
import com.rakshith.cricketapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by rakshith on 5/28/16.
 */
public class AboutUsFragment extends BaseFragment {
    RecyclerView rvRecyclerView;
    RecyclerAboutUsAdapter aboutUsAdapter;

    List<String> aboutUsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_recycler_view, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvRecyclerView = (RecyclerView) getView().findViewById(R.id.common_recycler_view_rv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRecyclerView.setLayoutManager(linearLayoutManager);
        rvRecyclerView.addItemDecoration(new RecyclerItemDecorator(5));
        aboutUsList();

        Collections.sort(aboutUsList, String.CASE_INSENSITIVE_ORDER);

        aboutUsAdapter = new RecyclerAboutUsAdapter(getActivity(), aboutUsList);
        rvRecyclerView.setAdapter(aboutUsAdapter);
    }

    private void aboutUsList() {
        aboutUsList.add("KARTHIK L M");
        aboutUsList.add("NATRAJ");
        aboutUsList.add("NAVEEN");
        aboutUsList.add("NITHIN");
        aboutUsList.add("SHRAVAN");
        aboutUsList.add("SANDEEP");
        aboutUsList.add("RAKSHITH SHANKAR T R");
        aboutUsList.add("HARSHITH SHANKAR T R");
        aboutUsList.add("SAGAR");
        aboutUsList.add("SANJAY");
        aboutUsList.add("MANU");
        aboutUsList.add("MOHAN");
        aboutUsList.add("MANU MOHAN");
        aboutUsList.add("SOMASHEKAR");
        aboutUsList.add("JOSHI");
        aboutUsList.add("PRASANA");
        aboutUsList.add("CHETHAN");
        aboutUsList.add("SHARATH");
        aboutUsList.add("LOHIT");
        aboutUsList.add("KARTHIK");
        aboutUsList.add("DATHA");
        aboutUsList.add("BALAJI");
        aboutUsList.add("SHASHWAT");
        aboutUsList.add("LINGA");
        aboutUsList.add("AKARSH");
        aboutUsList.add("SHIVU");
        aboutUsList.add("HARI");
        aboutUsList.add("GIRISH");
        aboutUsList.add("ANJAN");
    }
}

package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by rakshith on 3/10/17.
 */
public class BaseFragment extends Fragment {

    Activity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
    }
}

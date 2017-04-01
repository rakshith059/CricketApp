package com.rakshith.cricketapp.cricketAdmin.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;

/**
 * Created by rakshith on 3/10/17.
 */
public class BaseFragment extends Fragment {

    Activity mActivity;
    String isUserLoggedIn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isUserLoggedIn = Constants.getSharedPrefrenceString(mActivity, Constants.IS_USER_LOGGED_IN);
    }

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

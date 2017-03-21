package com.rakshith.cricketapp.Interfaces;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by rakshith on 5/26/16.
 */
public interface FragmentCallbacks {

    public void addFragment(Fragment fragment, String mBackStack, Bundle bundle);

    public void replaceFragment(Fragment fragment, String mBackStack, Bundle bundle);

    public Fragment getmFragment();

    public void popCurrentFragment();
}

package com.example.rakshith.cricketapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.rakshith.cricketapp.Interfaces.FragmentCallbacks;
import com.parse.Parse;

import java.util.ArrayList;

/**
 * Created by rakshith on 5/26/16.
 */
public class BaseActivity extends AppCompatActivity implements FragmentCallbacks {
    AppCompatActivity                    mContext;
    static ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    static Fragment                             mFragment;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    public void addFragment(Fragment fragment , String mBackStack , Bundle bundle)
    {
        if(mContext == null)
        {
            return;
        }
        mFragmentList.add(fragment);

        FragmentTransaction fragmentTransaction = mContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contentMainflMainContainer, fragment);

        if(mBackStack != null)
        {
            fragmentTransaction.addToBackStack(mBackStack);
        }
        mFragment = fragment;
        if(bundle != null) {
            mFragment.setArguments(bundle);
        }
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment fragment , String mBackStack , Bundle bundle)
    {
        if(mContext == null)
        {
            return;
        }
        mFragmentList.add(fragment);

        FragmentTransaction fragmentTransaction = mContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentMainflMainContainer, fragment);

        if(mBackStack != null)
        {
            fragmentTransaction.addToBackStack(mBackStack);
        }
        mFragment = fragment;
        if(bundle != null) {
            mFragment.setArguments(bundle);
        }
        fragmentTransaction.commit();
    }

    public Fragment getmFragment()
    {
        if(mFragmentList.size() > 0)
        {
            return mFragmentList.get(mFragmentList.size() - 1);
        }
        return null;
    }

    public void popCurrentFragment()
    {
        if(mFragmentList.size() > 0)
        {
            mFragmentList.remove(mFragmentList.size() - 1);
            if(mFragmentList.size() > 0)
            {
                mFragmentList.remove(mFragmentList.size() - 1);
            }
        }
        mContext.getSupportFragmentManager().popBackStack();
    }

    public boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }
}

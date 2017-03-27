package com.rakshith.cricketapp.cricketAdmin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.appinvite.AppInvite;
import com.google.android.gms.appinvite.AppInviteInvitationResult;
import com.google.android.gms.appinvite.AppInviteReferral;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rakshith.cricketapp.Fragments.AboutUsFragment;
import com.rakshith.cricketapp.Fragments.RulesFragment;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.fragments.HomeFragment;

import java.util.ArrayList;

public class HomeActivity extends BaseActivity implements GoogleApiClient.OnConnectionFailedListener,
        NavigationView.OnNavigationItemSelectedListener {
    FrameLayout flMainContainer;
    ArrayList<Fragment> mFragmentList = new ArrayList<Fragment>();
    AppCompatActivity mContext;
    Fragment mFragment;
    //    private Toolbar toolbar;
    private AdView mAdView;
    private GoogleApiClient mGoogleApiClient;

    FirebaseAnalytics firebaseAnalytics;

    private StorageReference mStorageRef;
//    ViewPager vpPager;
//    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        toolbar = (Toolbar) findViewById(R.id.activity_home_detail_toolbar);
//        setSupportActionBar(toolbar);
        Toolbar toolbarMain = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mContext = this;

        firebaseAnalytics = FirebaseAnalytics.getInstance(this);

        mStorageRef = FirebaseStorage.getInstance().getReference();

        // Build GoogleApiClient with AppInvite API for receiving deep links
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(AppInvite.API)
                .build();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbarMain, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

//        vpPager = (ViewPager) findViewById(R.id.activity_main_vp_pager);
//        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
//        vpPager.setAdapter(viewPagerAdapter);

        // Check if this app was launched from a deep link. Setting autoLaunchDeepLink to true
        // would automatically launch the deep link if one is found.
        boolean autoLaunchDeepLink = false;
        AppInvite.AppInviteApi.getInvitation(mGoogleApiClient, this, autoLaunchDeepLink)
                .setResultCallback(
                        new ResultCallback<AppInviteInvitationResult>() {
                            @Override
                            public void onResult(@NonNull AppInviteInvitationResult result) {
                                if (result.getStatus().isSuccess()) {
                                    // Extract deep link from Intent
                                    Intent intent = result.getInvitationIntent();
                                    String deepLink = AppInviteReferral.getDeepLink(intent);

                                    // Handle the deep link. For example, open the linked
                                    // content, or apply promotional credit to the user's
                                    // account.

                                    // ...
                                } else {
                                    Log.d("Rakshith", "getInvitation: no deep link found.");
                                }
                            }
                        });
        addFragment(new HomeFragment(), null);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    public void addFragment(Fragment fragment, String mBackStack) {
        if (mContext == null) {
            return;
        }
        mFragmentList.add(fragment);

        FragmentTransaction fragmentTransaction = mContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.contentMainflMainContainer, fragment);

        if (mBackStack != null) {
            fragmentTransaction.addToBackStack(mBackStack);
        }
        mFragment = fragment;
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment fragment, String mBackStack, Bundle bundle) {
        if (mContext == null) {
            return;
        }
        mFragmentList.add(fragment);

        FragmentTransaction fragmentTransaction = mContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.contentMainflMainContainer, fragment);

        if (mBackStack != null) {
            fragmentTransaction.addToBackStack(mBackStack);
        }
        mFragment = fragment;
        if (bundle != null) {
            mFragment.setArguments(bundle);
        }
        fragmentTransaction.commit();
    }

    public Fragment getmFragment() {
        if (mFragmentList.size() > 0) {
            return mFragmentList.get(mFragmentList.size() - 1);
        }
        return null;
    }

    public void popCurrentFragment() {
        if (mFragmentList.size() > 0) {
            mFragmentList.remove(mFragmentList.size() - 1);
            if (mFragmentList.size() > 0) {
                mFragmentList.remove(mFragmentList.size() - 1);
            }
        }
        mContext.getSupportFragmentManager().popBackStack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mContext = null;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Bundle bundle = new Bundle();
        if (id == R.id.nav_home) {
            replaceFragment(new HomeFragment(), null, null);
            bundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_HOME);
            fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);
        } else if (id == R.id.nav_rules) {
            replaceFragment(new RulesFragment(), "rulesFragment", null);
            bundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_RULES);
            fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);
        } else if (id == R.id.nav_sponsors) {
            // TODO: 3/27/17 create sponsor fragment
            bundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_SPONSERS);
            fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);
//            replaceFragment(new StatsFragment(), "statsFragment", null);
        } else if (id == R.id.nav_about_us) {
            // TODO: 3/27/17 create about us fragment
//            replaceFragment(new AboutUsFragment(), "aboutUsFragment", null);
            bundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_ABOUT_US);
            fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);
        } else if (id == R.id.nav_location) {
            bundle.putString(Constants.PARAM_SCREEN_NAME, Constants.PARAM_SCREEN_NAME_LOCATION);
            fireBaseAnalyticsEvents(Constants.EVENT_VIEW, bundle);

            Intent intent = new Intent(this, MapActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void fireBaseAnalyticsEvents(String eventName, Bundle bundle) {
        if (bundle != null)
            firebaseAnalytics.logEvent(eventName, bundle);
    }
}

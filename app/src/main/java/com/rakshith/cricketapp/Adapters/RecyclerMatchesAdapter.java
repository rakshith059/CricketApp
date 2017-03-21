package com.rakshith.cricketapp.Adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rakshith.cricketapp.Fragments.MatchDetailFragment;
import com.rakshith.cricketapp.Interfaces.FragmentCallbacks;
import com.rakshith.cricketapp.Models.MatchesModel;
import com.rakshith.cricketapp.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

/**
 * Created by rakshith on 5/27/16.
 */
public class RecyclerMatchesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    Activity mActivity;
    List<MatchesModel> matchesList;
    FragmentCallbacks fragmentCallbacks;

    public RecyclerMatchesAdapter(Activity mActivity, List<MatchesModel> matchesList, FragmentCallbacks fragmentCallbacks) {
        this.mActivity = mActivity;
        this.matchesList = matchesList;
        this.fragmentCallbacks = fragmentCallbacks;
    }

    @Override
    public MatchesHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(mActivity).inflate(R.layout.ad_layout, parent, false);
            return new AdHolder(view);
        } else {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.matches_row, parent, false);
        return new MatchesHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        try {
            if (position == 0) {
                AdRequest adRequest = new AdRequest.Builder()
//                    .addTestDevice("847D2C1EBF23C6270BCD6AA0BE9380BC")
                        .build();
                ((AdHolder) holder).nativeAdView.loadAd(adRequest);

                ((AdHolder) holder).nativeAdProgressBar.setVisibility(View.VISIBLE);
                ((AdHolder) holder).nativeAdView.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();
                    }

                    @Override
                    public void onAdFailedToLoad(int i) {
                        super.onAdFailedToLoad(i);
                    }

                    @Override
                    public void onAdLeftApplication() {
                        super.onAdLeftApplication();
                    }

                    @Override
                    public void onAdOpened() {
                        super.onAdOpened();
                        ((AdHolder) holder).nativeAdProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAdLoaded() {
                        super.onAdLoaded();
                        ((AdHolder) holder).nativeAdProgressBar.setVisibility(View.GONE);
                    }
                });

            } else {
                MatchesModel matchesModel = matchesList.get(position);

                String matchNum = matchesModel.getMatchNo();
                String teamOne = matchesModel.getTeamOne();
                String teamTwo = matchesModel.getTeamSecond();
                String winner = matchesModel.getWinner();
                String toss = matchesModel.getToss();

                ((MatchesHolder) holder).tvMatchNo.setText(matchNum);
                ((MatchesHolder) holder).tvTeamOne.setText(teamOne);
                ((MatchesHolder) holder).tvTeamTwo.setText(teamTwo);

                if (TextUtils.isEmpty(winner)) {
                    ((MatchesHolder) holder).cvCardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.white));
                    ((MatchesHolder) holder).ivMatchDetail.setVisibility(View.GONE);
                } else {
                    ((MatchesHolder) holder).ivMatchDetail.setVisibility(View.VISIBLE);
                    ((MatchesHolder) holder).cvCardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.light_grey));
                    ((MatchesHolder) holder).cvCardView.setOnClickListener(this);
                    ((MatchesHolder) holder).cvCardView.setTag(matchesModel);
                }
                if (TextUtils.isEmpty(toss)) {
                    ((MatchesHolder) holder).vDivider.setVisibility(View.GONE);
                    ((MatchesHolder) holder).tvToss.setVisibility(View.GONE);
                } else {
                    ((MatchesHolder) holder).vDivider.setVisibility(View.VISIBLE);
                    ((MatchesHolder) holder).tvToss.setVisibility(View.VISIBLE);
                    ((MatchesHolder) holder).tvToss.setText(toss);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }

    @Override
    public void onClick(View view) {
        MatchesModel matchesModel = (MatchesModel) view.getTag();
        Bundle bundle = new Bundle();
        bundle.putParcelable("matchDetail", matchesModel);
        fragmentCallbacks.replaceFragment(new MatchDetailFragment(), "matchDetail", bundle);
    }

    class MatchesHolder extends RecyclerView.ViewHolder {
        TextView tvMatchNo;
        TextView tvTeamOne;
        TextView tvTeamTwo;
        CardView cvCardView;
        TextView tvToss;
        ImageView ivMatchDetail;
        View vDivider;

        public MatchesHolder(View itemView) {
            super(itemView);

            cvCardView = (CardView) itemView.findViewById(R.id.matches_row_cv_cardview);
            tvMatchNo = (TextView) itemView.findViewById(R.id.matches_row_tv_match_num);
            tvTeamOne = (TextView) itemView.findViewById(R.id.matches_row_tv_team_one);
            tvTeamTwo = (TextView) itemView.findViewById(R.id.matches_row_tv_team_two);
            tvToss = (TextView) itemView.findViewById(R.id.matches_row_tv_toss);
            ivMatchDetail = (ImageView) itemView.findViewById(R.id.matches_row_iv_detail);
            vDivider = itemView.findViewById(R.id.matches_row_view);
        }
    }

    private class AdHolder extends MatchesHolder {
        AdView nativeAdView;
        ProgressBar nativeAdProgressBar;
        RelativeLayout rlContainer;

        public AdHolder(View view) {
            super(view);
            rlContainer = (RelativeLayout) itemView.findViewById(R.id.ad_layout_rl_container);
            nativeAdView = (AdView) itemView.findViewById(R.id.ad_layout_native_ad);
            nativeAdProgressBar = (ProgressBar) itemView.findViewById(R.id.ad_layout_progress_bar);
        }
    }
}

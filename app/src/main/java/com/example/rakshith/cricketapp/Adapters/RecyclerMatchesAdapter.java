package com.example.rakshith.cricketapp.Adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rakshith.cricketapp.Fragments.MatchDetailFragment;
import com.example.rakshith.cricketapp.Interfaces.FragmentCallbacks;
import com.example.rakshith.cricketapp.Models.MatchesModel;
import com.example.rakshith.cricketapp.R;

import java.util.List;

/**
 * Created by rakshith on 5/27/16.
 */
public class RecyclerMatchesAdapter extends RecyclerView.Adapter<RecyclerMatchesAdapter.MatchesHolder> implements View.OnClickListener{
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
        View view = LayoutInflater.from(mActivity).inflate(R.layout.matches_row , parent , false);
        return new MatchesHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchesHolder holder, int position) {
        MatchesModel matchesModel = matchesList.get(position);

        String matchNum = matchesModel.getMatchNo();
        String teamOne = matchesModel.getTeamOne();
        String teamTwo = matchesModel.getTeamSecond();
        String winner = matchesModel.getWinner();
        String toss = matchesModel.getToss();

        holder.tvMatchNo.setText(matchNum);
        holder.tvTeamOne.setText(teamOne);
        holder.tvTeamTwo.setText(teamTwo);

        if(TextUtils.isEmpty(winner)){
            holder.cvCardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.white));
        }
        else{
            holder.cvCardView.setCardBackgroundColor(mActivity.getResources().getColor(R.color.light_grey));
            holder.cvCardView.setOnClickListener(this);
            holder.cvCardView.setTag(matchesModel);
        }
        if(TextUtils.isEmpty(toss)){
            holder.tvToss.setVisibility(View.GONE);
        }
        else{
            holder.tvToss.setVisibility(View.VISIBLE);
            holder.tvToss.setText(toss);
        }
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }

    @Override
    public void onClick(View view) {
        MatchesModel matchesModel = (MatchesModel) view.getTag();
        Bundle bundle = new Bundle();
        bundle.putParcelable("matchDetail" , matchesModel);
        fragmentCallbacks.replaceFragment(new MatchDetailFragment() , "matchDetail" , bundle);
    }

    class MatchesHolder extends RecyclerView.ViewHolder {
        TextView tvMatchNo;
        TextView tvTeamOne;
        TextView tvTeamTwo;
        CardView cvCardView;
        TextView tvToss;

        public MatchesHolder(View itemView) {
            super(itemView);

            cvCardView = (CardView) itemView.findViewById(R.id.matches_row_cv_cardview);
            tvMatchNo = (TextView) itemView.findViewById(R.id.matches_row_tv_match_num);
            tvTeamOne = (TextView) itemView.findViewById(R.id.matches_row_tv_team_one);
            tvTeamTwo = (TextView) itemView.findViewById(R.id.matches_row_tv_team_two);
            tvToss = (TextView) itemView.findViewById(R.id.matches_row_tv_toss);
        }
    }
}

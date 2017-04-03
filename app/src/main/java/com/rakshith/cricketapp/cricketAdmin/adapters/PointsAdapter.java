package com.rakshith.cricketapp.cricketAdmin.adapters;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.activities.HomeActivity;
import com.rakshith.cricketapp.cricketAdmin.fragments.TeamsStandingDetailFragment;
import com.rakshith.cricketapp.cricketAdmin.fragments.TeamsStandingFragment;
import com.rakshith.cricketapp.cricketAdmin.models.TeamScore;

import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.PointsHolder> implements View.OnClickListener {
    Activity mActivity;
    List<TeamScore> pointsList;

    String teamName;
    int playedMatches;
    int matchesWin;
    int matchesLost;
    int points;

    public PointsAdapter(Activity mActivity, List<TeamScore> list) {
        this.mActivity = mActivity;
        this.pointsList = list;
    }

    @Override
    public PointsAdapter.PointsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.player_stats_item_row, parent, false);
        return new PointsHolder(view);
    }

    @Override
    public void onBindViewHolder(PointsAdapter.PointsHolder holder, int position) {
        TeamScore teamScore = pointsList.get(position);
        teamName = teamScore.getTeamName();
        playedMatches = teamScore.getMatchesPlayed();
        matchesWin = teamScore.getWins();
        matchesLost = teamScore.getLost();
        points = teamScore.getTotalPoints();
        String cityName = teamScore.getCityName();

        if (!TextUtils.isEmpty(teamName))
            holder.tvTeamName.setText(teamName);
        if (!TextUtils.isEmpty(cityName))
            holder.tvCityName.setText(cityName);
        holder.tvPlayedMatches.setText(String.valueOf(playedMatches));
        holder.tvWins.setText(String.valueOf(matchesWin));
        holder.tvLoss.setText(String.valueOf(matchesLost));
        holder.tvPoints.setText(String.valueOf(points));

        holder.cvMainContainer.setTag(teamScore);
        holder.cvMainContainer.setOnClickListener(this);

        Constants constants = new Constants();
        constants.setFadeAnimation(holder.itemView);
    }

    @Override
    public int getItemCount() {
        return pointsList.size();
    }

    @Override
    public void onClick(View v) {
        TeamScore teamScoreDetail = (TeamScore) v.getTag();
        switch (v.getId()) {
            case R.id.player_stats_item_row_cv_main_container:
                Bundle bundle = new Bundle();
                bundle.putParcelable(Constants.TEAM_DETAIL, teamScoreDetail);
                ((HomeActivity) mActivity).replaceFragment(new TeamsStandingDetailFragment(), mActivity.getResources().getString(R.string.team_standing_detail), bundle);
                break;
            default:
                break;
        }
    }

    class PointsHolder extends RecyclerView.ViewHolder {
        CardView cvMainContainer;
        TextView tvTeamName;
        TextView tvCityName;
        TextView tvPlayedMatches;
        TextView tvWins;
        TextView tvLoss;
        TextView tvPoints;

        public PointsHolder(View itemView) {
            super(itemView);

            cvMainContainer = (CardView) itemView.findViewById(R.id.player_stats_item_row_cv_main_container);
            tvTeamName = (TextView) itemView.findViewById(R.id.player_stats_item_row_tv_player_name);
            tvCityName = (TextView) itemView.findViewById(R.id.player_stats_item_row_tv_team_name);
            tvPlayedMatches = (TextView) itemView.findViewById(R.id.player_stats_item_row_tv_matches);
            tvWins = (TextView) itemView.findViewById(R.id.player_stats_item_row_tv_balls_faced);
            tvLoss = (TextView) itemView.findViewById(R.id.player_stats_item_row_tv_runs_scored);
            tvPoints = (TextView) itemView.findViewById(R.id.player_stats_item_row_tv_fours);
        }
    }
}
package com.rakshith.cricketapp.cricketAdmin.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.models.TeamScore;

import java.util.List;

public class PointsAdapter extends RecyclerView.Adapter<PointsAdapter.PointsHolder> {
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
        View view = LayoutInflater.from(mActivity).inflate(R.layout.points_table_row, parent, false);
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

        if (!TextUtils.isEmpty(teamName))
            holder.tvTeamName.setText(teamName);
        holder.tvPlayedMatches.setText(String.valueOf(playedMatches));
        holder.tvWins.setText(String.valueOf(matchesWin));
        holder.tvLoss.setText(String.valueOf(matchesLost));
        holder.tvPoints.setText(String.valueOf(points));
    }

    @Override
    public int getItemCount() {
        return pointsList.size();
    }

    class PointsHolder extends RecyclerView.ViewHolder {
        TextView tvTeamName;
        TextView tvPlayedMatches;
        TextView tvWins;
        TextView tvLoss;
        TextView tvPoints;

        public PointsHolder(View itemView) {
            super(itemView);

            tvTeamName = (TextView) itemView.findViewById(R.id.points_table_row_tv_team_name);
            tvPlayedMatches = (TextView) itemView.findViewById(R.id.points_table_row_tv_played);
            tvWins = (TextView) itemView.findViewById(R.id.points_table_row_tv_wins);
            tvLoss = (TextView) itemView.findViewById(R.id.points_table_row_tv_loss);
            tvPoints = (TextView) itemView.findViewById(R.id.points_table_row_tv_points);
        }
    }
}
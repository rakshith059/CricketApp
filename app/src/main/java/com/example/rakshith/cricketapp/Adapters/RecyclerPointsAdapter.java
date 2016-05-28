package com.example.rakshith.cricketapp.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rakshith.cricketapp.Models.PointsTableModel;
import com.example.rakshith.cricketapp.R;
import com.parse.ParseObject;

import java.util.List;

/**
 * Created by rakshith on 5/27/16.
 */
public class RecyclerPointsAdapter extends RecyclerView.Adapter<RecyclerPointsAdapter.PointsHolder> {
    Activity mActivity;
    List<PointsTableModel> pointsList;

    String teamName;
    int playedMatches;
    int matchesWin;
    int matchesLost;
    int points;
    String netRunRate;

    public RecyclerPointsAdapter(Activity mActivity, List<PointsTableModel> list) {
        this.mActivity = mActivity;
        this.pointsList = list;
    }

    @Override
    public RecyclerPointsAdapter.PointsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.points_table_row, parent, false);
        return new PointsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerPointsAdapter.PointsHolder holder, int position) {
        PointsTableModel pointsTableModel = pointsList.get(position);
        teamName = pointsTableModel.getTeamName();
        playedMatches = pointsTableModel.getMatchesPlayed();
        matchesWin = pointsTableModel.getWins();
        matchesLost = pointsTableModel.getLoss();
        points = pointsTableModel.getPoints();
        netRunRate = pointsTableModel.getNetrunRate();

        holder.tvTeamName.setText(teamName);
        holder.tvPlayedMatches.setText(String.valueOf(playedMatches));
        holder.tvWins.setText(String.valueOf(matchesWin));
        holder.tvLoss.setText(String.valueOf(matchesLost));
        holder.tvPoints.setText(String.valueOf(points));
        holder.tvNrr.setText(netRunRate);
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
        TextView tvNrr;

        public PointsHolder(View itemView) {
            super(itemView);

            tvTeamName = (TextView) itemView.findViewById(R.id.points_table_row_tv_team_name);
            tvPlayedMatches = (TextView) itemView.findViewById(R.id.points_table_row_tv_played);
            tvWins = (TextView) itemView.findViewById(R.id.points_table_row_tv_wins);
            tvLoss = (TextView) itemView.findViewById(R.id.points_table_row_tv_loss);
            tvPoints = (TextView) itemView.findViewById(R.id.points_table_row_tv_points);
            tvNrr = (TextView) itemView.findViewById(R.id.points_table_row_tv_nrr);
        }
    }
}


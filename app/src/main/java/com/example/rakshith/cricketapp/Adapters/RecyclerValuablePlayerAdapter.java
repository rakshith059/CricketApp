package com.example.rakshith.cricketapp.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rakshith.cricketapp.Models.ValuablePlayerModel;
import com.example.rakshith.cricketapp.R;

import java.util.List;

/**
 * Created by rakshith on 5/28/16.
 */
public class RecyclerValuablePlayerAdapter extends RecyclerView.Adapter<RecyclerValuablePlayerAdapter.ValuablePlayerHolder> {
    Activity mActivity;
    List<ValuablePlayerModel> valuablePlayerList;

    public RecyclerValuablePlayerAdapter(Activity mActivity, List<ValuablePlayerModel> valuablePlayerList) {
        this.mActivity = mActivity;
        this.valuablePlayerList = valuablePlayerList;
    }

    @Override
    public ValuablePlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.points_table_row, parent, false);
        return new ValuablePlayerHolder(view);
    }

    @Override
    public void onBindViewHolder(ValuablePlayerHolder holder, int position) {
        ValuablePlayerModel valuablePlayerModel = valuablePlayerList.get(position);

        String playername = valuablePlayerModel.getPlayerName();
        String runsScored = valuablePlayerModel.getRunsScored();
        String wicketsTaken = valuablePlayerModel.getWicketTaken();
        String caught = valuablePlayerModel.getCaught();
        String runOut = valuablePlayerModel.getRunOuts();
        int points = valuablePlayerModel.getPoints();

        holder.tvPlayerName.setText(playername);
        holder.tvRunsScored.setText(runsScored);
        holder.tvWicketsTaken.setText(wicketsTaken);
        holder.tvCaught.setText(caught);
        holder.tvRunOut.setText(runOut);
        holder.tvPoints.setText(String.valueOf(points));
    }

    @Override
    public int getItemCount() {
        return valuablePlayerList.size();
    }

    class ValuablePlayerHolder extends RecyclerView.ViewHolder {
        TextView tvPlayerName;
        TextView tvRunsScored;
        TextView tvWicketsTaken;
        TextView tvCaught;
        TextView tvRunOut;
        TextView tvPoints;

        public ValuablePlayerHolder(View itemView) {
            super(itemView);

            tvPlayerName = (TextView) itemView.findViewById(R.id.points_table_row_tv_team_name);
            tvRunsScored = (TextView) itemView.findViewById(R.id.points_table_row_tv_played);
            tvWicketsTaken = (TextView) itemView.findViewById(R.id.points_table_row_tv_wins);
            tvCaught = (TextView) itemView.findViewById(R.id.points_table_row_tv_loss);
            tvRunOut = (TextView) itemView.findViewById(R.id.points_table_row_tv_points);
            tvPoints = (TextView) itemView.findViewById(R.id.points_table_row_tv_nrr);
        }
    }
}

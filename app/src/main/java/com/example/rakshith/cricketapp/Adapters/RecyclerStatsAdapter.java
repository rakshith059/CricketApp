package com.example.rakshith.cricketapp.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rakshith.cricketapp.Models.BattingModel;
import com.example.rakshith.cricketapp.R;

import java.util.List;

/**
 * Created by rakshith on 5/28/16.
 */
public class RecyclerStatsAdapter extends RecyclerView.Adapter<RecyclerStatsAdapter.StatsHolder>{
    Activity mActivity;
    List<BattingModel> battingList;

    public RecyclerStatsAdapter(Activity mActivity, List<BattingModel> battingList) {
        this.mActivity = mActivity;
        this.battingList = battingList;
    }

    @Override
    public StatsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.stats_row , parent , false);
        return new StatsHolder(view);
    }

    @Override
    public void onBindViewHolder(StatsHolder holder, int position) {
        BattingModel battingBowlingModel = battingList.get(position);
        String name = battingBowlingModel.getBatsmanName();
        String balls = battingBowlingModel.getBallsFaced();
        int runsWicket = battingBowlingModel.getRunsScored();

        holder.tvstatsPLayerName.setText(name);
        holder.tvstatsBallsfacedOrBowled.setText(balls);
        holder.tvStatsRunsOrWicket.setText(String.valueOf(runsWicket));
    }

    @Override
    public int getItemCount() {
        return battingList.size();
    }

    class StatsHolder extends RecyclerView.ViewHolder {
        TextView tvstatsPLayerName;
        TextView tvstatsBallsfacedOrBowled;
        TextView tvStatsRunsOrWicket;

        public StatsHolder(View itemView) {
            super(itemView);

            tvstatsPLayerName = (TextView) itemView.findViewById(R.id.stats_row_tv_player_name);
            tvstatsBallsfacedOrBowled = (TextView) itemView.findViewById(R.id.stats_row_tv_ball);
            tvStatsRunsOrWicket = (TextView) itemView.findViewById(R.id.stats_row_tv_runs_wicket);
        }
    }
}

package com.rakshith.cricketapp.cricketAdmin.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.Utils.Constants;
import com.rakshith.cricketapp.cricketAdmin.models.MemberStats;

import java.util.List;

/**
 * Created by rakshith on 3/15/17.
 */
public class PlayerStatsAdapter extends RecyclerView.Adapter<PlayerStatsAdapter.PlayerStatViewHolder> {
    Activity activity;
    List<MemberStats> memberStatsList;
    String from;

    public PlayerStatsAdapter(Activity mActivity, String from, List<MemberStats> memberStatsList) {
        this.activity = mActivity;
        this.memberStatsList = memberStatsList;
        this.from = from;
    }

    @Override
    public PlayerStatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.player_stats_item, parent, false);
        return new PlayerStatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerStatViewHolder holder, int position) {
        MemberStats memberStats = memberStatsList.get(position);

        String playerName = memberStats.getmPlayerName();
        int matchesPlayed = memberStats.getiMatchesPlayed();
        int ballsFaced = memberStats.getiBallsFaced();
        int runsScored = memberStats.getiRunsScored();
        int fours = memberStats.getiFours();
        int oversBowled = memberStats.getiOversBowled();
        int wicketsTook = memberStats.getiWicketsTook();
        int maidens = memberStats.getiMaindens();
        int catches = memberStats.getiCatches();
        int runOuts = memberStats.getiRunouts();
        int stumps = memberStats.getiStumps();

        if (!TextUtils.isEmpty(playerName)) {
            holder.tvPlayerName.setText(playerName);
            holder.tvMatchesPlayed.setText(String.valueOf(matchesPlayed));
        }
        if (!TextUtils.isEmpty(from)) {
            if (from.equalsIgnoreCase(Constants.ROLE_BATSMEN)) {
                holder.tvBallsOverCatches.setText(String.valueOf(ballsFaced));
                holder.tvRunsWicketsRunOuts.setText(String.valueOf(runsScored));
                holder.tvFoursMaidenStump.setText(String.valueOf(fours));
            } else if (from.equalsIgnoreCase(Constants.ROLE_BOWLER)) {
                holder.tvBallsOverCatches.setText(String.valueOf(oversBowled));
                holder.tvRunsWicketsRunOuts.setText(String.valueOf(wicketsTook));
                holder.tvFoursMaidenStump.setText(String.valueOf(maidens));
            } else if (from.equalsIgnoreCase(Constants.ROLE_KEEPER)) {
                holder.tvBallsOverCatches.setText(String.valueOf(catches));
                holder.tvRunsWicketsRunOuts.setText(String.valueOf(runOuts));
                holder.tvFoursMaidenStump.setText(String.valueOf(stumps));
            }
        }

    }

    @Override
    public int getItemCount() {
        return memberStatsList.size();
    }

    public class PlayerStatViewHolder extends RecyclerView.ViewHolder {
        TextView tvPlayerName;
        TextView tvMatchesPlayed;
        TextView tvBallsOverCatches;
        TextView tvRunsWicketsRunOuts;
        TextView tvFoursMaidenStump;

        public PlayerStatViewHolder(View view) {
            super(view);

            tvPlayerName = (TextView) view.findViewById(R.id.player_stats_item_tv_player_name);
            tvMatchesPlayed = (TextView) view.findViewById(R.id.player_stats_item_tv_matches);
            tvBallsOverCatches = (TextView) view.findViewById(R.id.player_stats_item_tv_balls_faced);
            tvRunsWicketsRunOuts = (TextView) view.findViewById(R.id.player_stats_item_tv_runs_scored);
            tvFoursMaidenStump = (TextView) view.findViewById(R.id.player_stats_item_tv_fours);
        }
    }
}

package com.rakshith.cricketapp.cricketAdmin.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.models.TeamList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rakshith on 3/12/17.
 */
public class TeamsPollAdapter extends RecyclerView.Adapter<TeamsPollAdapter.TeamsPollViewHolder> {
    private Activity mActivity;
    private List<TeamList> teams;

    List<TeamList> pollATeams = new ArrayList<>();
    List<TeamList> pollBTeams = new ArrayList<>();
    private String userIdPollB;
    private String userIdPollA;

    @Override
    public TeamsPollViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.matches_poll_item, parent, false);

        return new TeamsPollViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TeamsPollViewHolder holder, int position) {
        TeamList team = teams.get(position);
        if (position == 0) {
            holder.tvPollName.setText(mActivity.getResources().getString(R.string.pollA));
//            if (teams != null) {
//                int teamsSize = teams.size();
//                if (teamsSize > 1) {
//                    int pollASize = teamsSize / 2;
//
//                    pollATeams = teams.subList(0, pollASize);
//                    pollBTeams = teams.subList(pollASize, teamsSize);
//
//                    DatabaseReference databasePollA = FirebaseDatabase.getInstance().getReference(Constants.DB_POLL_A);
//                    DatabaseReference databasePollB = FirebaseDatabase.getInstance().getReference(Constants.DB_POLL_B);
//
//                    if (TextUtils.isEmpty(userIdPollA)) {
//                        userIdPollA = databasePollA.push().getKey();
//                    }
//                    databasePollA.child(userIdPollA).setValue(pollATeams);
//
//                    if (TextUtils.isEmpty(userIdPollB)) {
//                        userIdPollB = databasePollB.push().getKey();
//                    }
//                    databasePollB.child(userIdPollB).setValue(pollBTeams);
//                }
//            }
        } else {
            holder.tvPollName.setText(mActivity.getResources().getString(R.string.pollB));
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public void updateTeamsList(Activity mActivity, List teams) {
        this.mActivity = mActivity;
        this.teams = teams;
    }

    public class TeamsPollViewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvPollTeams;
        TextView tvPollName;
        LinearLayout llViewMore;

        public TeamsPollViewHolder(View itemView) {
            super(itemView);

            llViewMore = (LinearLayout) itemView.findViewById(R.id.matches_poll_item_ll_view_more);
            rvPollTeams = (RecyclerView) itemView.findViewById(R.id.matches_poll_item_rv_poll_teams);
            tvPollName = (TextView) itemView.findViewById(R.id.matches_poll_item_tv_poll_name);
        }
    }
}

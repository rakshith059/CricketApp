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
import com.rakshith.cricketapp.cricketAdmin.fragments.EditMatchesFragment;
import com.rakshith.cricketapp.cricketAdmin.fragments.EnterMatchesFragment;
import com.rakshith.cricketapp.cricketAdmin.models.MatchList;

import java.util.ArrayList;

/**
 * Created by rakshith on 3/26/17.
 */
public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder> implements View.OnClickListener {

    private Activity mActivity;
    private ArrayList<MatchList> matchesList;

    Bundle analyticsBundle;

    public MatchesAdapter(Activity mActivity, ArrayList<MatchList> matchesList) {
        this.mActivity = mActivity;
        this.matchesList = matchesList;

        analyticsBundle = new Bundle();
    }

    @Override
    public MatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.matches_row_item, parent, false);
        return new MatchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchesViewHolder holder, int position) {
        MatchList matchList = matchesList.get(position);
        String mMatchNumber = matchList.getMatchNumber();
        String mTeamOne = matchList.getTeamOneName();
        String mTeamTwo = matchList.getTeamTwoName();

        if (!TextUtils.isEmpty(mMatchNumber))
            holder.tvMatchNumber.setText(mMatchNumber);
        if (!TextUtils.isEmpty(mTeamOne))
            holder.tvTeamOne.setText(mTeamOne);
        if (!TextUtils.isEmpty(mTeamTwo))
            holder.tvTeamTwo.setText(mTeamTwo);

        holder.cvMainContainer.setTag(matchList);
        holder.cvMainContainer.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        if (matchesList == null)
            return 0;
        return matchesList.size();
    }

    @Override
    public void onClick(View v) {
        MatchList matchList = (MatchList) v.getTag();
        switch (v.getId()) {
            case R.id.matches_row_item_cv_main_container:
                Bundle bundle = new Bundle();
                if (matchList != null) {
                    bundle.putParcelable(Constants.MATCH_DETAIL, matchList);

                    ((HomeActivity) mActivity).replaceFragment(new EditMatchesFragment(), mActivity.getResources().getString(R.string.enter_matches), bundle);

                    analyticsBundle.putString(Constants.MATCH_NUM, matchList.getMatchNumber());
                    ((HomeActivity) mActivity).fireBaseAnalyticsEvents(Constants.EVENT_CLICKED, bundle);
                }
                break;
            default:
                break;
        }
    }

    public class MatchesViewHolder extends RecyclerView.ViewHolder {
        CardView cvMainContainer;
        TextView tvMatchNumber;
        TextView tvTeamOne;
        TextView tvTeamTwo;

        public MatchesViewHolder(View itemView) {
            super(itemView);
            cvMainContainer = (CardView) itemView.findViewById(R.id.matches_row_item_cv_main_container);
            tvMatchNumber = (TextView) itemView.findViewById(R.id.matches_row_item_tv_match_number);
            tvTeamOne = (TextView) itemView.findViewById(R.id.matches_row_item_tv_team_one);
            tvTeamTwo = (TextView) itemView.findViewById(R.id.matches_row_item_tv_team_two);
        }
    }
}

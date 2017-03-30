package com.rakshith.cricketapp.cricketAdmin.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rakshith.cricketapp.R;

import java.util.List;

/**
 * Created by rakshith on 5/28/16.
 */
public class RecyclerAboutUsAdapter extends RecyclerView.Adapter<RecyclerAboutUsAdapter.AboutUsHolder>{
    Activity mActivity;
    List<String> aboutUsList;

    public RecyclerAboutUsAdapter(FragmentActivity activity, List<String> aboutUsList) {
        this.mActivity = activity;
        this.aboutUsList = aboutUsList;
    }

    @Override
    public AboutUsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.about_us_row , parent , false);
        return new AboutUsHolder(view);
    }

    @Override
    public void onBindViewHolder(AboutUsHolder holder, int position) {
//        aboutUsList.get(position);
        holder.tvPlayerName.setText(aboutUsList.get(position));
    }

    @Override
    public int getItemCount() {
        return aboutUsList.size();
    }

    class AboutUsHolder extends RecyclerView.ViewHolder {
        TextView tvPlayerName;
        public AboutUsHolder(View itemView) {
            super(itemView);
            tvPlayerName = (TextView) itemView.findViewById(R.id.about_us_row_tv_name);
        }
    }
}

package com.rakshith.cricketapp.cricketAdmin.adapters;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rakshith.cricketapp.R;
import com.rakshith.cricketapp.cricketAdmin.models.SponsorModel;

import java.util.ArrayList;

/**
 * Created by rakshith on 3/31/17.
 */

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.SponsorViewHolder> {
    ArrayList<SponsorModel> sponsorList;
    Activity mActivity;
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    public SponsorAdapter(Activity mActivity, ArrayList<SponsorModel> sponsorList) {
        this.mActivity = mActivity;
        this.sponsorList = sponsorList;
    }

    @Override
    public SponsorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.sponsor_row_item, parent, false);
        return new SponsorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SponsorViewHolder holder, int position) {
        SponsorModel sponsorModel = sponsorList.get(position);
        String mSponsorName = sponsorModel.getName();
        String mSponsorDetail = sponsorModel.getDetail();

        String path = "Sponsers/" + mSponsorName + ".jpeg";
        final StorageReference storageReference = firebaseStorage.getReference(path);

        if (!TextUtils.isEmpty(mSponsorName))
            holder.tvSponsorName.setText(mSponsorName);
        if (!TextUtils.isEmpty(mSponsorDetail))
            holder.tvSponsorDetail.setText(mSponsorDetail);

        final long ONE_MEGABYTE = 1024 * 1024;
        storageReference.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                holder.ivSponsorImage.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return sponsorList.size();
    }

    public class SponsorViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSponsorImage;
        TextView tvSponsorName;
        TextView tvSponsorDetail;

        public SponsorViewHolder(View itemView) {
            super(itemView);

            ivSponsorImage = (ImageView) itemView.findViewById(R.id.sponsor_row_item_iv_image);
            tvSponsorName = (TextView) itemView.findViewById(R.id.sponsor_row_item_tv_name);
            tvSponsorDetail = (TextView) itemView.findViewById(R.id.sponsor_row_item_tv_detail);
        }
    }
}

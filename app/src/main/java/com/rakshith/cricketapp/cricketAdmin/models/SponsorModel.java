package com.rakshith.cricketapp.cricketAdmin.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rakshith on 3/31/17.
 */

public class SponsorModel implements Parcelable {
    private String name;
    private String detail;
    private String imageUrl;

    public SponsorModel() {
    }

    public SponsorModel(String sponserName, String sponsorDetail, String sponsorImageUrl) {
        this.name = sponserName;
        this.detail = sponsorDetail;
        this.imageUrl = sponsorImageUrl;
    }

    protected SponsorModel(Parcel in) {
        name = in.readString();
        detail = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<SponsorModel> CREATOR = new Creator<SponsorModel>() {
        @Override
        public SponsorModel createFromParcel(Parcel in) {
            return new SponsorModel(in);
        }

        @Override
        public SponsorModel[] newArray(int size) {
            return new SponsorModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(detail);
        dest.writeString(imageUrl);
    }
}

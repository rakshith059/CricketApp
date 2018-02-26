package com.rakshith.cricketapp.cricketAdmin.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by rakshith on 3/11/17.
 */
public class TeamList implements Parcelable {
    private String teamName;
    private String cityName;
    private String contactNo;
    private String teamImageUrl;
    private List<MemberItem> teamMembers = null;

    public TeamList() {
    }

    public TeamList(String teamName, List<MemberItem> teamMembers, String cityName, String contactNo) {
        this.setTeamName(teamName);
        this.setTeamMembers(teamMembers);
        this.setCityName(cityName);
        this.setContactNo(contactNo);
    }

    protected TeamList(Parcel in) {
        teamName = in.readString();
        cityName = in.readString();
        contactNo = in.readString();
        teamImageUrl = in.readString();
    }

    public static final Creator<TeamList> CREATOR = new Creator<TeamList>() {
        @Override
        public TeamList createFromParcel(Parcel in) {
            return new TeamList(in);
        }

        @Override
        public TeamList[] newArray(int size) {
            return new TeamList[size];
        }
    };

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<MemberItem> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(List<MemberItem> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getTeamImageUrl() {
        return teamImageUrl;
    }

    public void setTeamImageUrl(String teamImageUrl) {
        this.teamImageUrl = teamImageUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(teamName);
        dest.writeString(cityName);
        dest.writeString(contactNo);
        dest.writeString(teamImageUrl);
    }
}

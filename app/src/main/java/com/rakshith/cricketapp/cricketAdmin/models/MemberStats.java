package com.rakshith.cricketapp.cricketAdmin.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rakshith on 3/14/17.
 */
public class MemberStats implements Parcelable {
    private String mPlayerName;
    private String mTeamName;
    private int iMatchesPlayed;
    private int iBallsFaced;
    private int iRunsScored;
    private int iFours;
    private int iOversBowled;
    private int iWicketsTook;
    private int iMaindens;
    private int iCatches;
    private int iRunouts;
    private int iStumps;

    public MemberStats() {
    }

    public MemberStats(String mPlayerName, String mTeamName, int iMatchesPlayed, int iBallsFaced, int iRunsScored, int iFours, int iOversBowled, int iWicketsTook, int iMaindens, int iCatches, int iRunouts, int iStumps) {
        this.mPlayerName = mPlayerName;
        this.mTeamName = mTeamName;
        this.iMatchesPlayed = iMatchesPlayed;
        this.iBallsFaced = iBallsFaced;
        this.iRunsScored = iRunsScored;
        this.iFours = iFours;
        this.iOversBowled = iOversBowled;
        this.iWicketsTook = iWicketsTook;
        this.iMaindens = iMaindens;
        this.iCatches = iCatches;
        this.iRunouts = iRunouts;
        this.iStumps = iStumps;
    }

    protected MemberStats(Parcel in) {
        setmPlayerName(in.readString());
        setmTeamName(in.readString());
        setiMatchesPlayed(in.readInt());
        setiBallsFaced(in.readInt());
        setiRunsScored(in.readInt());
        setiFours(in.readInt());
        setiOversBowled(in.readInt());
        setiWicketsTook(in.readInt());
        setiMaindens(in.readInt());
        setiCatches(in.readInt());
        setiRunouts(in.readInt());
        setiStumps(in.readInt());
    }

    public static final Creator<MemberStats> CREATOR = new Creator<MemberStats>() {
        @Override
        public MemberStats createFromParcel(Parcel in) {
            return new MemberStats(in);
        }

        @Override
        public MemberStats[] newArray(int size) {
            return new MemberStats[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getmPlayerName());
        dest.writeString(getmTeamName());
        dest.writeInt(getiMatchesPlayed());
        dest.writeInt(getiBallsFaced());
        dest.writeInt(getiRunsScored());
        dest.writeInt(getiFours());
        dest.writeInt(getiOversBowled());
        dest.writeInt(getiWicketsTook());
        dest.writeInt(getiMaindens());
        dest.writeInt(getiCatches());
        dest.writeInt(getiRunouts());
        dest.writeInt(getiStumps());
    }

    public String getmPlayerName() {
        return mPlayerName;
    }

    public void setmPlayerName(String mPlayerName) {
        this.mPlayerName = mPlayerName;
    }

    public String getmTeamName() {
        return mTeamName;
    }

    public void setmTeamName(String mTeamName) {
        this.mTeamName = mTeamName;
    }

    public int getiMatchesPlayed() {
        return iMatchesPlayed;
    }

    public void setiMatchesPlayed(int iMatchesPlayed) {
        this.iMatchesPlayed = iMatchesPlayed;
    }

    public int getiBallsFaced() {
        return iBallsFaced;
    }

    public void setiBallsFaced(int iBallsFaced) {
        this.iBallsFaced = iBallsFaced;
    }

    public int getiRunsScored() {
        return iRunsScored;
    }

    public void setiRunsScored(int iRunsScored) {
        this.iRunsScored = iRunsScored;
    }

    public int getiFours() {
        return iFours;
    }

    public void setiFours(int iFours) {
        this.iFours = iFours;
    }

    public int getiOversBowled() {
        return iOversBowled;
    }

    public void setiOversBowled(int iOversBowled) {
        this.iOversBowled = iOversBowled;
    }

    public int getiWicketsTook() {
        return iWicketsTook;
    }

    public void setiWicketsTook(int iWicketsTook) {
        this.iWicketsTook = iWicketsTook;
    }

    public int getiMaindens() {
        return iMaindens;
    }

    public void setiMaindens(int iMaindens) {
        this.iMaindens = iMaindens;
    }

    public int getiCatches() {
        return iCatches;
    }

    public void setiCatches(int iCatches) {
        this.iCatches = iCatches;
    }

    public int getiRunouts() {
        return iRunouts;
    }

    public void setiRunouts(int iRunouts) {
        this.iRunouts = iRunouts;
    }

    public int getiStumps() {
        return iStumps;
    }

    public void setiStumps(int iStumps) {
        this.iStumps = iStumps;
    }
}

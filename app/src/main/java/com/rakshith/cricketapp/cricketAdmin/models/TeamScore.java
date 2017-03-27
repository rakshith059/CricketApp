package com.rakshith.cricketapp.cricketAdmin.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rakshith on 3/12/17.
 */
public class TeamScore implements Parcelable {
    private String teamName;
    private String cityName;
    private int matchesPlayed;
    private int wins;
    private int lost;
    private int runsFor;
    private int wicketsLost;
    private int runsAgainst;
    private int wicketsTook;
    private int totalPoints;

    public TeamScore() {
    }


    public TeamScore(String teamName, String cityName, int matchesPlayed, int wins, int lost, int scoreFor, int wicketsLost, int scoreAgainst, int wicketsTook, int totalPoints) {
        this.setTeamName(teamName);
        this.setCityName(cityName);
        this.setMatchesPlayed(matchesPlayed);
        this.setWins(wins);
        this.setLost(lost);
        this.setRunsFor(scoreFor);
        this.setWicketsLost(wicketsLost);
        this.setRunsAgainst(scoreAgainst);
        this.setWicketsTook(wicketsTook);
        this.setTotalPoints(totalPoints);
    }

    protected TeamScore(Parcel in) {
        setTeamName(in.readString());
        setCityName(in.readString());
        setMatchesPlayed(in.readInt());
        setWins(in.readInt());
        setLost(in.readInt());
        setRunsFor(in.readInt());
        setWicketsLost(in.readInt());
        setRunsAgainst(in.readInt());
        setWicketsTook(in.readInt());
        setTotalPoints(in.readInt());
    }

    public static final Creator<TeamScore> CREATOR = new Creator<TeamScore>() {
        @Override
        public TeamScore createFromParcel(Parcel in) {
            return new TeamScore(in);
        }

        @Override
        public TeamScore[] newArray(int size) {
            return new TeamScore[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getTeamName());
        dest.writeString(getCityName());
        dest.writeInt(getMatchesPlayed());
        dest.writeInt(getWins());
        dest.writeInt(getLost());
        dest.writeInt(getRunsFor());
        dest.writeInt(getWicketsLost());
        dest.writeInt(getRunsAgainst());
        dest.writeInt(getWicketsTook());
        dest.writeInt(getTotalPoints());
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getRunsFor() {
        return runsFor;
    }

    public void setRunsFor(int runsFor) {
        this.runsFor = runsFor;
    }

    public int getWicketsLost() {
        return wicketsLost;
    }

    public void setWicketsLost(int wicketsLost) {
        this.wicketsLost = wicketsLost;
    }

    public int getRunsAgainst() {
        return runsAgainst;
    }

    public void setRunsAgainst(int runsAgainst) {
        this.runsAgainst = runsAgainst;
    }

    public int getWicketsTook() {
        return wicketsTook;
    }

    public void setWicketsTook(int wicketsTook) {
        this.wicketsTook = wicketsTook;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}

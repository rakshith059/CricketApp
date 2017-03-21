package com.rakshith.cricketapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rakshith on 5/27/16.
 */
public class MatchesModel implements Parcelable{
    private String matchNo;
    private String teamOne;
    private String teamSecond;
    private String toss;
    private String winner;
    private String teamOneScore;
    private String teamTwoScore;
    private String mom;

    public MatchesModel(String matchNum, String teamOne, String teamSecond, String toss, String winner, String team1Score, String team2Score, String mom) {
        this.matchNo = matchNum;
        this.teamOne = teamOne;
        this.teamSecond = teamSecond;
        this.toss = toss;
        this.winner = winner;
        this.teamOneScore = team1Score;
        this.teamTwoScore = team2Score;
        this.mom = mom;
    }

    public MatchesModel() {

    }

    protected MatchesModel(Parcel in) {
        matchNo = in.readString();
        teamOne = in.readString();
        teamSecond = in.readString();
        toss = in.readString();
        winner = in.readString();
        teamOneScore = in.readString();
        teamTwoScore = in.readString();
        mom = in.readString();
    }

    public static final Creator<MatchesModel> CREATOR = new Creator<MatchesModel>() {
        @Override
        public MatchesModel createFromParcel(Parcel in) {
            return new MatchesModel(in);
        }

        @Override
        public MatchesModel[] newArray(int size) {
            return new MatchesModel[size];
        }
    };

    public String getMatchNo() {
        return matchNo;
    }

    public void setMatchNo(String matchNo) {
        this.matchNo = matchNo;
    }

    public String getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    public String getTeamSecond() {
        return teamSecond;
    }

    public void setTeamSecond(String teamSecond) {
        this.teamSecond = teamSecond;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getToss() {
        return toss;
    }

    public void setToss(String toss) {
        this.toss = toss;
    }

    public String getTeamOneScore() {
        return teamOneScore;
    }

    public void setTeamOneScore(String teamOneScore) {
        this.teamOneScore = teamOneScore;
    }

    public String getTeamTwoScore() {
        return teamTwoScore;
    }

    public void setTeamTwoScore(String teamTwoScore) {
        this.teamTwoScore = teamTwoScore;
    }

    public String getMom() {
        return mom;
    }

    public void setMom(String mom) {
        this.mom = mom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(matchNo);
        parcel.writeString(teamOne);
        parcel.writeString(teamSecond);
        parcel.writeString(toss);
        parcel.writeString(winner);
        parcel.writeString(teamOneScore);
        parcel.writeString(teamTwoScore);
        parcel.writeString(mom);
    }
}

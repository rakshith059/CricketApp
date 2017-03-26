package com.rakshith.cricketapp.cricketAdmin.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rakshith on 3/26/17.
 */
public class MatchList implements Parcelable {
    private String matchNumber;
    private String teamOneName;
    private String teamOneRuns;
    private String teamOneWickets;
    private String teamTwoName;
    private String teamTwoRuns;
    private String teamTwoWickets;
    private String tossWinByTeam;
    private String matchWinByTeam;
    private String winByRunsWickets;
    private String manOfTheMatch;

    public MatchList() {
    }

    public MatchList(String matchNumber, String teamOneName, String teamOneRuns, String teamOneWickets, String teamTwoName, String teamTwoRuns, String teamTwoWickets, String tossWinByTeam, String matchWinByTeam, String winByRunsWickets, String manOfTheMatch) {
        this.setMatchNumber(matchNumber);
        this.teamOneName = teamOneName;
        this.teamOneRuns = teamOneRuns;
        this.teamOneWickets = teamOneWickets;
        this.teamTwoName = teamTwoName;
        this.teamTwoRuns = teamTwoRuns;
        this.teamTwoWickets = teamTwoWickets;
        this.tossWinByTeam = tossWinByTeam;
        this.matchWinByTeam = matchWinByTeam;
        this.setWinByRunsWickets(winByRunsWickets);
        this.manOfTheMatch = manOfTheMatch;
    }

    protected MatchList(Parcel in) {
        setMatchNumber(in.readString());
        teamOneName = in.readString();
        teamOneRuns = in.readString();
        teamOneWickets = in.readString();
        teamTwoName = in.readString();
        teamTwoRuns = in.readString();
        teamTwoWickets = in.readString();
        tossWinByTeam = in.readString();
        matchWinByTeam = in.readString();
        setWinByRunsWickets(in.readString());
        manOfTheMatch = in.readString();
    }

    public static final Creator<MatchList> CREATOR = new Creator<MatchList>() {
        @Override
        public MatchList createFromParcel(Parcel in) {
            return new MatchList(in);
        }

        @Override
        public MatchList[] newArray(int size) {
            return new MatchList[size];
        }
    };

    public String getTeamOneName() {
        return teamOneName;
    }

    public void setTeamOneName(String teamOneName) {
        this.teamOneName = teamOneName;
    }

    public String getTeamOneRuns() {
        return teamOneRuns;
    }

    public void setTeamOneRuns(String teamOneRuns) {
        this.teamOneRuns = teamOneRuns;
    }

    public String getTeamOneWickets() {
        return teamOneWickets;
    }

    public void setTeamOneWickets(String teamOneWickets) {
        this.teamOneWickets = teamOneWickets;
    }

    public String getTeamTwoName() {
        return teamTwoName;
    }

    public void setTeamTwoName(String teamTwoName) {
        this.teamTwoName = teamTwoName;
    }

    public String getTeamTwoRuns() {
        return teamTwoRuns;
    }

    public void setTeamTwoRuns(String teamTwoRuns) {
        this.teamTwoRuns = teamTwoRuns;
    }

    public String getTeamTwoWickets() {
        return teamTwoWickets;
    }

    public void setTeamTwoWickets(String teamTwoWickets) {
        this.teamTwoWickets = teamTwoWickets;
    }

    public String getTossWinByTeam() {
        return tossWinByTeam;
    }

    public void setTossWinByTeam(String tossWinByTeam) {
        this.tossWinByTeam = tossWinByTeam;
    }

    public String getMatchWinByTeam() {
        return matchWinByTeam;
    }

    public void setMatchWinByTeam(String matchWinByTeam) {
        this.matchWinByTeam = matchWinByTeam;
    }

    public String getManOfTheMatch() {
        return manOfTheMatch;
    }

    public void setManOfTheMatch(String manOfTheMatch) {
        this.manOfTheMatch = manOfTheMatch;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getMatchNumber());
        dest.writeString(teamOneName);
        dest.writeString(teamOneRuns);
        dest.writeString(teamOneWickets);
        dest.writeString(teamTwoName);
        dest.writeString(teamTwoRuns);
        dest.writeString(teamTwoWickets);
        dest.writeString(tossWinByTeam);
        dest.writeString(matchWinByTeam);
        dest.writeString(getWinByRunsWickets());
        dest.writeString(manOfTheMatch);
    }

    public String getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(String matchNumber) {
        this.matchNumber = matchNumber;
    }

    public String getWinByRunsWickets() {
        return winByRunsWickets;
    }

    public void setWinByRunsWickets(String winByRunsWickets) {
        this.winByRunsWickets = winByRunsWickets;
    }
}

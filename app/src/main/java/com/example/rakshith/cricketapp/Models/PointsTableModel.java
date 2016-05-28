package com.example.rakshith.cricketapp.Models;

/**
 * Created by rakshith on 5/27/16.
 */
public class PointsTableModel {

    private String teamName;
    private int matchesPlayed;
    private int wins;
    private int loss;
    private int points;
    private String netrunRate;

    public PointsTableModel(String teamName, int playedMatches, int matchesWin, int matchesLost, int points, String netRunRate) {
        this.teamName = teamName;
        this.matchesPlayed = playedMatches;
        this.wins = matchesWin;
        this.loss = matchesLost;
        this.points = points;
        this.netrunRate = netRunRate;
    }

    public PointsTableModel() {

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

    public int getLoss() {
        return loss;
    }

    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getNetrunRate() {
        return netrunRate;
    }

    public void setNetrunRate(String netrunRate) {
        this.netrunRate = netrunRate;
    }
}

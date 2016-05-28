package com.example.rakshith.cricketapp.Models;

/**
 * Created by rakshith on 5/28/16.
 */
public class ValuablePlayerModel {
    private String playerName;
    private String runsScored;
    private String wicketTaken;
    private String caught;
    private String runOuts;
    private int points;

    public ValuablePlayerModel(String playername, String runsScored, String wicketTaken, String caught, String runOut, int points) {
        this.playerName = playername;
        this.runsScored = runsScored;
        this.wicketTaken = wicketTaken;
        this.caught = caught;
        this.runOuts = runOut;
        this.points = points;
    }

    public ValuablePlayerModel() {

    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(String runsScored) {
        this.runsScored = runsScored;
    }

    public String getWicketTaken() {
        return wicketTaken;
    }

    public void setWicketTaken(String wicketTaken) {
        this.wicketTaken = wicketTaken;
    }

    public String getCaught() {
        return caught;
    }

    public void setCaught(String caught) {
        this.caught = caught;
    }

    public String getRunOuts() {
        return runOuts;
    }

    public void setRunOuts(String runOuts) {
        this.runOuts = runOuts;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}

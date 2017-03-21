package com.rakshith.cricketapp.Models;

/**
 * Created by rakshith on 5/28/16.
 */
public class BattingModel {
    private String batsmanName;
    private String ballsFaced;
    private int runsScored;

    public BattingModel(String batsmanName, String ballsFaced, int runsScored) {
        this.batsmanName = batsmanName;
        this.ballsFaced = ballsFaced;
        this.runsScored = runsScored;
    }

    public BattingModel() {

    }

    public String getBatsmanName() {
        return batsmanName;
    }

    public void setBatsmanName(String batsmanName) {
        this.batsmanName = batsmanName;
    }

    public String getBallsFaced() {
        return ballsFaced;
    }

    public void setBallsFaced(String ballsFaced) {
        this.ballsFaced = ballsFaced;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }
}

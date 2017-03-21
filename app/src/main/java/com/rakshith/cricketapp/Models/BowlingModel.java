package com.rakshith.cricketapp.Models;

/**
 * Created by rakshith on 5/28/16.
 */
public class BowlingModel {
    private String BowlerName;
    private String BallsBowled;
    private int wicketsTaken;

    public String getBowlerName() {
        return BowlerName;
    }

    public void setBowlerName(String bowlerName) {
        BowlerName = bowlerName;
    }

    public String getBallsBowled() {
        return BallsBowled;
    }

    public void setBallsBowled(String ballsBowled) {
        BallsBowled = ballsBowled;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }
}

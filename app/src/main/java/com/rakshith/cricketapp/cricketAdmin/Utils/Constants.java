package com.rakshith.cricketapp.cricketAdmin.Utils;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

/**
 * Created by rakshith on 3/11/17.
 */

public class Constants {

    public static final String DB_TEAM = "teams";
    public static final String DB_POLL_A = "pollA";
    public static final String DB_POLL_B = "pollB";
    public static final String DB_TEAMS_SCORE = "teamsScore";
    public static final String DB_PLAYER_STATS = "playerStats";

    public static final String DB_TEAMS_SCORE_CHILD_TOTAL_POINT = "totalPoints";
    public static final String DB_PLAYER_STATS_CHILD_RUNS = "iRunsScored";
    public static final String DB_PLAYER_STATS_CHILD_BALLS_FACED = "iBallsFaced";
    public static final String DB_PLAYER_STATS_CHILD_FOURS = "iFours";
    public static final String DB_PLAYER_STATS_CHILD_OVERS_BOWLED = "iOversBowled";
    public static final String DB_PLAYER_STATS_CHILD_WICKETS = "iWicketsTook";
    public static final String DB_PLAYER_STATS_CHILD_MAIDENS = "iMaindens";
    public static final String DB_PLAYER_STATS_CHILD_CATCHES = "iCatches";
    public static final String DB_PLAYER_STATS_CHILD_RUNOUTS = "iRunouts";
    public static final String DB_PLAYER_STATS_CHILD_STUMPS = "iStumps";
    public static final String DB_TEAMS_SCORE_CHILD_MATCHES_PLAYED = "matchesPlayed";
    public static final String DB_TEAMS_SCORE_CHILD_WINS = "wins";
    public static final String DB_TEAMS_SCORE_CHILD_LOSS = "lost";

    public static final String ROLE_BATSMEN = "Batsmen";
    public static final String ROLE_BOWLER = "Bowler";
    public static final String ROLE_KEEPER = "Wkt keeper";
    public static final String ROLE_ALL_ROUNDER = "All rounder";
    private static final long FADE_DURATION = 1000;
    public static final String TEAM_DETAIL = "teamDetail";
    public static final String TEAM_BG_IMAGE = "teamBackgroundImage";

    public static final String TARIKERE = "TARIKERE";
    public static final String BHADRAVATHI = "BHADRAVATHI";
    public static final String KADUR = "KADUR";
    public static final String SHIVAMOGA = "SHIVAMOGA";
    public static final String ARSIKERE = "ARSIKERE";
    public static final String TIPTUR = "TIPTUR";
    public static final String HASSAN = "HASSAN";
    public static final String TEAM_NAME = "team_name";
    public static final String PLAYER_NAME = "player_name";

    public void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }

    public void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(Animation.ABSOLUTE);
        view.startAnimation(anim);
    }
}

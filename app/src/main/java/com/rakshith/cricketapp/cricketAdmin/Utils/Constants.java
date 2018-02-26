package com.rakshith.cricketapp.cricketAdmin.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
    public static final String DB_MATCHES = "matches";
    public static final String DB_SPONSORS = "sponsors";

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
    public static final String PLAYER_DETAIL = "player_detail";

    public static final String RUN = "run";
    public static final String WICKET = "wicket";
    public static final String RUNS = "runs";
    public static final String WICKETS = "wickets";
    public static final String MATCH_NUM = "match_number";
    public static final String TEAM_NAME_ONE = "team_name_one";
    public static final String TEAM_NAME_TWO = "team_name_two";
    public static final String MATCH_DETAIL = "match_detail";
    public static final String WIN = "win";
    public static final String LOST = "lost";
    public static final String CITY_NAME = "city_name";
    public static String TIE = "tie";

    public static final String IS_USER_LOGGED_IN = "isUserLoggedIn";

    /**
     * Constants for firebase analytics
     */
    public static String PARAM_SCREEN_NAME = "screen_name";
    public static String PARAM_YEAR = "year";
    public static String PARAM_TEAM_NAME = "team_name";
    public static String PARAM_MATCH_NO = "match_number";
    public static String PARAM_ORDER_BY = "order_by";
    public static String PARAM_ORDER_BY_BALLS_FACED = "order_by_balls_faced";
    public static String PARAM_ORDER_BY_RUNS_SCORED = "order_by_runs_scored";
    public static String PARAM_ORDER_BY_FOURS = "order_by_fours";
    public static String PARAM_ORDER_BY_OVERS_BOWLED = "order_by_overs_bowled";
    public static String PARAM_ORDER_BY_WICKETS_TOOK = "order_by_wickets_took";
    public static String PARAM_ORDER_BY_MAIDEN = "order_by_maidens";
    public static String PARAM_ORDER_BY_CATCHES = "order_by_catches";
    public static String PARAM_ORDER_BY_RUNOUTS = "order_by_runouts";
    public static String PARAM_ORDER_BY_STUMPS = "order_by_stumps";
    public static String PARAM_ORDER_BY_MATCHES_PLAYED = "order_by_matches_played";
    public static String PARAM_ORDER_BY_MATCHES_WINS = "order_by_matches_wins";
    public static String PARAM_ORDER_BY_MATCHES_LOST = "order_by_matches_lost";
    public static String PARAM_ORDER_BY_TOTAL_POINTS = "order_by_total_points";

    public static final String PARAM_SCREEN_NAME_HOME = "screen_name_home";
    public static final String PARAM_YEAR_2017 = "2017";
    public static final String PARAM_YEAR_2018 = "2018";
    public static String PARAM_SCREEN_NAME_TEAMS = "screen_name_teams";
    public static String PARAM_SCREEN_NAME_POOLS = "screen_name_pools";
    public static String PARAM_SCREEN_NAME_MATCHES = "screen_name_matches";
    public static String PARAM_SCREEN_NAME_BATSMEN_STATS = "screen_name_batsmen_stats";
    public static String PARAM_SCREEN_NAME_BOWLER_STATS = "screen_name_bowler_stats";
    public static String PARAM_SCREEN_NAME_FIELDING_STATS = "screen_name_fielding_stats";
    public static String PARAM_SCREEN_NAME_TEAM_STANDINGS = "screen_name_team_standings";
    public static String PARAM_SCREEN_NAME_TEAM_DETAIL = "screen_name_team_detail";
    public static String PARAM_SCREEN_NAME_PLAYER_DETAIL = "screen_name_player_detail";
    public static String PARAM_SCREEN_NAME_MATCH_DETAIL = "screen_name_match_detail";
    public static String PARAM_SCREEN_NAME_RULES = "screen_name_rules";
    public static String PARAM_SCREEN_NAME_LOCATION = "screen_name_locations";
    public static String PARAM_SCREEN_NAME_SPONSERS = "screen_name_sponsers";
    public static String PARAM_SCREEN_NAME_ABOUT_US = "screen_name_about_us";
    public static final String PARAM_SCREEN_NAME_LOGIN = "screen_name_login";
    public static final String PARAM_SCREEN_NAME_LOGOUT = "screen_name_logout";
    public static final String PARAM_SCREEN_NAME_SHARE = "screen_name_share";
    public static final String PARAM_SCREEN_NAME_TEAM_STANDING_DETAIL = "screen_team_standing_detail";

    public static String EVENT_VIEW = "event_view";
    public static String EVENT_CLICKED = "event_clicked";
    public static String TRUE = "true";
    public static final String FALSE = "false";
    public static int RUNS_FOR_WICKET = 3;

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

    public static void setSharedPrefrence(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences
                (context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getSharedPrefrenceString(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences
                (context);
        return sharedPreferences.getString(key, "");
    }
}

package com.example.rakshith.cricketapp.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rakshith.cricketapp.Models.BattingModel;
import com.example.rakshith.cricketapp.Models.BowlingModel;
import com.example.rakshith.cricketapp.Models.MatchesModel;
import com.example.rakshith.cricketapp.Models.PointsTableModel;
import com.example.rakshith.cricketapp.Models.ValuablePlayerModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rakshith
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CricketApp";

    public static final String TABLE_POINTS_TABLE = "pointTable";
    public static final String TABLE_MATCHES = "Matches";
    public static final String TABLE_BATTING = "Batting";
    public static final String TABLE_BOWLING = "Bowling";
    public static final String TABLE_VALUABLE_PLAYER = "ValuablePlayer";

    private static final String MY_POINTS_TABLE_TEAM_NAME = "myPointsTableTeamName";
    private static final String MY_POINTS_TABLE_PLAYED = "myPointsTablePlayed";
    private static final String MY_POINTS_TABLE_WIN = "myPointsTableWin";
    private static final String MY_POINTS_TABLE_LOSS = "myPointsTableLoss";
    private static final String MY_POINTS_TABLE_POINTS = "myPointsTablePoints";
    private static final String MY_POINTS_TABLE_NRR = "myPointsTableNrr";

    private static final String MY_MATCHES_ID = "myMatchesId";
    private static final String MY_MATCHES_NUM = "myMatchesNum";
    private static final String MY_MATCHES_TEAM_ONE = "myMatchesTeamOne";
    private static final String MY_MATCHES_TEAM_TWO = "myMatchesTeamTwo";
    private static final String MY_MATCHES_TOSS = "myMatchesToss";
    private static final String MY_MATCHES_WINNER = "myMatchesWinner";

    private static final String MY_BATTING_BATSMAN_NAME = "myBattingBatsmanName";
    private static final String MY_BATTING_BALLS_FACED = "myBattingBallsFaced";
    private static final String MY_BATTING_RUNS_SCORED = "myBattingRunsScored";

    private static final String MY_BOWLING_BOWLER_NAME = "myBowlingBowlerName";
    private static final String MY_BOWLING_BALLS_BOWLED = "myBowlingBallsBowled";
    private static final String MY_BOWLING_WICKETS_TAKEN = "myBowlingWicketsTaken";

    private static final String MY_VALUABLE_PLAYER_NAME = "myValuablePlayerName";
    private static final String MY_VALUABLE_PLAYER_RUNS = "myValuablePlayerRuns";
    private static final String MY_VALUABLE_PLAYER_WICKET = "myValuablePlayerWicket";
    private static final String MY_VALUABLE_PLAYER_CAUGHT = "myValuablePlayerCaught";
    private static final String MY_VALUABLE_PLAYER_RUNOUT = "myValuablePlayerRunout";
    private static final String MY_VALUABLE_PLAYER_POINTS = "myValuablePlayerPoints";


    private static final String CREATE_TABLE_TEAMS= "CREATE TABLE " + TABLE_POINTS_TABLE + "(" + MY_POINTS_TABLE_TEAM_NAME + " TEXT,"
            + MY_POINTS_TABLE_PLAYED + " TEXT," + MY_POINTS_TABLE_WIN + " TEXT,"
            + MY_POINTS_TABLE_LOSS + " TEXT," + MY_POINTS_TABLE_POINTS + " INTEGER,"
            + MY_POINTS_TABLE_NRR + " TEXT" + ")";

    private static final String CREATE_TABLE_MATCHES= "CREATE TABLE " + TABLE_MATCHES + "(" + MY_MATCHES_NUM + " TEXT,"
            + MY_MATCHES_ID + " INTEGER AUTO INCREMENT,"
            + MY_MATCHES_TEAM_ONE + " TEXT," + MY_MATCHES_TEAM_TWO + " TEXT,"
            + MY_MATCHES_TOSS + " TEXT," + MY_MATCHES_WINNER + " TEXT" + ")";

    private static final String CREATE_TABLE_BATTING= "CREATE TABLE " + TABLE_BATTING + "(" + MY_BATTING_BATSMAN_NAME + " TEXT,"
            + MY_BATTING_BALLS_FACED + " INTEGER," + MY_BATTING_RUNS_SCORED + " INTEGER" + ")";

    private static final String CREATE_TABLE_BOWLING = "CREATE TABLE " + TABLE_BOWLING + "(" + MY_BOWLING_BOWLER_NAME + " TEXT,"
            + MY_BOWLING_BALLS_BOWLED + " INTEGER," + MY_BOWLING_WICKETS_TAKEN + " INTEGER" + ")";

    private static final String CREATE_TABLE_VALUABLE_PLAYER= "CREATE TABLE " + TABLE_VALUABLE_PLAYER + "(" + MY_VALUABLE_PLAYER_NAME + " TEXT,"
            + MY_VALUABLE_PLAYER_RUNS + " INTEGER," + MY_VALUABLE_PLAYER_WICKET + " INTEGER,"
            + MY_VALUABLE_PLAYER_CAUGHT + " INTEGER," + MY_VALUABLE_PLAYER_RUNOUT + " INTEGER,"
            + MY_VALUABLE_PLAYER_POINTS + " INTEGER" + ")";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TEAMS);
        db.execSQL(CREATE_TABLE_MATCHES);
        db.execSQL(CREATE_TABLE_BATTING);
        db.execSQL(CREATE_TABLE_BOWLING);
        db.execSQL(CREATE_TABLE_VALUABLE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_POINTS_TABLE);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_MATCHES);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_BATTING);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_BOWLING);
        db.execSQL("DROP TABLE IF EXIST " + TABLE_VALUABLE_PLAYER);
        onCreate(db);
    }

    public long insertPointsTable(PointsTableModel pointsTableModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MY_POINTS_TABLE_TEAM_NAME, pointsTableModel.getTeamName());
        contentValues.put(MY_POINTS_TABLE_PLAYED, pointsTableModel.getMatchesPlayed());
        contentValues.put(MY_POINTS_TABLE_WIN, pointsTableModel.getWins());
        contentValues.put(MY_POINTS_TABLE_LOSS, pointsTableModel.getLoss());
        contentValues.put(MY_POINTS_TABLE_POINTS, pointsTableModel.getPoints());
        contentValues.put(MY_POINTS_TABLE_NRR, pointsTableModel.getNetrunRate());

        long pointsTableId = sqLiteDatabase.insert(TABLE_POINTS_TABLE, null, contentValues);
        return pointsTableId;
    }

    public long insertMatches(MatchesModel matchesModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MY_MATCHES_NUM, matchesModel.getMatchNo());
        contentValues.put(MY_MATCHES_TEAM_ONE, matchesModel.getTeamOne());
        contentValues.put(MY_MATCHES_TEAM_TWO, matchesModel.getTeamSecond());
        contentValues.put(MY_MATCHES_TOSS, matchesModel.getToss());
        contentValues.put(MY_MATCHES_WINNER, matchesModel.getWinner());

        long matchesId = sqLiteDatabase.insert(TABLE_MATCHES, null, contentValues);
        return matchesId;
    }

    public long insertBatting(BattingModel battingModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MY_BATTING_BATSMAN_NAME, battingModel.getBatsmanName());
        contentValues.put(MY_BATTING_BALLS_FACED, battingModel.getBallsFaced());
        contentValues.put(MY_BATTING_RUNS_SCORED, battingModel.getRunsScored());

        long battingId = sqLiteDatabase.insert(TABLE_BATTING, null, contentValues);
        return battingId;
    }

    public long insertBowling(BowlingModel bowlingModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MY_BATTING_BATSMAN_NAME, bowlingModel.getBowlerName());
        contentValues.put(MY_BATTING_BALLS_FACED, bowlingModel.getBallsBowled());
        contentValues.put(MY_BATTING_RUNS_SCORED, bowlingModel.getWicketsTaken());

        long bowlingId = sqLiteDatabase.insert(TABLE_BOWLING, null, contentValues);
        return bowlingId;
    }

    public long insertValuablePlayer(ValuablePlayerModel valuablePlayerModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MY_VALUABLE_PLAYER_NAME, valuablePlayerModel.getPlayerName());
        contentValues.put(MY_VALUABLE_PLAYER_RUNS, valuablePlayerModel.getRunsScored());
        contentValues.put(MY_VALUABLE_PLAYER_WICKET, valuablePlayerModel.getWicketTaken());
        contentValues.put(MY_VALUABLE_PLAYER_CAUGHT, valuablePlayerModel.getCaught());
        contentValues.put(MY_VALUABLE_PLAYER_RUNOUT, valuablePlayerModel.getRunOuts());
        contentValues.put(MY_VALUABLE_PLAYER_POINTS, valuablePlayerModel.getPoints());

        long valuablePlayerId = sqLiteDatabase.insert(TABLE_POINTS_TABLE, null, contentValues);
        return valuablePlayerId;
    }

//    public List<PointsTableModel> getAllPointsTable() {
//        List<PointsTableModel> pointsTableList = new ArrayList<PointsTableModel>();
//        String selectQuery = "SELECT * FROM " + TABLE_POINTS_TABLE + " ORDER BY " + MY_POINTS_TABLE_POINTS + " DESC";
//
//        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                PointsTableModel pointsTableModel = new PointsTableModel();
//                pointsTableModel.setMatchNo(cursor.getString(cursor.getColumnIndex(MY_MATCHES_NUM)));
//                pointsTableModel.setTeamOne(cursor.getString(cursor.getColumnIndex(MY_MATCHES_TEAM_ONE)));
//                pointsTableModel.setTeamSecond(cursor.getString(cursor.getColumnIndex(MY_MATCHES_TEAM_TWO)));
//                pointsTableModel.setToss(cursor.getString(cursor.getColumnIndex(MY_MATCHES_TOSS)));
//                pointsTableModel.setWinner(cursor.getString(cursor.getColumnIndex(MY_MATCHES_WINNER)));
//
//                pointsTableList.add(pointsTableModel);
//            } while (cursor.moveToNext());
//        }
//        return pointsTableList;
//    }

    public List<MatchesModel> getAllMatches() {
        List<MatchesModel> matchesList = new ArrayList<MatchesModel>();
        String selectQuery = "SELECT * FROM " + TABLE_MATCHES + " ORDER BY " + MY_MATCHES_ID + " ASC";

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                MatchesModel matchesModel = new MatchesModel();
                matchesModel.setMatchNo(cursor.getString(cursor.getColumnIndex(MY_MATCHES_NUM)));
                matchesModel.setTeamOne(cursor.getString(cursor.getColumnIndex(MY_MATCHES_TEAM_ONE)));
                matchesModel.setTeamSecond(cursor.getString(cursor.getColumnIndex(MY_MATCHES_TEAM_TWO)));
                matchesModel.setToss(cursor.getString(cursor.getColumnIndex(MY_MATCHES_TOSS)));
                matchesModel.setWinner(cursor.getString(cursor.getColumnIndex(MY_MATCHES_WINNER)));

                matchesList.add(matchesModel);
            } while (cursor.moveToNext());
        }
        return matchesList;
    }

    public List<BattingModel> getAllBattingStats() {
        List<BattingModel> battingList = new ArrayList<BattingModel>();
        String selectQuery = "SELECT * FROM " + TABLE_BATTING + " ORDER BY " + MY_BATTING_RUNS_SCORED + " DESC";

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BattingModel battingModel = new BattingModel();
                battingModel.setBatsmanName(cursor.getString(cursor.getColumnIndex(MY_BATTING_BATSMAN_NAME)));
                battingModel.setBallsFaced(cursor.getString(cursor.getColumnIndex(MY_BATTING_BALLS_FACED)));
                battingModel.setRunsScored(cursor.getInt(cursor.getColumnIndex(MY_BATTING_RUNS_SCORED)));

                battingList.add(battingModel);
            } while (cursor.moveToNext());
        }
        return battingList;
    }

    public List<BattingModel> getAllBowlingStats() {
        List<BattingModel> bowlingList = new ArrayList<BattingModel>();
        String selectQuery = "SELECT * FROM " + TABLE_BOWLING + " ORDER BY " + MY_BOWLING_WICKETS_TAKEN + " DESC";

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BattingModel bowlingModel = new BattingModel();
                bowlingModel.setBatsmanName(cursor.getString(cursor.getColumnIndex(MY_BOWLING_BOWLER_NAME)));
                bowlingModel.setBallsFaced(cursor.getString(cursor.getColumnIndex(MY_BOWLING_BALLS_BOWLED)));
                bowlingModel.setRunsScored(cursor.getInt(cursor.getColumnIndex(MY_BOWLING_WICKETS_TAKEN)));

                bowlingList.add(bowlingModel);
            } while (cursor.moveToNext());
        }
        return bowlingList;
    }

    public List<ValuablePlayerModel> getAllValuablePlayerStats() {
        List<ValuablePlayerModel> valuablePlayerList = new ArrayList<ValuablePlayerModel>();
        String selectQuery = "SELECT * FROM " + TABLE_VALUABLE_PLAYER + " ORDER BY " + MY_VALUABLE_PLAYER_POINTS + " DESC";

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ValuablePlayerModel valuablePlayerModel = new ValuablePlayerModel();
                valuablePlayerModel.setPlayerName(cursor.getString(cursor.getColumnIndex(MY_VALUABLE_PLAYER_NAME)));
                valuablePlayerModel.setRunsScored(cursor.getString(cursor.getColumnIndex(MY_VALUABLE_PLAYER_RUNS)));
                valuablePlayerModel.setWicketTaken(cursor.getString(cursor.getColumnIndex(MY_VALUABLE_PLAYER_WICKET)));
                valuablePlayerModel.setCaught(cursor.getString(cursor.getColumnIndex(MY_VALUABLE_PLAYER_CAUGHT)));
                valuablePlayerModel.setRunOuts(cursor.getString(cursor.getColumnIndex(MY_VALUABLE_PLAYER_RUNOUT)));
                valuablePlayerModel.setPoints(cursor.getInt(cursor.getColumnIndex(MY_VALUABLE_PLAYER_POINTS)));

                valuablePlayerList.add(valuablePlayerModel);
            } while (cursor.moveToNext());
        }
        return valuablePlayerList;
    }

    public void closeDB() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }

    public void deleteTable(String tableName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(tableName, null, null);
    }

    public boolean isDataPresent(String tableName, String fieldName, String fieldValue) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String rawQuery = "SELECT * FROM " + tableName + " WHERE " + fieldName + " = " + fieldValue;

        Cursor cursor = sqLiteDatabase.rawQuery(rawQuery, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}

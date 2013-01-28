package com.refnil.uqcard.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "uqcard.db";
	public static final int DATABASE_VERSION = 1;	
	
  public static final String TABLE_CARDS = "card";
  public static final String CARDS_ID = "id";
  public static final int CARDS_NUM_ID = 0;
  public static final String CARDS_NAME = "name";
  public static final int CARDS_NUM_NAME = 1;
  public static final String CARDS_DESCRIPTION = "description";
  public static final int CARDS_NUM_DESCRIPTION = 2;
  public static final String CARDS_COST = "cost";
  public static final int CARDS_NUM_COST = 3;
  public static final String CARDS_FLAVOR = "flavor";
  public static final int CARDS_NUM_FLAVOR = 4;
  public static final String CARDS_IMAGE = "image";
  public static final int CARDS_NUM_IMAGE = 5;
  
  public static final String TABLE_CREATURECARDS = "card";
  public static final String CREATURECARDS_IDCARD = "idcard";
  public static final int CREATURECARDS_NUM_ID = 0;
  public static final String CREATURECARDS_ATTACK = "attack";
  public static final int CREATURECARDS_NUM_ATTACK = 1;
  public static final String CREATURECARDS_DEFENSE = "defense";
  public static final int CREATURECARDS_NUM_DEFENSE = 2;
  public static final String CREATURECARDS_HEALTH = "health";
  public static final int CREATURECARDS_NUM_HEALTH = 3;

  static final String CARDS_TABLE_CREATE =
		  "create table " + TABLE_CARDS +
		  "(" +
		CARDS_ID + " integer primary key autoincrement, " +
		CARDS_NAME + " text not null, " + CARDS_DESCRIPTION + " text not null, " +
		CARDS_COST + " integer not null, " + CARDS_FLAVOR + " text not null, " +
		CARDS_IMAGE + " blob not null " + 
		");";
  
  static final String CREATURECARDS_TABLE_CREATE =
		  "create table " +  TABLE_CREATURECARDS +
		  "(" +
		  CREATURECARDS_IDCARD + " integer not null, " +
		  CREATURECARDS_ATTACK + " integer not null, " +
		  CREATURECARDS_DEFENSE + " integer not null, " +
		  CREATURECARDS_HEALTH + " integer not null, " +
		  " FOREING KEY("+CREATURECARDS_IDCARD+") REFENRENCES "+ TABLE_CARDS +"("+ CARDS_ID+")"+
		  ");";


  public MySQLiteHelper(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(CARDS_TABLE_CREATE);
    database.execSQL(CREATURECARDS_TABLE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARDS);
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_CREATURECARDS);
    onCreate(db);
  }

} 
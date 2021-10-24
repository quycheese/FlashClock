package com.example.flashclock.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public Context context;
    public static final String DATABASE_NAME = "FlashClock.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "FlashClock";
    public static final String COLUMN_TIMER = "_time";
    public static final String COLUMN_NOTICE = "_notice";
    public static final String COLUMN_MUSIC = "_music";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(
              "CREATE TABLE FlashClock" +
                      "(time integer, notice text)");

      db.execSQL( "INSERT INTO FlashClock VALUES ('9:30', 'Good morning') ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS FlashClock");
        onCreate(db);
    }
    public void btnSaveTime (int time, String notice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_TIMER, time);
        cv.put(COLUMN_NOTICE, notice);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
    }
}

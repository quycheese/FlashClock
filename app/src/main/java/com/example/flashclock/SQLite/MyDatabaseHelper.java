package com.example.flashclock.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.flashclock.Model.ListTime;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public Context context;
    public static final String DATABASE_NAME = "flash_lock";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "FlashClock";
    public static final String COLUMN_TIMER = "time";
    public static final String COLUMN_NOTICE = "notice";
    public static final String COLUMN_MUSIC = "_music";

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL(
              "CREATE TABLE FlashClock " +
                      "(id integer PRIMARY KEY , time text, notice text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        db.execSQL("DROP TABLE IF EXISTS FlashClock");
//        onCreate(db);
    }
    public void btnSaveTime (String time, String notice) {
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

    public List<ListTime> selectAllAlarm () {
        List<ListTime> alarms = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT id, time, notice FROM FlashClock ", null);
        if (c.moveToFirst()){
            do {
                ListTime listTime = new ListTime();
                listTime.setId(c.getInt(0));
                listTime.setTime(c.getString(1));
                listTime.setNotice(c.getString(2));
                alarms.add(listTime);
            } while(c.moveToNext());
        }
        c.close();
        db.close();
        return alarms;
    }

    public void deleteAlarm(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete("FlashClock",  // Where to delete
                "id" + " = ?",
                new String[]{Integer.toString(id)});  // What to delete
        sqLiteDatabase.close();

    }


}

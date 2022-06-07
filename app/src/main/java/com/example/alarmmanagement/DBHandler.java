package com.example.alarmmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private Button deleteBtn;

    private static final String DB_NAME = "alarmdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "myAlarms";
    private static final String ID_COL = "id";
    private static final String NAME_COL = "DATE";
    private static final String DURATION_COL = "MINUTES";
    private static final String DESCRIPTION_COL = "description";
    private static final String TRACKS_COL = "HOURS";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DURATION_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT,"
                + TRACKS_COL + " TEXT)";

        db.execSQL(query);
    }

    public void addNewAlarm(String courseName, String courseDuration, String courseDescription, String courseTracks) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        courseDescription = courseDescription;

        values.put(NAME_COL, courseName);
        values.put(DURATION_COL, courseDuration);
        values.put(DESCRIPTION_COL, courseDescription);
        values.put(TRACKS_COL, courseTracks);


        db.insert(TABLE_NAME, null, values);

        db.close();
    }


    public ArrayList<AlarmModal> readAlarms() {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorAlarms = db.rawQuery("SELECT * FROM " + TABLE_NAME , null);

        ArrayList<AlarmModal> AlarmModalArrayList = new ArrayList<>();

        if (cursorAlarms.moveToFirst()) {
            do {

                AlarmModalArrayList.add(new AlarmModal(cursorAlarms.getString(1),
                        cursorAlarms.getString(4),
                        cursorAlarms.getString(2),
                        cursorAlarms.getString(3) + cursorAlarms.getString(0)));
            } while (cursorAlarms.moveToNext());

        }
        cursorAlarms.close();
        return AlarmModalArrayList;
    }
    public void deleteAlarm(String Date, String hour) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,"MINUTES" + "=? AND " + "HOURS" + "=?",new String[] {Date, hour});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
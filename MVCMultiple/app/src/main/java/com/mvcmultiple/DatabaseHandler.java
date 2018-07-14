package com.mvcmultiple;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    //Database Name
    private static final String DATABASE_NAME="mydb";

    //Table Name
    private static final String TABLE_MYTABLE="mytable";

    //Column name
    private static final String ROLL_NO="rollno";
    private static final String NAME="name";
    private static final String MARKS="marks";

    //Create Table statement
    private static final String CREATE_TABLE_MYTABLE="CREATE TABLE "
            + TABLE_MYTABLE + "(" + ROLL_NO + " INTEGER," + NAME
            + " TEXT," + MARKS + " INTEGER" + ")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_MYTABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MYTABLE);

        // create new tables
        onCreate(db);
    }

    /**
     * Inserting
     */
    public long addItems(MyTable myTable) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ROLL_NO, myTable.getRoll());
        values.put(NAME, myTable.getName());
        values.put(MARKS, myTable.getMarks());

        // insert row
        long myTableId = db.insert(TABLE_MYTABLE, null, values);
        db.close();
        return myTableId;
    }

    /**
     * getting all todos
     * */
    public List<MyTable> getAllItems() {
        List<MyTable> items = new ArrayList<MyTable>();
        String selectQuery = "SELECT  * FROM " + TABLE_MYTABLE;

        //Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                MyTable mytbl = new MyTable();
                mytbl.setRoll(c.getInt((c.getColumnIndex(ROLL_NO))));
                mytbl.setName((c.getString(c.getColumnIndex(NAME))));
                mytbl.setMarks(c.getInt(c.getColumnIndex(MARKS)));

                // adding to mytbl list
                items.add(mytbl);
            } while (c.moveToNext());
        }

        return items;
    }

    /**
     * Updating items
     */
    public int updateItems(MyTable mytbl) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(ROLL_NO, mytbl.getRoll());
        values.put(NAME, mytbl.getName());
        values.put(MARKS, mytbl.getMarks());

        // updating row
        return db.update(TABLE_MYTABLE, values, ROLL_NO + " = ?",
                new String[] { String.valueOf(mytbl.getRoll()) });
    }

    /**
     * Deleting items
     */
    public void deleteItems(long mytbl) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MYTABLE, ROLL_NO + " = ?",
                new String[] { String.valueOf(mytbl) });
    }


}


package com.dbsimple;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="mydb";
    private static final String TABLE_MYTABLE="mytable";
    private static final String ROLL="roll";
    private static final String NAME="name";
    private static final String MARKS="marks";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_MYTABLE_TABLE = "CREATE TABLE " + TABLE_MYTABLE + "("
                + ROLL + " TEXT," + NAME + " TEXT,"
                + MARKS + " TEXT" + ")";
        db.execSQL(CREATE_MYTABLE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MYTABLE);
        onCreate(db);
    }

    public boolean insertValue(String roll, String name, String marks){
        //Inserting Records
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        contentValues.put(ROLL,roll);
        contentValues.put(NAME,name);
        contentValues.put(MARKS,marks);
        long result = db.insert(TABLE_MYTABLE,null,contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllValues(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_MYTABLE,null);
        return res;
    }

    public boolean updateValues(String roll, String name, String marks){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ROLL,roll);
        contentValues.put(NAME,name);
        contentValues.put(MARKS,marks);
        db.update(TABLE_MYTABLE,contentValues, "roll=?", new String[] { roll });
        return true;
    }

    public Integer deleteValues(String roll){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_MYTABLE,"roll=?",new String[] { roll });
    }
}

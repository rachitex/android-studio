package com.mms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "mmsDB";

    //Tables Names
    private static final String TABLE_ADMIN="admin_tbl";
    private static final String TABLE_USER="user_tbl";
    private static final String TABLE_FOOD="food_tbl";
    private static final String TABLE_DAY="day_tbl";

    //Columns of TABLE_ADMIN
    private static final String ADMIN_ID="a_id";
    private static final String ADMIN_NAME="a_name";
    private static final String ADMIN_PASSWORD="a_password";

    //Columns of TABLE_USER
    private static final String USER_ID="u_id";
    private static final String USER_NAME="u_name";
    private static final String USER_PASSWORD="u_password";
    private static final String USER_TYPE="u_type";

    //Columns of TABLE_FOOD
    private static final String FOOD_ID="f_id";
    private static final String FOOD_NAME="f_name";
    private static final String FOOD_TYPE="f_type";
    private static final String FOOD_UTENSILS="f_utensils";
    private static final String FOOD_DAY="f_day";

    //Columns of TABLE_DAY
    private static final String DAY_ID="d_id";
    private static final String DAY_NAME="d_name";
    private static final String BREAKFAST="breakfast";
    private static final String LUNCH="lunch";
    private static final String DINNER="dinner";
    private static final String UTENSILS_BREAKFAST="utensils_breakfast";
    private static final String UTENSILS_LUNCH="utensils_lunch";
    private static final String UTENSILS_DINNER="utensils_dinner";

    // Create statements for Tables
    private static final String CREATE_TABLE_ADMIN = "CREATE TABLE "+ TABLE_ADMIN +
            "("+ADMIN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +ADMIN_NAME+" TEXT, "
            +ADMIN_PASSWORD+" TEXT" + ")";

    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS "+ TABLE_USER +
            "("+USER_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +USER_NAME+" TEXT, "
            +USER_PASSWORD+" TEXT, "
            +USER_TYPE+" TEXT" + ")";

    private static final String CREATE_TABLE_FOOD = "CREATE TABLE IF NOT EXISTS "+ TABLE_FOOD +
            "("+FOOD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +FOOD_NAME+" TEXT, "
            +FOOD_TYPE+" TEXT, "
            +FOOD_UTENSILS+" TEXT, "
            +FOOD_DAY+" TEXT" + ")";

    private static final String CREATE_TABLE_DAY = "CREATE TABLE IF NOT EXISTS "+ TABLE_DAY +
            "("+DAY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +DAY_NAME+" TEXT, "
            +BREAKFAST+" TEXT, "
            +LUNCH+" TEXT, "
            +DINNER+" TEXT"
            +UTENSILS_BREAKFAST+" TEXT, "
            +UTENSILS_LUNCH+" TEXT, "
            +UTENSILS_DINNER+" TEXT "+ ")";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creating tables

        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL("insert into " +TABLE_ADMIN+ "(" + ADMIN_ID + ","
                + ADMIN_NAME + "," +ADMIN_PASSWORD+") values(1,'admin','root')");

        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_DAY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // On upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_DAY);
        // Create new tables
        onCreate(db);
    }

    // admin_tbl methods

    public Cursor getAllAdminValues(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_ADMIN,null);
        /*if (res!=null)
        {
            res.close();
        }*/
        //db.close();
        return res;
    }

    // user_tbl methods

    public boolean insertUserValue(String u_name, String u_password, String u_type){
        //Inserting Records
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        //contentValues.put(USER_ID, u_id);
        contentValues.put(USER_NAME,u_name);
        contentValues.put(USER_PASSWORD,u_password);
        contentValues.put(USER_TYPE,u_type);
        long result = db.insert(TABLE_USER,null,contentValues);

        //db.close();
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllUserValues(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res=db.rawQuery("select * from "+TABLE_USER,null);
        /*if (res!=null)
        {
            res.close();
        }*/

        //db.close();
        return res;
    }

    public boolean updateUserValues(String u_id, String u_name, String u_password, String u_type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_ID,u_id);
        contentValues.put(USER_NAME,u_name);
        contentValues.put(USER_PASSWORD,u_password);
        contentValues.put(USER_TYPE,u_type);
        db.update(TABLE_USER,contentValues, "u_id=?", new String[] { u_id });

        //db.close();
        return true;
    }

    public Integer deleteUserValues(String u_id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_USER,"u_id=?",new String[] { u_id });
    }

    public Cursor getAllRectorValues(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TABLE_USER+" WHERE u_name='Rector'",null);

            //db.close();
            return res;
    }

    public Cursor getAllStudentValues(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TABLE_USER+" WHERE u_name='Student'",null);

        //db.close();
        return res;
    }

    // food_tbl methods

    public Cursor getAllFoodValues(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TABLE_FOOD,null);

        //db.close();
        return res;
    }

    public boolean insertFoodValue(String f_name, String f_type, String f_utensils, String f_day){
        //Inserting Records
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues=new ContentValues();
        //contentValues.put(USER_ID, u_id);
        contentValues.put(FOOD_NAME,f_name);
        contentValues.put(FOOD_TYPE,f_type);
        contentValues.put(FOOD_UTENSILS,f_utensils);
        contentValues.put(FOOD_DAY,f_day);
        long result = db.insert(TABLE_FOOD,null,contentValues);

        //db.close();
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateFoodValues(String f_id, String f_name, String f_type, String f_utensils, String f_day){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_ID,f_id);
        contentValues.put(FOOD_NAME,f_name);
        contentValues.put(FOOD_TYPE,f_type);
        contentValues.put(FOOD_UTENSILS,f_utensils);
        contentValues.put(FOOD_DAY,f_day);
        db.update(TABLE_FOOD,contentValues, "f_id=?", new String[] { f_id });

        //db.close();
        return true;
    }

    public Integer deleteFoodValues(String f_id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_FOOD,"f_id=?",new String[] { f_id });
    }

    // day_tbl methods

    public Cursor getAllDayValues(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from "+TABLE_DAY,null);

        //db.close();
        return res;
    }
}

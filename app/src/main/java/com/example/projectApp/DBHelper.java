package com.example.projectApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Salaty.db";    // Database NAME
    public static final String TABLE_NAME = "user";   // Table NAME
    public static final int DATABASE_Version = 5;    // Database Version
    public static final String UID="_id";     // Column I (Primary Key)
    public static final String NAME = "name";    //Column II
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String TRADE = "trade";
    private static final String CREATE_TABLE_USER = "CREATE TABLE "+TABLE_NAME+
            " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME +" VARCHAR(225),"+ EMAIL + " VARCHAR(50),"+PASSWORD+" VARCHAR(50),"+TRADE+" INTEGER(50))";

    public static final String TABLE_NAME_P = "product";
    public static final String PID="_pid";
    public static final String PRODNAME="name";
    public static final String PRODPRICE="price";
    public static final String PRODDESC="des";
    private static final String CREATE_TABLE_PRODUCT = "CREATE TABLE "+TABLE_NAME_P+
            " ("+PID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + PRODNAME +" VARCHAR(50), "+ PRODPRICE + " VARCHAR(50), "+ PRODDESC +"  VARCHAR(225))";


    private static final String DROP_TABLE_U ="DROP TABLE IF EXISTS "+TABLE_NAME;
    private static final String DROP_TABLE_P ="DROP TABLE IF EXISTS "+TABLE_NAME_P;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }

    public void onCreate(SQLiteDatabase db) {
        //database name db
        try {
            db.execSQL(CREATE_TABLE_USER); //create table in db
            db.execSQL(CREATE_TABLE_PRODUCT);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //upgrade db, change version
        try {
            db.execSQL(DROP_TABLE_U);
            db.execSQL(DROP_TABLE_P);
            onCreate(db);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }}
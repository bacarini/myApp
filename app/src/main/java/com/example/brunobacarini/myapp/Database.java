package com.example.brunobacarini.myapp;

/**
 * Created by brunobacarini on 23/02/2017.
 */

import java.io.IOException;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Database extends SQLiteOpenHelper {
    private final Context _context;
    private static final String KEY_ID = "id";
    private static final String KEY_BRAND = "brand";
    private static final String KEY_MODEL = "model";
    private static final String KEY_OWNER = "owner";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CarsDatabase";
    private static final String TABLE_NAME = "cars";
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    KEY_ID + " INTEGER PRIMARY KEY, " +
                    KEY_BRAND + " STRING, " +
                    KEY_MODEL + " STRING, " +
                    KEY_OWNER + " STRING);";

    Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    public void addCar(String brand, String model, String owner) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_BRAND, brand); //
        values.put(KEY_MODEL, model); //
        values.put(KEY_OWNER, owner); //

        // Inserting Row
        long id = db.insert(TABLE_NAME, null, values);
        db.close(); // Closing database connection

        Log.d("New car", "ID: " + id);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public Cursor getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME,
                null
        );
        c.moveToFirst();
        db.close();
        return c;
    }

    public void deleteCar(String ID){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_NAME, "Id=?", new String[] { ID });
    }

    public void updateCar(String owner, String ID){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_OWNER, owner); //

        db.update(TABLE_NAME,values, KEY_ID + " = ?", new String[]{ID} );
        db.close();
    }

    public void clearDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();

    }
}
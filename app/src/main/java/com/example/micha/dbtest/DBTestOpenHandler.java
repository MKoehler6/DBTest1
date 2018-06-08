package com.example.micha.dbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBTestOpenHandler extends SQLiteOpenHelper {

    private static final String TAG = "DBTestOpenHandler";

    private static final String DATABASE_NAME = "dbtest.db";
    private static final int DATABASE_VERSION = 1;

    private static final String _IDA = "_id";
    private static final String _IDB = "_id";
    private static final String A1 = "a1";
    private static final String A2 = "a2";
    private static final String B1 = "b1";
    private static final String B2 = "b2";
    private static final String TABLE_NAME_DBTESTA = "tabelleA";
    private static final String TABLE_NAME_DBTESTB = "tabelleB";

    private static final String TABLE_DBTEST_CREATEA = "CREATE TABLE "
            + TABLE_NAME_DBTESTA + " (" + _IDA + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + A1 + " INTEGER, " + A2 + " INTEGER);";
    private static final String TABLE_DBTEST_CREATEB = "CREATE TABLE "
            + TABLE_NAME_DBTESTB + " (" + _IDB + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + B1 + " INTEGER, " + B2 + " INTEGER);";

    private static final String TABLE_DBTEST_DROPA = "DROP TABLE IF EXISTS " + TABLE_NAME_DBTESTA;
    private static final String TABLE_DBTEST_DROPB = "DROP TABLE IF EXISTS " + TABLE_NAME_DBTESTB;

    public DBTestOpenHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_DBTEST_CREATEA);
        db.execSQL(TABLE_DBTEST_CREATEB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    void insert(int a1, int a2, int b1, int b2) {
        long rowIDA = -1;
        long rowIDB = -1;
        try {
            SQLiteDatabase db = getWritableDatabase();
            Log.d(TAG, "Pfad: " + db.getPath());

            ContentValues valuesA = new ContentValues();
            valuesA.put(A1, a1);
            valuesA.put(A2, a2);
            rowIDA = db.insert(TABLE_NAME_DBTESTA, null, valuesA);
            ContentValues valuesB = new ContentValues();
            valuesB.put(B1, b1);
            valuesB.put(B2, b2);
            rowIDB = db.insert(TABLE_NAME_DBTESTB, null, valuesB);
        } catch (SQLiteException e) {
            Log.e(TAG, "insert()", e);
        } finally {
            Log.d(TAG, "insert(): rowID" + rowIDA);
            Log.d(TAG, "insert(): rowID" + rowIDB);
        }
    }
    void delete() {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL(TABLE_DBTEST_DROPA);
        db.execSQL(TABLE_DBTEST_DROPB);
        onCreate(db);
    }
    public ArrayList readA(){
        SQLiteDatabase db = this.getReadableDatabase();

        try {

            ArrayList intArray = new ArrayList();
            int i = 0;

            Cursor cursor = db.query(TABLE_NAME_DBTESTA,
                    new String[]{A1, A2},
                    null, null, null, null, null);

            try {
                while (cursor.moveToNext()) {
                    intArray.add(cursor.getInt(0));
                    intArray.add(cursor.getInt(1));
                }
                return intArray;

            } finally {
                cursor.close();
            }

        } finally {
            db.close();
        }
    }
    public ArrayList readB(){
        SQLiteDatabase db = this.getReadableDatabase();

        try {

            ArrayList intArray = new ArrayList();
            int i = 0;

            Cursor cursor = db.query(TABLE_NAME_DBTESTB,
                    new String[]{B1, B2},
                    null, null, null, null, null);

            try {
                while (cursor.moveToNext()) {
                    intArray.add(cursor.getInt(0));
                    intArray.add(cursor.getInt(1));
                }
                return intArray;

            } finally {
                cursor.close();
            }

        } finally {
            db.close();
        }
    }
    public ArrayList plus(){
        SQLiteDatabase db = this.getReadableDatabase();

        try {

            ArrayList intArray = new ArrayList();

            Cursor cursorA = db.query(TABLE_NAME_DBTESTA,
                    new String[]{A1 + "+" + A2},
                    null, null, null, null, null);
            Cursor cursorB = db.query(TABLE_NAME_DBTESTB,
                    new String[]{B1 + "+" + B2},
                    null, null, null, null, null);

            try {
                while (cursorA.moveToNext() & cursorB.moveToNext()) {
                    intArray.add(cursorA.getInt(0) + cursorB.getInt(0));
                }
                return intArray;

            } finally {
                cursorA.close();
                cursorB.close();
            }

        } finally {
            db.close();
        }
    }
    public ArrayList mal(){
        SQLiteDatabase db = this.getReadableDatabase();

        try {

            ArrayList intArray = new ArrayList();

            Cursor cursorA = db.query(TABLE_NAME_DBTESTA,
                    new String[]{A1 + "*" + A2},
                    null, null, null, null, null);
            Cursor cursorB = db.query(TABLE_NAME_DBTESTB,
                    new String[]{B1 + "*" + B2},
                    null, null, null, null, null);

            try {
                while (cursorA.moveToNext() & cursorB.moveToNext()) {
                    intArray.add(cursorA.getInt(0) * cursorB.getInt(0));
                }
                return intArray;

            } finally {
                cursorA.close();
                cursorB.close();
            }
        } finally{
                db.close();
        }

    }
}


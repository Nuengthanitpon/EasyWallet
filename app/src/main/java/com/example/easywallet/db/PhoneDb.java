package com.example.easywallet.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Admin on 2/12/2560.
 */

public class PhoneDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "phone.db";
    private static final int DATABASE_VERSION = 9;

    public static final String TABLE_NAME_WALLET= "Money";;

    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_MONNEY = "monney";
    public static final String COL_PICTURE = "picture";

    private static final String CREATE_TABLE_WALLET = "CREATE TABLE "+ TABLE_NAME_WALLET + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITLE + " TEXT, "
            + COL_MONNEY + " TEXT, "
            + COL_PICTURE + " TEXT)";


    public PhoneDb(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_WALLET );
        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE, "คุณพ่อให้เงิน");
        cv.put(COL_MONNEY, "8,000");
        cv.put(COL_PICTURE, "ic_income.png");
        db.insert(TABLE_NAME_WALLET, null, cv);

        cv.put(COL_TITLE, "จ่ายค่าหอ");
        cv.put(COL_MONNEY, "2,500");
        cv.put(COL_PICTURE, "ic_expense.png");
        db.insert(TABLE_NAME_WALLET, null, cv);

        cv.put(COL_TITLE, "ซื้อล็อตเตอรี่1ชุด");
        cv.put(COL_MONNEY, "700");
        cv.put(COL_PICTURE, "ic_expense.png");
        db.insert(TABLE_NAME_WALLET, null, cv);

        cv.put(COL_TITLE, "ถูกล็อตเตอรี่รางวัลที่1");
        cv.put(COL_MONNEY, "30,000,000");
        cv.put(COL_PICTURE, "ic_income.png");
        db.insert(TABLE_NAME_WALLET, null, cv);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_WALLET);
        onCreate(db);
    }
}

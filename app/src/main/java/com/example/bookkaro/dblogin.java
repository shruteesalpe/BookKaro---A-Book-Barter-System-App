package com.example.bookkaro;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelperlogin extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Example.db";
    public static final String TABLE_NAME = "users";
    public static final String COL_1 = "ID";
    public static final String COL_2="NAME";
    public static final String COL_3 = "EMAIL";
    public static final String COL_4 = "PHONENO";
    public static final String COL_5 = "ADDRESS";
    public static final String COL_6 = "CATEGORIES";

//    public static final String COL_6 = "PEOPLE";

    public DBHelperlogin(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, PHONENO INTEGER,ADDRESS TEXT,CATEGORIES TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert data
    public boolean loginData(String name,String email, String phoneno,String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, phoneno);
        contentValues.put(COL_5, address);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // returns true if successful
    }

    // Update data
    public boolean updateData(String id, String name, String age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, age);
        int result = db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return result > 0; // returns true if updated
    }

    // Delete data
    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
    }

    // Select data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
    public boolean categoriesData(String categories) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_6, categories);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // returns true if successful
    }

}

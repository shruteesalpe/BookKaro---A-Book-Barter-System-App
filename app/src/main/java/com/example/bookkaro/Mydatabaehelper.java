package com.example.bookkaro;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Example1.db";
    public static final String TABLE_NAME = "sellers";
    public static final String TABLE_NAME1="BUYERS";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "CATEGORY";
    public static final String COL_4 = "PRICE";
    public static final String COL_5 = "QUANTITY";
    public static final String COL_6 = "IMAGE";
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, CATEGORY TEXT,PRICE INTEGER,QUANTITY INTEGER,IMAGE BLOB)");
        db.execSQL("CREATE TABLE " + TABLE_NAME1 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, CATEGORY TEXT,PRICE INTEGER,QUANTITY INTEGER,IMAGE BLOB)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(db);
    }

    public boolean sellData(String name, String category,String price,String quantity,byte[] image) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_4,price);
        contentValues.put(COL_5,quantity);
        contentValues.put(COL_3,category);
        contentValues.put(COL_6, image);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // returns true if successful
    }
//    public boolean sellData(String name, String category, String price, String quantity) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_2, name);
//        contentValues.put(COL_3, category);
//
//        // Convert price and quantity to integers
//        try {
//            contentValues.put(COL_4, Integer.parseInt(price));
//            contentValues.put(COL_5, Integer.parseInt(quantity));
//        } catch (NumberFormatException e) {
//            Log.e("DB_ERROR", "Invalid input: price or quantity is not a number.");
//            return false;  // Invalid data, return false
//        }
//
//        long result = db.insert(TABLE_NAME, null, contentValues);
//
//        if (result == -1) {
//            Log.e("DB_INSERT_ERROR", "Failed to insert data into " + TABLE_NAME);
//        } else {
//            Log.d("DB_INSERT_SUCCESS", "Data inserted successfully: " + name + ", " + category);
//        }
//
//        return result != -1;  // returns true if successful
//    }

    public boolean donateData(String name, String category,String price,String quantity,byte[] image ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, category);
        contentValues.put(COL_4,price);
        contentValues.put(COL_5,quantity);
        contentValues.put(COL_6, image);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1; // returns true if successful
    }

    // Update data
//    public boolean updateData(String id, String name, String age) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_2, name);
//        contentValues.put(COL_3, age);
//        int result = db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
//        return result > 0; // returns true if updated
//    }
    public boolean insertbuyer(String name,String price){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_4,price);
        long result = db.insert(TABLE_NAME1, null, contentValues);
        return result != -1; // returns true if successful
    }

    // Delete data
    public Integer deleteData(String NAME) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "NAME = ?", new String[]{NAME});
    }

    // Select data
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
    public Cursor getAllbuyed() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME1, null);
    }
    public Cursor getBooksByCategory(String category) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (category.equals("All")) {
            return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        } else {
            return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE CATEGORY = ?", new String[]{category});
        }
    }

}

package mp.zico.org.sqlku;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ProgrammingKnowledge on 4/3/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SqlFour.db";
    public static final String TABLE_NAME = "chart";
//    public static final String COL_1 = "ID";
    public static final String COL_1 = "X";
    public static final String COL_2 = "Y";
//    public static final String COL_3 = "Y";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");
//        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,X INTEGER,Y INTEGER)");
        db.execSQL("create table " + TABLE_NAME +" (X INTEGER PRIMARY KEY, Y INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

//    public boolean insertData(String name,String surname,String marks) {
public boolean insertData(String x,String y) {
    SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,x);
        contentValues.put(COL_2,y);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

//    public boolean updateData(String id,String name,String surname,String marks) {
//    public boolean updateData(String id,String x,String y) {
      public boolean updateData(String x,String y) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,x);
        contentValues.put(COL_2,y);
//        contentValues.put(COL_3,surname);
//        contentValues.put(COL_3,y);
        db.update(TABLE_NAME, contentValues, "X = ?",new String[] { x });
        return true;
    }

    public Integer deleteData (String x) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "X = ?",new String[] {x});
    }
}
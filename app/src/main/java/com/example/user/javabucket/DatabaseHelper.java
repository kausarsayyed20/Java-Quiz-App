package com.example.user.javabucket;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;


/**
 * Created by User on 01/09/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "sign.db";
    private static final String TABLE_NAME = "sign_table";

    private static final String id = "ID";
    private static final String name = "NAME";
    private static final String username = "USERNAME";
    private static final String password = "PASSWORD";
    private static final String email = "EMAIL";
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT , USERNAME TEXT, PASSWORD TEXT,EMAIL TEXT)");
        //db.execSQL("create table " + TABLE_NAME + "(" + id + "INTEGER PRIMARY KEY NOT NULL AUTOINCREMENT," + name + "TEXT NOT NULL," + username + "TEXT NOT NULL," + password + "TEXT NOT NULL," + email + "TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exits " + TABLE_NAME);
        this.onCreate(db);
    }


    public boolean insertlog(String name, String username, String password, String mail) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NAME", name);
        cv.put("USERNAME", username);
        cv.put("PASSWORD", password);
        cv.put("EMAIL", mail);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1)

            return false;

        else

            return true;

    }

    public Cursor getInformation(DatabaseHelper dop) {

        SQLiteDatabase sq = dop.getReadableDatabase();
        String[] col = {username, password};
        Cursor cr = sq.query(TABLE_NAME, col, null, null, null, null, null);


        return cr;

    }
        public String searchPass(String uname)
        {
            SQLiteDatabase db;
            db=this.getReadableDatabase();
            String query="SELECT NAME,PASSWORD FROM "+TABLE_NAME;
            Cursor cursor=db.rawQuery(query,null);
            String a,b;
            b="Not found!!!";
            if(cursor.moveToFirst())
            {
                do {
                    a=cursor.getString(0);

                    if(a.equals(uname))
                    {
                        b=cursor.getString(1);
                        break;
                    }
                }while(cursor.moveToNext());
            }

            return b;
        }

}


package com.esperando_la.esperandola.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by eder on 07/02/2015.
 */
public class SQLHelper extends SQLiteOpenHelper
{

    private String SQL_TABLE = "CREATE TABLE Codigos(id TEXT primary key , status INTEGER NOT NULL, description TEXT NOT NULL)";
    public static final String DATABASE_NAME = "CodigosDB";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS Codigos");
        onCreate(db);
    }

    public void InsertIntoDB(String Id, String Description)
    {
        SQLHelper Help = new SQLHelper(this.context);
        SQLiteDatabase db = Help.getWritableDatabase();

        ContentValues Registry = new ContentValues();
        Registry.put("id", Id);
        Registry.put("status", 1);
        Registry.put("description", Description);

        db.insert("Codigos", null, Registry);
        System.out.println("Codigo(id:  " + Id + ", status: 1, description: " + Description + ") insertado.");

        db.close();

    }

    public Cursor SelectAll()
    {
        SQLHelper Help = new SQLHelper(this.context);
        SQLiteDatabase db = Help.getWritableDatabase();

        Cursor cr = db.query("Codigos", null, null, null, null, null, null);
        cr.moveToFirst();

        return cr;
    }

}

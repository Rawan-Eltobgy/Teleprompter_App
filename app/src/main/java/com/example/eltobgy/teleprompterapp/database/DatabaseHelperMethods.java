package com.example.eltobgy.teleprompterapp.database;

/**
 * Created by Eltobgy on 11-Jul-18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.eltobgy.teleprompterapp.models.TeleprompterFile;

import java.util.ArrayList;

public class DatabaseHelperMethods extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Files.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FilesContract.FilesEntry.TABLE_NAME + " (" +
                    FilesContract.FilesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    FilesContract.FilesEntry.COLUMN_NAME_TITLE + " TEXT," +
                    FilesContract.FilesEntry.COLUMN_NAME_DESCRIPTION + " TEXT)";


    public DatabaseHelperMethods(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertNotes(ArrayList<TeleprompterFile> notes) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Reset table before the insert operations
        db.delete(FilesContract.FilesEntry.TABLE_NAME, null, null);

        for (int i = 0; i < notes.size(); i++) {
            TeleprompterFile noteObj = notes.get(i);
            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(FilesContract.FilesEntry.COLUMN_NAME_TITLE, noteObj.getTitle());
            values.put(FilesContract.FilesEntry.COLUMN_NAME_DESCRIPTION, noteObj.getDescription());


            // Insert the new row, returning the primary key value of the new row
            db.insert(FilesContract.FilesEntry.TABLE_NAME, null, values);
        }
        db.close();
    }

    public ArrayList<TeleprompterFile> readFiles() {
        SQLiteDatabase db = getReadableDatabase();

        // * is for every column , is for every row.
        String DISPLAY_QUERY = "SELECT * FROM " + FilesContract.FilesEntry.TABLE_NAME + " WHERE 1";

        Cursor cursor = db.rawQuery(DISPLAY_QUERY, null);

        ArrayList<TeleprompterFile> files = new ArrayList<>();
        while (cursor.moveToNext()) {
            TeleprompterFile filesObj = new TeleprompterFile();
            filesObj.setTitle(cursor.getString(1));
            filesObj.setDescription(cursor.getString(2));

            files.add(filesObj);
        }
        cursor.close();
        db.close();
        return files;
    }

}


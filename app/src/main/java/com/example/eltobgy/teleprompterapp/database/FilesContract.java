package com.example.eltobgy.teleprompterapp.database;
import android.provider.BaseColumns;
/**
 * Created by Eltobgy on 11-Jul-18.
 */

public class FilesContract {
    public static class FilesEntry implements BaseColumns {

            // Define database schema
            public static final String TABLE_NAME = "Files";
            public static final String COLUMN_NAME_TITLE = "title";
            public static final String COLUMN_NAME_DESCRIPTION = "description";

        }

}

package uk.ac.aston.cs3mdd.mobiledesignproject.ui.TestModule;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.Module;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "module_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_MODULES = "modules";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_MODULES + "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MODULES);
        onCreate(db);
    }



    // Implement methods for CRUD operations (insert, update, delete, retrieve)
}

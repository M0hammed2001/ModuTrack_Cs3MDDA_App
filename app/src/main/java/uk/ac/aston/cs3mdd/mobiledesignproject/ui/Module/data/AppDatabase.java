package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Module.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ModuleDao moduleDao();

    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "module-database")
                    .build();
        }
        return instance;
    }
}

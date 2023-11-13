package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import android.content.Context;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomDatabase;
import androidx.room.Database;
import androidx.room.Room;

import java.util.List;

@Entity
public class Module {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "Module_Name")
    public String ModuleName;

    @ColumnInfo(name = "Module_Code")
    public String ModuleCode;
}

@Database(entities = {Module.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ModuleDao moduleDao();

    // Example: Pass context in constructor
    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class, "database-name").build();
    }
}

    // In your activity or wherever you are using the database
    AppDatabase db = AppDatabase.getInstance(getApplicationContext());
    ModuleDao moduleDao = db.moduleDao();
    List<Module> modules = moduleDao.getAll();
}
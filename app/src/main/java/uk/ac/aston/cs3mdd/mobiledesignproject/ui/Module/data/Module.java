package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.RoomDatabase;

public class Module {

    @Entity
    public class module {
        @PrimaryKey
        public int uid;

        @ColumnInfo(name = "Module_Name")
        public String ModuleName;

        @ColumnInfo(name = "Module_Code")
        public String ModuleCode;

    }

    @androidx.room.Database(entities = {Module.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {
        public abstract Database.ModuleDao moduleDao();
    }

}

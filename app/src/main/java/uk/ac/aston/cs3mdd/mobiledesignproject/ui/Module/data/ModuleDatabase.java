package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Module.class}, version = 1)
public abstract class ModuleDatabase extends RoomDatabase {

    public abstract ModuleDao getModuleDAO();

}

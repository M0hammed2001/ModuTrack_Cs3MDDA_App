package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomDatabase;

import java.util.List;

public class DataSource {
    private ModuleDao moduleDao;

    public DataSource(ModuleDao moduleDao) {
        this.moduleDao = moduleDao;
    }

    public void insertModule(Module module) {
        // Perform insert operation using moduleDao
        moduleDao.insert(module);
    }

    public void updateModule(Module module) {
        // Perform update operation using moduleDao
        moduleDao.update(module);
    }

    public void deleteModule(Module module) {
        // Perform delete operation using moduleDao
        moduleDao.delete(module);
    }

    public List<Module> getAllModules() {
        // Perform query operation using moduleDao
        return moduleDao.getAllModules();
    }
}

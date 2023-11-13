package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;

import java.util.List;

@Dao
public interface ModuleDao {
    @Insert
    void insert(Module module);

    @Update
    void update(Module module);

    @Delete
    void delete(Module module);

    @Query("SELECT * FROM modules")
    List<Module> getAllModules();
}

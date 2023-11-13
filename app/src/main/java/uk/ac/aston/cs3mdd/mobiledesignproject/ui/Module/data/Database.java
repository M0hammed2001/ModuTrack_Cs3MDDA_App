package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomDatabase;

import java.util.List;

public class Database {

    @Dao
    public interface ModuleDao {
        @Query("SELECT * FROM module")
        List<Module> getAll();

        @Query("SELECT * FROM module WHERE uid IN (:userIds)")
        List<Module> loadAllByIds(int[] userIds);

        @Query("SELECT * FROM module WHERE Module_Code LIKE :first AND " +
                "Module_Name LIKE :last LIMIT 1")
        Module findByName(String first, String last);

        @Insert
        void insertAll(Module... modules);

        @Delete
        void delete(Module module);
    }




}

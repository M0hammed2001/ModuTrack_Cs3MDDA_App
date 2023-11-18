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
        public void addModule(Module module);

        @Update
        public void updateModule(Module module);

        @Delete
        public void deleteModule(Module module);

        @Query("select * from modules")
        public List<Module> getAllModules();

        @Query("select * from modules where Module_id==:Module_id")
        public Module getModule(int Module_id);



//        @Query("delete from modules where Module_id==:Module_id")
//        public List<Module> getAllModulesdeleted();





    }


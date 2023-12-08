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

        @Update
        public void editModule(Module module);

        @Delete
        public void deleteModule(Module module);

        @Query("select * from modules")
        public List<Module> getAllModules();

        @Query("select * from modules where Module_id==:Module_id")
        public Module getModule(int Module_id);

        @Query("DELETE FROM modules WHERE Module_id = :Module_id")
        abstract void deleteByModuleId(long Module_id);

        @Query("select * from modules where moduleName = :moduleName or moduleCode = :moduleCode")
        public Module getModuleFilter(long moduleName, long moduleCode);


        @Insert
        void InsertAll(Module Module);

        @Delete
        void DeleteAll(Module Module);



    }



package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import java.util.ArrayList;
import java.util.List;

public class ModuleList {
    List<Module> modules;

    public ModuleList() {
        modules = new ArrayList<>();
    }

    public void add(Module module){
        modules.add(module);
    }

    public boolean delete(Module module){
        for(Module m:modules){
            if(module.equals(m)){
                this.modules.remove(module);
                return true;
            }
        }
        return false;
    }
    public void update(Module newModule) {
        int i=0;
        for(Module m:modules){
            if(m.equals(newModule)){
                Module mUpdated = m;
                mUpdated.setModuleName(newModule.getModuleName());

                mUpdated.setAssignmentsName(newModule.getAssignmentsName());
                mUpdated.setAssignmentsDate(newModule.getAssignmentsDate());

                mUpdated.setExamName(newModule.getExamName());
                mUpdated.setExamDate(newModule.getExamDate());

                mUpdated.setLessonDateTime(newModule.getLessonDateTime());
                mUpdated.setLessonRoom(newModule.getLessonRoom());
                this.modules.set(i,mUpdated);
                return;
            }
            i++;
        }
    }

    public List<Module> getModules() {
        return modules;
    }



}

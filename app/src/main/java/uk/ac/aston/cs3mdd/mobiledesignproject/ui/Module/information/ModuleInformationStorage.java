package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.information;

import java.util.ArrayList;
import java.util.List;

public class ModuleInformationStorage {
    private List<ModuleInformation> moduleList;

    public ModuleInformationStorage() {
        moduleList = new ArrayList<>();
    }

    public void addModule(ModuleInformation module) {
        moduleList.add(module);
    }

    public List<ModuleInformation> getAllModules() {
        return moduleList;
    }

    public ModuleInformation generatemodules() {
        ModuleInformationStorage storage = new ModuleInformationStorage();

        ModuleInformation module1 = new ModuleInformation();
        module1.setCourseCode("CS3");
        module1.setModuleCode("CS3MDD");
        module1.setModuleName("Mobile Design & Development");
        module1.setCredits(15);
        module1.setDescription("This module considers the design and development of mobile, Android applications. Its focus is split between the interaction design aspects of app creation and the technical programming of Android apps.  It brings together state of the art research in mobile Human Computer Interaction with practical development skills, encouraging skills development in both front and back-end app creation.");
        module1.setExam("YES");
        module1.setCoursework("YES");
        module1.setExamDate("21st January 2023");
        module1.setCourseworkDate("31st December 2023");

        ModuleInformation module2 = new ModuleInformation();
        module2.setCourseCode("CS3");
        module2.setModuleCode("CS3AI");
        module2.setModuleName("Artificial Intelligence");
        module2.setCredits(15);
        module2.setDescription("This module considers the design and development of mobile, Android applications. Its focus is split between the interaction design aspects of app creation and the technical programming of Android apps.  It brings together state of the art research in mobile Human Computer Interaction with practical development skills, encouraging skills development in both front and back-end app creation.");
        module2.setExam("YES");
        module2.setCoursework("YES");
        module2.setExamDate("21st January 2023");
        module2.setCourseworkDate("31st December 2023");

        ModuleInformation module3 = new ModuleInformation();
        module3.setCourseCode("CS3");
        module3.setModuleCode("CS3ID");
        module3.setModuleName("Individual Project");
        module3.setCredits(15);
        module3.setDescription("This module considers the design and development of mobile, Android applications. Its focus is split between the interaction design aspects of app creation and the technical programming of Android apps.  It brings together state of the art research in mobile Human Computer Interaction with practical development skills, encouraging skills development in both front and back-end app creation.");
        module3.setExam("No");
        module3.setCoursework("YES");
        module3.setExamDate("21st January 2023");
        module3.setCourseworkDate("31st December 2023");

        storage.addModule(module1);
        storage.addModule(module2);

        List<ModuleInformation> allModules = storage.getAllModules();

        for (ModuleInformation module : allModules) {
            // Do something with each module, e.g., print its information
            System.out.println("Module Name: " + module.getModuleName());
            // Print other properties as needed
        }

        return null;  // You might want to return something meaningful here
    }

    public ModuleInformation getToyModules() {
        ModuleInformation other = new ModuleInformation();
        other.setModuleName("Module " + other.getModuleName());
        other.setCredits(15);
        other.setDescription("Description of " + other.getDescription());
        //other.setLearningOutcomes("Learning outcomes for " + other.getTitle() + "\nL01 Learn about something");
        return other;


    }

    public ModuleInformation getmoduleinformation(){


    }
}
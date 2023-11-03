package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.information;

import android.content.Context;

public class DataSource {
    private static DataSource _INSTANCE;
    private Context context;

    private DataSource(Context context) {
        this.context = context;
    }

    public static DataSource getInstance(Context context) {
        if (_INSTANCE == null) {
            _INSTANCE = new DataSource(context);
        }
        return _INSTANCE;
    }

    public ModuleInformation getRealModule() {
        ModuleInformation moduleinformation = new ModuleInformation();
        moduleinformation.setCourseCode("CS3");
        moduleinformation.setModuleCode("CS3MDD");
        moduleinformation.setModuleName("Mobile Design & Development");
        moduleinformation.setCredits(15);
        moduleinformation.setDescription("This module considers the design and development of mobile, Android applications. Its focus is split between the interaction design aspects of app creation and the technical programming of Android apps.  It brings together state of the art research in mobile Human Computer Interaction with practical development skills, encouraging skills development in both front and back-end app creation.");
//        moduleinformation.set("LO1: apply an informed understanding of good mobile design and related concepts to the design of real-world mobile applications that support effective mobile interaction;\n" +
//                "LO2: critically analyse academic research publications related to mobile interaction design for the purpose of advanced learning and innovations;\n" +
//                "LO3: apply practical Android development skills, using appropriate development approaches, to develop real-world mobile applications; and\n" +
//                "LO4: deploy strategies that lead to ongoing personal skill development, including those that relate to adaptability, critical thinking/analysis, self-motivation, self-directed knowledge expansion, effective interpersonal communication, and written communication and verbal communication skills.");
        return moduleinformation;
    }

    public ModuleInformation getToyModule() {
        ModuleInformation other = new ModuleInformation();
        other.setModuleName("Module " + other.getModuleName());
        other.setCredits(15);
        other.setDescription("Description of " + other.getDescription());
//        other.setLearningOutcomes("Learning outcomes for " +
//                other.getTitle() + "\nL01 Learn about something");
        return other;
    }
}

package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.information;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

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


//        ModuleInformation moduleinformation = new ModuleInformation();
//        moduleinformation.setCourseCode("CS3");
//        moduleinformation.setModuleCode("CS3MDD");
//        moduleinformation.setModuleName("Mobile Design & Development");
//        moduleinformation.setCredits(15);
//        moduleinformation.setDescription("This module considers the design and development of mobile, Android applications. Its focus is split between the interaction design aspects of app creation and the technical programming of Android apps.  It brings together state of the art research in mobile Human Computer Interaction with practical development skills, encouraging skills development in both front and back-end app creation.");
//        moduleinformation.setExam("YES");
//        moduleinformation.setCoursework("YES");
//        moduleinformation.setExamDate("21st January 2023");
//        moduleinformation.setCourseworkDate("31st December 2023");

        return moduleinformation;
    }



}

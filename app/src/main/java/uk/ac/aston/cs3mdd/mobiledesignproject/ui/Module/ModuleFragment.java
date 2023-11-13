package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDatabase;

public class ModuleFragment extends Fragment {
    EditText ModuleNameEdit;
    EditText ModuleCodeEdit;

    EditText assignmentNameEdit;
    EditText assignmentdueEdit;
    EditText assignmentDateEdit;

    EditText ExamNameEdit;
    EditText ExamdueEdit;
    EditText ExamDateedit;

    Button SaveButton, getDataButton;

    ModuleDatabase moduleDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_module, container, false);

        ModuleNameEdit = view.findViewById(R.id.ModuleNameEdit);
        ModuleCodeEdit = view.findViewById(R.id.ModuleCodeEdit);

        SaveButton = view.findViewById(R.id.SaveButton);
        getDataButton = view.findViewById(R.id.getDataButton);

        assignmentNameEdit = view.findViewById(R.id.assignmentNameEdit);
        assignmentdueEdit = view.findViewById(R.id.assignmentdueEdit);
        assignmentDateEdit = view.findViewById(R.id.assignmentDateEdit);

        ExamNameEdit = view.findViewById(R.id.ExamNameEdit);
        ExamdueEdit = view.findViewById(R.id.ExamdueEdit);
        ExamDateedit = view.findViewById(R.id.ExamDateedit);

        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }

            @Override
            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
                super.onDestructiveMigration(db);
            }

            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        moduleDB = Room.databaseBuilder(requireContext(), ModuleDatabase.class, "moduleDB").addCallback(myCallBack).build();

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String moduleName = ModuleNameEdit.getText().toString();
                String moduleCode = ModuleCodeEdit.getText().toString();

                String assignmentName = assignmentNameEdit.getText().toString();
                String assignmentdue = assignmentdueEdit.getText().toString();
                String assignmentDate = assignmentDateEdit.getText().toString();

                String examName = ExamNameEdit.getText().toString();
                String examdue = ExamdueEdit.getText().toString();
                String examdate = ExamDateedit.getText().toString();

                // You can use ModuleName and ModuleCode as needed

                // Create a Module object and save it to the database
                Module module1 = new Module(moduleName, moduleCode, assignmentName, assignmentdue, assignmentDate,  examName, examdue, examdate);
                moduleDB.getModuleDAO().addModule(module1);
            }
        });

        return view;
    }
}




//package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//
//import uk.ac.aston.cs3mdd.mobiledesignproject.R;
//import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
//import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDatabase;
//
//public class ModuleFragment extends Fragment {
//    EditText ModuleNameEdit;
//    EditText ModuleCodeEdit;
//
//    EditText assignmentNameEdit;
//    EditText assignmentdueEdit;
//    EditText assignmentDateEdit;
//
//    EditText ExamNameEdit;
//    EditText ExamdueEdit;
//    EditText ExamDateedit;
//
//
//    Button SaveButton, getDataButton;
//
//    ModuleDatabase moduleDB;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_module, container, false);
//
//        ModuleNameEdit = view.findViewById(R.id.ModuleNameEdit);
//        ModuleCodeEdit = view.findViewById(R.id.ModuleCodeEdit);
//
//        SaveButton = view.findViewById(R.id.SaveButton);
//        getDataButton = view.findViewById(R.id.getDataButton);
//
//        assignmentNameEdit = view.findViewById(R.id.assignmentNameEdit);
//        assignmentdueEdit = view.findViewById(R.id.assignmentdueEdit);
//        assignmentDateEdit = view.findViewById(R.id.assignmentDateEdit);
//
//        ExamNameEdit = view.findViewById(R.id.ExamNameEdit);
//        ExamdueEdit = view.findViewById(R.id.ExamdueEdit);
//        ExamDateedit = view.findViewById(R.id.ExamDateedit);
//
//        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
//            @Override
//            public void onCreate(@NonNull SupportSQLiteDatabase db) {
//                super.onCreate(db);
//            }
//
//            @Override
//            public void onDestructiveMigration(@NonNull SupportSQLiteDatabase db) {
//                super.onDestructiveMigration(db);
//            }
//
//            @Override
//            public void onOpen(@NonNull SupportSQLiteDatabase db) {
//                super.onOpen(db);
//            }
//        };
//
//        moduleDB = Room.databaseBuilder(getApplicationContext(), ModuleDatabase.class,"moduleDB").addCallback(myCallBack);
//
//        SaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String ModuleName = ModuleNameEdit.getText().toString();
//                String ModuleCode = ModuleCodeEdit.getText().toString();
//
//                String assignmentName = assignmentNameEdit.getText().toString();
//                String assignmentDue = assignmentdueEdit.getText().toString();
//                String assignmentDate = assignmentDateEdit.getText().toString();
//
//                String ExamName = ExamNameEdit.getText().toString();
//                String ExamDue =  ExamdueEdit.getText().toString();
//                String ExamDate = ExamDateedit.getText().toString();
//
//
//                // You can use ModuleName and ModuleCode as needed
//            }
//        });
//
//        return view;
//    }
//}

package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.managedata;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentTrainmapBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.PopupAddModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.PopupEditModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleFragment;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleViewModel;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDatabase;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.Destination;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.Trainmap.TrainMapFragment;


public class ModuleEditFragment extends Fragment {

    EditText popupModuleEditName;
    EditText popupModuleEditCode;

    EditText popupAssignmentEditDate;
    EditText popupAssignmentEditName;

    EditText popupExamEditDate;
    EditText popupExamEditName;

    EditText LectureEditRoom;
    EditText TutorialEditRoom;

    Button buttonChangeModule, ButttonEditBack;

    ModuleDatabase moduleDB;
    List<Module> moduleList;
    ModuleViewModel moduleViewModel;

    private PopupEditModuleBinding binding;
    private Module module;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String moduleCode = module.getModuleCode();
        String moduleName = module.getModuleName();

        String AssignmentName = module.getAssignmentName();
        String AssignmentDate = module.getAssignmentDate();

        String ExamName = module.getExamName();
        String ExamDate = module.getExamdate();

        String lectureRoom = module.getLectureRoom();
        String tutorialRoom = module.getTutorialRoom();

        // Handle null values
        moduleCode = (moduleCode != null) ? moduleCode : "Module Code";
        moduleName = (moduleName != null) ? moduleName : "Module Name";

//        ExamName = (ExamName != null) ? ExamName : "Exam Name";
//        ExamDate = (ExamDate != null) ? ExamDate : "Exam Date";
//
//        AssignmentName = (AssignmentName != null) ? AssignmentName : "Assignment Name";
//        AssignmentDate = (AssignmentDate != null) ? AssignmentDate : "Assignment Date";

        lectureRoom = (lectureRoom != null) ? lectureRoom : "Room details Unavailable";
        tutorialRoom = (tutorialRoom != null) ? tutorialRoom : "Room details Unavailable";


        String ModuleCodeText = moduleCode;
        String ModuleNameText = moduleName;

        String AssignmentDateText =AssignmentDate;
        String AssignmentNameText = AssignmentName;

        String ExamDateText =ExamDate;
        String ExamNameText = ExamName;

        String LectureRoomText =lectureRoom;
        String TutorialRoomText = tutorialRoom;


        binding.ButttonEditBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModuleEditFragment.this).navigate(R.id.action_module_to_moduleEdit);
            }
        });
        binding.ButttonEditBack.setText(module.toString());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.popup_edit_module, container, false);

        module = ModuleEditFragmentArgs.fromBundle(getArguments()).getCurrentModules();

        binding = PopupEditModuleBinding.inflate(inflater, container, false);


        popupModuleEditName = view.findViewById(R.id.popupModuleEditName);
        popupModuleEditCode = view.findViewById(R.id.popupModuleEditCode);

        buttonChangeModule = view.findViewById(R.id.buttonChangeModule);
        ButttonEditBack = view.findViewById(R.id.ButttonEditBack);

        popupAssignmentEditDate = view.findViewById(R.id.popupAssignmentEditDate);
        popupAssignmentEditName = view.findViewById(R.id.popupAssignmentEditName);

        popupExamEditDate = view.findViewById(R.id.popupExamEditDate);
        popupExamEditName = view.findViewById(R.id.popupExamEditName);

        LectureEditRoom = view.findViewById(R.id.LectureEditRoom);
        TutorialEditRoom = view.findViewById(R.id.TutorialEditRoom);




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
        moduleViewModel = new ViewModelProvider(this).get(ModuleViewModel.class);

        final Observer<List<Module>> moduleObserver = new Observer<List<Module>>() {
            @Override
            public void onChanged(List<Module> modules) {
                Log.i("TAG", "printing Module number " + modules.size());

            }
        };
        moduleViewModel.getAllModules().observe(getViewLifecycleOwner(), moduleObserver);
        buttonChangeModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String moduleName = popupModuleEditName.getText().toString();
//                String moduleCode = popupModuleEditCode.getText().toString();
//
//                String assignmentDate = popupAssignmentEditDate.getText().toString();
//                String assignmentdue = popupAssignmentEditdue.getText().toString();
//                String assignmentName = popupAssignmentEditName.getText().toString();
//
//                String examdate = popupExamEditDate.getText().toString();
//                String examdue = popupExamEditdue.getText().toString();
//                String examName = popupExamEditName.getText().toString();

                // You can use ModuleName and ModuleCode as needed

                editModuleInBackground(module);


            }
        });

        buttonChangeModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return view;

    }
    public void editModuleInBackground(Module module){

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //background task
                moduleDB.getModuleDAO().updateModule(module);
                //on finish task
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "Module amended", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
    }


}

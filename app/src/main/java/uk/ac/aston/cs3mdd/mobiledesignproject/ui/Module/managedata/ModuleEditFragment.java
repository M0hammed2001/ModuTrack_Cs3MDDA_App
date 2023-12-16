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
import androidx.room.Update;
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

    EditText ModuleNameText, ModuleCodeText, AssignmentDate1Text, AssignmentName1Text, AssignmentDate2Text, AssignmentName2Text, ExamDateText, ExamNameText, LectureRoomText, TutorialRoomText;

    Button buttonChangeModule, ButttonEditCancel;

    ModuleDatabase moduleDB;
    List<Module> moduleList;
    ModuleViewModel moduleViewModel;

    private PopupEditModuleBinding binding;
    private Module module;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // Initialize module properly from arguments or other sources
        module = ModuleEditFragmentArgs.fromBundle(getArguments()).getCurrentModules();
        if (module == null) {
            // Handle the case where module is null
            return;
        }
        binding.ButttonEditCancel.setText(module.toString());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.popup_edit_module, container, false);

        module = ModuleEditFragmentArgs.fromBundle(getArguments()).getCurrentModules();

        binding = PopupEditModuleBinding.inflate(inflater, container, false);


        ModuleCodeText = view.findViewById(R.id.ModuleCodeText);
        ModuleNameText = view.findViewById(R.id.ModuleNameText);

        AssignmentDate1Text = view.findViewById(R.id.AssignmentDate1Text);
        AssignmentName1Text = view.findViewById(R.id.AssignmentName1Text);

        AssignmentDate2Text = view.findViewById(R.id.AssignmentDate2Text);
        AssignmentName2Text = view.findViewById(R.id.AssignmentName2Text);


        ExamDateText = view.findViewById(R.id.ExamDateText);
        ExamNameText = view.findViewById(R.id.ExamNameText);

        LectureRoomText = view.findViewById(R.id.LectureRoomText);
        TutorialRoomText = view.findViewById(R.id.TutorialRoomText);

        buttonChangeModule = view.findViewById(R.id.buttonChangeModule);
        ButttonEditCancel = view.findViewById(R.id.ButttonEditCancel);

        ModuleCodeText.setText(module.getModuleCode());
        ModuleNameText.setText(module.getModuleName());

        AssignmentDate1Text.setText(module.getAssignmentDate1());
        AssignmentName1Text.setText(module.getAssignmentName1());

        AssignmentDate2Text.setText(module.getAssignmentDate2());
        AssignmentName2Text.setText(module.getAssignmentName2());

        ExamDateText.setText(module.getExamdate());
        ExamNameText.setText(module.getExamName());

        LectureRoomText.setText(module.getLectureRoom());
        TutorialRoomText.setText(module.getTutorialRoom());




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

                // will set once the button is clicked it will save the data to be set to what ever the data is set.
                module.setModuleCode(ModuleCodeText.getText().toString());
                module.setModuleName(ModuleNameText.getText().toString());

                module.setAssignmentDate1(AssignmentDate1Text.getText().toString());
                module.setAssignmentName1(AssignmentName1Text.getText().toString());

                module.setAssignmentDate2(AssignmentDate2Text.getText().toString());
                module.setAssignmentName2(AssignmentName2Text.getText().toString());

                module.setExamdate(ExamDateText.getText().toString());
                module.setExamName(ExamNameText.getText().toString());

                module.setTutorialRoom(LectureRoomText.getText().toString());
                module.setLectureRoom(TutorialRoomText.getText().toString());


                EditModuleInBackground(module);

                Log.i("MS","changing the module");
                // after it edited the module it will send you back to the main Screen
                NavHostFragment.findNavController(ModuleEditFragment.this).navigate(R.id.action_nav_moduleEdit_to_nav_module);


            }
        });

        ButttonEditCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModuleEditFragment.this).navigate(R.id.action_nav_moduleEdit_to_nav_module);

                Log.i("MS","Canceling Edit");
            }
        });

        return view;

    }

    public void EditModuleInBackground(Module model) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {

            @Override
            public void run() {
                    //background task
                    moduleDB.getModuleDAO().editModule(module);

                    //on finish task
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            Toast.makeText(getContext(), "updated in DataBase", Toast.LENGTH_LONG).show();
                        }
                    });

            }
        });
    }


}

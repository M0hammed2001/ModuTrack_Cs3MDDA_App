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

    EditText popupModuleEditName, popupModuleEditCode, popupAssignmentEditDate,  popupAssignmentEditName, popupExamEditDate,  popupExamEditName, LectureEditRoom, TutorialEditRoom;

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


        popupModuleEditName = view.findViewById(R.id.popupModuleEditName);
        popupModuleEditCode = view.findViewById(R.id.popupModuleEditCode);

        buttonChangeModule = view.findViewById(R.id.buttonChangeModule);
        ButttonEditCancel = view.findViewById(R.id.ButttonEditCancel);

        popupAssignmentEditDate = view.findViewById(R.id.popupAssignmentEditDate);
        popupAssignmentEditName = view.findViewById(R.id.popupAssignmentEditName);

        popupExamEditDate = view.findViewById(R.id.popupExamEditDate);
        popupExamEditName = view.findViewById(R.id.popupExamEditName);

        LectureEditRoom = view.findViewById(R.id.LectureEditRoom);
        TutorialEditRoom = view.findViewById(R.id.TutorialEditRoom);

        popupModuleEditName.setText(module.getModuleName());
        popupModuleEditCode.setText(module.getModuleCode());

        popupAssignmentEditDate.setText(module.getAssignmentDate1());
        popupAssignmentEditName.setText(module.getAssignmentName1());

        popupExamEditDate.setText(module.getExamdate());
        popupExamEditName.setText(module.getExamName());

        LectureEditRoom.setText(module.getLectureRoom());
        TutorialEditRoom.setText(module.getTutorialRoom());




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
                module.setModuleCode(popupModuleEditCode.getText().toString());
                module.setModuleName(popupModuleEditName.getText().toString());

                module.setAssignmentDate1(popupAssignmentEditDate.getText().toString());
                module.setAssignmentName1(popupAssignmentEditName.getText().toString());

                module.setExamdate(popupExamEditDate.getText().toString());
                module.setExamName(popupExamEditName.getText().toString());

                module.setTutorialRoom(TutorialEditRoom.getText().toString());
                module.setLectureRoom(LectureEditRoom.getText().toString());


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

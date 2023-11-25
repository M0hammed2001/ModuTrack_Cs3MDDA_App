package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.managedata;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.Trainmap.TrainMapFragment;


public class ModuleEditFragment extends ModuleFragment {

    EditText popupModuleEditName;
    EditText popupModuleEditCode;

    EditText popupAssignmentEditDate;
    EditText popupAssignmentEditdue;
    EditText popupAssignmentEditName;

    EditText popupExamEditDate;
    EditText popupExamEditdue;
    EditText popupExamEditName;

    Button buttonChangeModule, ButttonEditBack;

    ModuleDatabase moduleDB;
    List<Module> moduleList;
    ModuleViewModel moduleViewModel;

    private PopupEditModuleBinding binding;
    private Module module;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.ButttonEditBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ModuleEditFragment.this).navigate(R.id.action_moduleEdit_to_module);
            }
        });
        binding.ButttonEditBack.setText(module.toString());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.popup_edit_module, container, false);

        module = ModuleEditFragmentArgs.fromBundle(getArguments()).getModule();

        binding = PopupEditModuleBinding.inflate(inflater, container, false);


        popupModuleEditName = view.findViewById(R.id.popupModuleEditName);
        popupModuleEditCode = view.findViewById(R.id.popupModuleEditCode);

        buttonChangeModule = view.findViewById(R.id.buttonChangeModule);
        ButttonEditBack = view.findViewById(R.id.ButttonEditBack);

        popupAssignmentEditDate = view.findViewById(R.id.popupAssignmentEditDate);
        popupAssignmentEditdue = view.findViewById(R.id.popupAssignmentEditdue);
        popupAssignmentEditName = view.findViewById(R.id.popupAssignmentEditName);

        popupExamEditDate = view.findViewById(R.id.popupExamEditDate);
        popupExamEditdue = view.findViewById(R.id.popupExamEditdue);
        popupExamEditName = view.findViewById(R.id.popupExamEditName);


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
//        moduleViewModel = new ViewModelProvider(requireActivity()).get(ModuleViewModel.class);
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
                        Toast.makeText(getContext(), "Data Changed", Toast.LENGTH_SHORT).show();


                    }
                });
            }
        });
    }


}

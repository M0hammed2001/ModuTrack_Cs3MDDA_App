package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;


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
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.PopupEditModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDatabase;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleListAdapter;


public class ModuleFragment extends Fragment {

    ModuleDatabase moduleDB;
    List<Module> moduleList;

    public Module modules;
    ModuleViewModel moduleViewModel;

    FragmentModuleBinding binding;

    private RecyclerView ModuleRecyclerView;
    Button ButtonDeleteModule ;

    private ModuleViewModel viewModel;

    private ModuleListAdapter moduleAdapter;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.popup_edit_module, container, false);

        View ButtonView = inflater.inflate(R.layout.module_item, container, false);
        ButtonDeleteModule = ButtonView.findViewById(R.id.ButtonDeleteModule);



//        ButtonEdit = view.findViewById(R.id.ButtonEdit);

        // Initialize viewModel using ViewModelProvider
        viewModel = new ViewModelProvider(this).get(ModuleViewModel.class);

        binding = FragmentModuleBinding.inflate(inflater, container, false);

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

        getModuleListInBackground(moduleViewModel);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Get a handle to the RecyclerView.


        ModuleRecyclerView = view.findViewById(R.id.MFRecyclerView);


        // Create an adapter and supply the data to be displayed.
        moduleAdapter = new ModuleListAdapter(getContext(), viewModel.getAllModules().getValue());
        // Connect the adapter with the RecyclerView.
        ModuleRecyclerView.setAdapter(moduleAdapter);
        // Give the RecyclerView a default layout manager.
        ModuleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        final Observer<List<Module>> ModuleListObserver = new Observer<List<Module>>() {
            @Override
            public void onChanged(@Nullable final List<Module> ModuleList) {
                // Update your adapter with the filtered list
                moduleAdapter.updateData(ModuleList);
                Log.i("MS", "Modules:" + moduleList.size());
            }
        };

        binding.addDatabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ModuleFragment.this).navigate(R.id.action_module_to_moduleAdd);
                Log.i("MS", "ADDED");
            }
        });

        ButtonDeleteModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calling all fields
                String moduleName = modules.getModuleName();
                String moduleCode = modules.getModuleCode();

                String assignmentDate = modules.getAssignmentDate();
                String assignmentDue = modules.getAssignmentdue();
                String assignmentName = modules.getAssignmentName();

                String examDate = modules.getExamdate();
                String examDue = modules.getExamdue();
                String examName = modules.getExamName();

//                    String ModuleID = modules.getId();

                // Deletes the Module from the database using the delete background task
                Module DeleteModule = new Module(moduleName, moduleCode, assignmentName, assignmentDue, assignmentDate, examName, examDate, examDue);
                DeleteModuleInBackground(DeleteModule);

                Log.i("MS", "Deleted");
            }
        });




        moduleViewModel.getAllModules().observe(getViewLifecycleOwner(), ModuleListObserver);


    }

    public void getModuleListInBackground(ModuleViewModel model) {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //background task

                moduleList = moduleDB.getModuleDAO().getAllModules();

                //on finish task
                handler.post(new Runnable() {
                    @Override
                    public void run() {

//                        StringBuilder sb = new StringBuilder();
//                        for(Module m : moduleList){
//
//                            sb.append(m.getModuleCode()+" : "+m.getModuleName());
//                            sb.append(m.getExamName()+" : "+m.getExamdue()+" : "+m.getExamdate());
//                            sb.append("\n");
//                        }
//                        String finaldata = sb.toString();
//                        Toast.makeText(getContext(), "" + finaldata, Toast.LENGTH_LONG).show();


                        model.updateModule(moduleList);
////                         Create the text with line breaks

//                        Toast.makeText("ms", "Number of Modules" + moduleList.size());


                    }
                });
            }
        });
    }

    public void DeleteModuleInBackground(Module module) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // Check if moduleDB is not null before accessing getModuleDAO()
                if (moduleDB != null) {
                    // Background task
                    moduleDB.getModuleDAO().deleteBymoduleId(module.getId());

                    // On finish task
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ButtonDeleteModule.getContext(), "Deleted From DataBase", Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    // Log an error or handle the situation where moduleDB is null
                    Log.e("DeleteModuleInBackground", "ModuleDatabase is null");
                }
            }
        });

    }

}

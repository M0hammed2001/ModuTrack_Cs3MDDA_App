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
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDatabase;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleListAdapter;


public class ModuleFragment extends Fragment {
//    EditText ModuleNameEdit;
//    EditText ModuleCodeEdit;
//
//    EditText assignmentNameEdit;
//    EditText assignmentdueEdit;
//    EditText assignmentDateEdit;
//
//    EditText ExamNameEdit;
//    EditText ExamdueEdit;
//    EditText ExamDateEdit;

//    Button SaveButton, getDataButton;
    Button SaveButton, addDatabutton;

    ModuleDatabase moduleDB;
    List<Module> moduleList;
    ModuleViewModel moduleViewModel;

    FragmentModuleBinding binding;


    private RecyclerView ModuleRecyclerView;


//    private ModuleViewModel viewModel;
    private ModuleViewModel viewModel;

    private ModuleListAdapter moduleAdapter;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_module, container, false);

        // Initialize viewModel using ViewModelProvider
        viewModel = new ViewModelProvider(this).get(ModuleViewModel.class);

        binding = FragmentModuleBinding.inflate(inflater, container, false);

//        ModuleNameEdit = view.findViewById(R.id.ModuleNameEdit);
//        ModuleCodeEdit = view.findViewById(R.id.ModuleCodeEdit);

//        SaveButton = view.findViewById(R.id.SaveButton);
////       getDataButton = view.findViewById(R.id.getDataButton);

//        assignmentNameEdit = view.findViewById(R.id.assignmentNameEdit);
//        assignmentdueEdit = view.findViewById(R.id.assignmentdueEdit);
//        assignmentDateEdit = view.findViewById(R.id.assignmentDateEdit);
//
//        ExamNameEdit = view.findViewById(R.id.ExamNameEdit);
//        ExamdueEdit = view.findViewById(R.id.ExamdueEdit);
//        ExamDateEdit = view.findViewById(R.id.ExamDateEdit);



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
        getModuleListInBackground(moduleViewModel);

        return binding.getRoot();
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ModuleViewModel.class);

         //Get a handle to the RecyclerView.
        ModuleRecyclerView = binding.MFRecyclerView;
        // Create an adapter and supply the data to be displayed.
        moduleAdapter = new ModuleListAdapter(getContext(), viewModel.getAllModules().getValue());
        // Connect the adapter with the RecyclerView.
        ModuleRecyclerView.setAdapter(moduleAdapter);
        // Give the RecyclerView a default layout manager.
        ModuleRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewModel = new ViewModelProvider(requireActivity()).get(ModuleViewModel.class);


        final Observer<List<Module>> ModuleListObserver = new Observer<List<Module>>() {
            @Override
            public void onChanged(@Nullable final List<Module> ModuleList) {
                List<Module> filteredList = new ArrayList<>();
                // this if and for loop will make sure that if final Module is empty it wont display the item.
                if (ModuleList == null | ModuleList != null) {
                    for (Module moduleDisplay : ModuleList) {
                                filteredList.add(moduleDisplay);
                    }
                    Log.i("MS","Test Print");
                    // Update your adapter with the filtered list
                    moduleAdapter.updateData(filteredList);
                }
            }
        };
        binding.addDatabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ModuleFragment.this).navigate(R.id.action_module_to_moduleAdd);
                Log.i("TAG", "ADDED");
            }
        });

        viewModel.getAllModules().observe(getViewLifecycleOwner(), ModuleListObserver);
    }

    public void addModuleInBackground(Module module){

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //background task
                moduleDB.getModuleDAO().addModule(module);
                //on finish task
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        Toast.makeText(getContext(), "Added to Database", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }



    public void getModuleListInBackground(ModuleViewModel model){

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                //background task

                moduleList = moduleDB.getModuleDAO().getAllModules();
//                TrainService trainService = mTrainList.get(position);
//                holder.trainService = trainService;
                //on finish task
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        StringBuilder sb = new StringBuilder();
                        for(Module m : moduleList){

                            sb.append(m.getModuleCode()+" : "+m.getModuleName());
                            sb.append(m.getExamName()+" : "+m.getExamdue()+" : "+m.getExamdate());
                            sb.append("\n");
                        }
//                        String finaldata = sb.toString();
//                        Toast.makeText(getContext(), "" + finaldata, Toast.LENGTH_SHORT).show();
//                        model.updateModule(moduleList);
                        // Create the text with line breaks





                    }
                });
            }
        });
    }
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_module, container, false);
//
////        // Get a handle to the RecyclerView.
////        mRecyclerView = view.findViewById(R.id.MFrecyclerview);
////        // Create an adapter and supply the data to be displayed.
////        mAdapter = new TrainListAdapter(getContext(), viewModel.getAllTrains().getValue());
////        // Connect the adapter with the RecyclerView.
////        mRecyclerView.setAdapter(mAdapter);
////        // Give the RecyclerView a default layout manager.
////        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
//        ExamDateEdit = view.findViewById(R.id.ExamDateEdit);
//
//
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
//        moduleDB = Room.databaseBuilder(requireContext(), ModuleDatabase.class, "moduleDB").addCallback(myCallBack).build();
////        moduleViewModel = new ViewModelProvider(requireActivity()).get(ModuleViewModel.class);
//        moduleViewModel = new ViewModelProvider(this).get(ModuleViewModel.class);
//        getModuleListInBackground(moduleViewModel);
//        final Observer<List<Module>> moduleObserver = new Observer<List<Module>>() {
//
//            @Override
//            public void onChanged(List<Module> modules) {
//                Log.i("TAG", "printing Module number " + modules.size());
//
//            }
//        };
//        moduleViewModel.getAllModules().observe(getViewLifecycleOwner(), moduleObserver);
//        SaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String moduleName = ModuleNameEdit.getText().toString();
//                String moduleCode = ModuleCodeEdit.getText().toString();
//
//                String assignmentName = assignmentNameEdit.getText().toString();
//                String assignmentdue = assignmentdueEdit.getText().toString();
//                String assignmentDate = assignmentDateEdit.getText().toString();
//
//                String examName = ExamNameEdit.getText().toString();
//                String examdue = ExamdueEdit.getText().toString();
//                String examdate = ExamDateEdit.getText().toString();
//
//                // You can use ModuleName and ModuleCode as needed
//
//                // Create a Module object and save it to the database
//                Module module1 = new Module(moduleName, moduleCode, assignmentName, assignmentdue, assignmentDate,  examName, examdue, examdate);
//                addModuleInBackground(module1);
//            }
//        });
//
//        getDataButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//            }
//        });
//
//        return view;
//    }


//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        // Get a handle to the RecyclerView.
//        mRecyclerView = view.findViewById(R.id.MFrecyclerview);
//        // Create an adapter and supply the data to be displayed.
//        mAdapter = new ModuleListAdapter(getContext(), viewModel.getAllModules().getValue());
//        // Connect the adapter with the RecyclerView.
//        mRecyclerView.setAdapter(mAdapter);
//        // Give the RecyclerView a default layout manager.
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }

}

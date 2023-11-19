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
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.PopupAddModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleFragment;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleViewModel;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDatabase;


public class ModuleAddFragment extends Fragment {

    EditText popupModuleNameEdit;
    EditText popupModuleCodeEdit;

    EditText popupAssignmentDateEdit;
    EditText popupAssignmentdueEdit;
    EditText popupAssignmentNameEdit;

    EditText popupExamDateEdit;
    EditText popupExamdueEdit;
    EditText popupExamNameEdit;

    Button buttonAddModule, buttongoBack;

    ModuleDatabase moduleDB;
    List<Module> moduleList;
    ModuleViewModel moduleViewModel;


    private RecyclerView mRecyclerView;

    private PopupAddModuleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.popup_add_module, container, false);

//        // Get a handle to the RecyclerView.
//        mRecyclerView = view.findViewById(R.id.MFrecyclerview);
//        // Create an adapter and supply the data to be displayed.
//        mAdapter = new TrainListAdapter(getContext(), viewModel.getAllTrains().getValue());
//        // Connect the adapter with the RecyclerView.
//        mRecyclerView.setAdapter(mAdapter);
//        // Give the RecyclerView a default layout manager.
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        popupModuleNameEdit = view.findViewById(R.id.popupModuleNameEdit);
        popupModuleCodeEdit = view.findViewById(R.id.popupModuleCodeEdit);

        buttonAddModule = view.findViewById(R.id.buttonAddModule);


        popupAssignmentDateEdit = view.findViewById(R.id.popupAssignmentDateEdit);
        popupAssignmentdueEdit = view.findViewById(R.id.popupAssignmentdueEdit);
        popupAssignmentNameEdit = view.findViewById(R.id.popupAssignmentNameEdit);

        popupExamDateEdit = view.findViewById(R.id.popupExamDateEdit);
        popupExamdueEdit = view.findViewById(R.id.popupExamdueEdit);
        popupExamNameEdit = view.findViewById(R.id.popupExamNameEdit);


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
        buttonAddModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String moduleName = popupModuleNameEdit.getText().toString();
                String moduleCode = popupModuleCodeEdit.getText().toString();

                String assignmentDate = popupAssignmentDateEdit.getText().toString();
                String assignmentdue = popupAssignmentdueEdit.getText().toString();
                String assignmentName = popupAssignmentNameEdit.getText().toString();

                String examdate = popupExamDateEdit.getText().toString();
                String examdue = popupExamdueEdit.getText().toString();
                String examName = popupExamNameEdit.getText().toString();
//
//                // You can use ModuleName and ModuleCode as needed
//
//                // Create a Module object and save it to the database
                Module module1 = new Module(moduleName, moduleCode, assignmentName, assignmentdue, assignmentDate, examName, examdue, examdate);
                EditModuleInBackground(module1);
            }
        });

        buttongoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        return view;


//        public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//
//
//            binding = PopupAddModuleBinding.inflate(inflater, container, false);
//
//            return binding.getRoot();
//
//        }

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
////        final Observer<List<Module>> moduleObserver = new Observer<List<Module>>() {
////
////            @Override
////            public void onChanged(List<Module> modules) {
////                Log.i("TAG", "printing Module number " + modules.size());
////
////            }
////        };
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
//
//
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
//
//        @Override
//        public void onDestroyView() {
//            super.onDestroyView();
//            binding = null;
//        }
//    }
    }

    public void EditModuleInBackground(Module module) {

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
                        Toast.makeText(getContext(), "Added to Database", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}

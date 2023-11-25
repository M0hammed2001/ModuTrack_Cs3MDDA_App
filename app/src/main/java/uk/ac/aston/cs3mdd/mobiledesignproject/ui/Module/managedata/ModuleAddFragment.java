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
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.PopupAddModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleViewModel;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDatabase;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleListAdapter;


public class ModuleAddFragment extends Fragment {

    ModuleDatabase moduleDB;

    EditText popupModuleNameEdit;
    EditText popupModuleCodeEdit;

    EditText popupAssignmentDateEdit;
    EditText popupAssignmentdueEdit;
    EditText popupAssignmentNameEdit;

    EditText popupExamDateEdit;
    EditText popupExamdueEdit;
    EditText popupExamNameEdit;

    Button buttonAddModule, ButtonDeleteModule;

    private ModuleListAdapter moduleAdapter;

    List<Module> moduleList;
    ModuleViewModel moduleViewModel;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.popup_add_module, container, false);

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

                    // Creates a Module object and save it to the database
                    Module module = new Module(moduleName, moduleCode, assignmentName, assignmentdue, assignmentDate, examName, examdue, examdate);
                    AddModuleInBackground(module);
                }
            });

        return view;
    }


    public void AddModuleInBackground(Module module) {

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
                        Toast.makeText(getContext(), "Added to DataBase", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }


}

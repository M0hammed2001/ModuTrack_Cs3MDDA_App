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
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleViewModel;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDatabase;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleListAdapter;


public class ModuleAddFragment extends Fragment {

    ModuleDatabase moduleDB;

    EditText popupModuleNameEdit, popupModuleCodeEdit, popupAssignmentDateEdit,  popupAssignmentNameEdit, popupExamDateEdit, popupExamNameEdit, TutorialRoomEdit, LectureRoomEdit;

    Button buttonAddModule;



    ModuleViewModel moduleViewModel;




    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.popup_add_module, container, false);

        popupModuleNameEdit = view.findViewById(R.id.popupModuleNameEdit);
        popupModuleCodeEdit = view.findViewById(R.id.popupModuleCodeEdit);

        buttonAddModule = view.findViewById(R.id.buttonAddModule);

        popupAssignmentDateEdit = view.findViewById(R.id.popupAssignmentDateEdit);
        popupAssignmentNameEdit = view.findViewById(R.id.popupAssignmentNameEdit);

        popupExamDateEdit = view.findViewById(R.id.popupExamDateEdit);
        popupExamNameEdit = view.findViewById(R.id.popupExamNameEdit);

        TutorialRoomEdit = view.findViewById(R.id.TutorialRoomEdit);
        LectureRoomEdit = view.findViewById(R.id.LectureRoomEdit);

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

                    String assignmentDate1 = popupAssignmentDateEdit.getText().toString();
                    String assignmentName1 = popupAssignmentNameEdit.getText().toString();

                    String assignmentDate2 = popupAssignmentDateEdit.getText().toString();
                    String assignmentName2 = popupAssignmentNameEdit.getText().toString();

                    String ExamDate = popupExamDateEdit.getText().toString();
                    String ExamName = popupExamNameEdit.getText().toString();

                    String tutorialRoom = TutorialRoomEdit.getText().toString();
                    String lectureRoom = LectureRoomEdit.getText().toString();

                    // Creates a Module object and save it to the database
                    Module moduleAdd = new Module(moduleName, moduleCode, assignmentName1, assignmentDate1, assignmentName2, assignmentDate2, ExamName, ExamDate, tutorialRoom, lectureRoom);
                    AddModuleInBackground(moduleAdd);

                    NavHostFragment.findNavController(ModuleAddFragment.this).navigate(R.id.action_moduleadd_to_module);

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

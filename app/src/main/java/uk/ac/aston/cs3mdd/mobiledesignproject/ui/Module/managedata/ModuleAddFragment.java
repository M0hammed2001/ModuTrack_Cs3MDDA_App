package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.managedata;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleViewModel;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDatabase;



public class ModuleAddFragment extends Fragment {

    ModuleDatabase moduleDB;

    EditText ModuleNameText, ModuleCodeText, AssignmentName1Text, AssignmentName2Text, ExamNameText, LectureRoomText, TutorialRoomText;
    TextView AssignmentDate1Text, AssignmentDate2Text,ExamDateText;


    Button buttonAddModule;



    ModuleViewModel moduleViewModel;

    private void openDialog() {
        // Assuming you are in a Fragment, use requireActivity() as the context
        DatePickerDialog dialog = new DatePickerDialog(requireActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        if (ExamDateText.isPressed()){
                            ExamDateText.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));

                        } else if (AssignmentDate1Text.isPressed()) {
                            AssignmentDate1Text.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));


                        }else if (AssignmentDate2Text.isPressed()) {
                            AssignmentDate2Text.setText(String.valueOf(dayOfMonth)+"/"+String.valueOf(month)+"/"+String.valueOf(year));


                        }else {
                            Toast.makeText(getContext(),"failed to add Date", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                2023, 12, 29
        );

        dialog.show();
    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.popup_add_module, container, false);

        ModuleCodeText = view.findViewById(R.id.ModuleCodeText);
        ModuleNameText = view.findViewById(R.id.ModuleNameText);

        buttonAddModule = view.findViewById(R.id.buttonAddModule);

        AssignmentDate1Text = view.findViewById(R.id.AssignmentDate1Text);
        AssignmentName1Text = view.findViewById(R.id.AssignmentName1Text);

        AssignmentDate2Text = view.findViewById(R.id.AssignmentDate2Text);
        AssignmentName2Text = view.findViewById(R.id.AssignmentName2Text);


        ExamDateText = view.findViewById(R.id.ExamDateText);
        ExamNameText = view.findViewById(R.id.ExamNameText);

        LectureRoomText = view.findViewById(R.id.LectureRoomText);
        TutorialRoomText = view.findViewById(R.id.TutorialRoomText);

        ExamDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openDialog();

            }
        });

        AssignmentDate1Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });

        AssignmentDate2Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();

            }
        });



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
                    String moduleName = ModuleNameText.getText().toString();
                    String moduleCode = ModuleCodeText.getText().toString().trim().toUpperCase();

                    String assignmentDate1 = AssignmentDate1Text.getText().toString();
                    String assignmentName1 = AssignmentName1Text.getText().toString();

                    String assignmentDate2 = AssignmentDate2Text.getText().toString();
                    String assignmentName2 = AssignmentName2Text.getText().toString();

                    String ExamDate = ExamDateText.getText().toString();
                    String ExamName = ExamNameText.getText().toString();

                    String tutorialRoom = TutorialRoomText.getText().toString().trim().toUpperCase();
                    String lectureRoom = LectureRoomText.getText().toString().trim().toUpperCase();

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

package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.managedata;

import android.app.DatePickerDialog;
import android.graphics.Color;
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

    TextView AssignmentDate1Label , AssignmentDate2Label, ExamDateLabel;

    TextView AssignmentDate1Text, AssignmentDate2Text,ExamDateText;

    Button buttonAddModule;


    ModuleViewModel moduleViewModel;

    private void openDialog(final TextView targetTextView) {
        // Assuming you are in a Fragment, use requireActivity() as the context
        DatePickerDialog dialog = new DatePickerDialog(requireActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // did month +1 as it thinks january is 00
                        targetTextView.setText(String.valueOf(dayOfMonth) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));

                    }
                },
                2024, 00, 01
        );

        dialog.show();
    }

    private void OGThemeColour() {
        ExamNameText.setHintTextColor(Color.BLACK);
        ExamNameText.setTextColor(Color.BLACK);

        AssignmentName1Text.setHintTextColor(Color.BLACK);
        AssignmentName1Text.setTextColor(Color.BLACK);

        AssignmentName2Text.setHintTextColor(Color.BLACK);
        AssignmentName2Text.setTextColor(Color.BLACK);

        ModuleCodeText.setHintTextColor(Color.BLACK);
        ModuleCodeText.setTextColor(Color.BLACK);


        ModuleNameText.setHintTextColor(Color.BLACK);
        ModuleNameText.setTextColor(Color.BLACK);


        TutorialRoomText.setHintTextColor(Color.BLACK);
        TutorialRoomText.setTextColor(Color.BLACK);


        LectureRoomText.setHintTextColor(Color.BLACK);
        LectureRoomText.setTextColor(Color.BLACK);

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

        // this is the drawable Date icon
        AssignmentDate1Label = view.findViewById(R.id.AssignmentDate1Label);
        AssignmentDate2Label= view.findViewById(R.id.AssignmentDate2Label);
        ExamDateLabel= view.findViewById(R.id.ExamDateLabel);

        OGThemeColour();

        ExamDateLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opens the Date picker dialog with and adds date as a string to the field in brackets
                openDialog(ExamDateText);

            }
        });

        AssignmentDate1Label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opens the Date picker dialog with and adds date as a string to the field in brackets

                openDialog(AssignmentDate1Text);

            }
        });

        AssignmentDate2Label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opens the Date picker dialog with and adds date as a string to the field in brackets
                openDialog(AssignmentDate2Text);

            }
        });



        ExamDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openDialog(ExamDateText);

            }
        });

        AssignmentDate1Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(AssignmentDate1Text);

            }
        });

        AssignmentDate2Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(AssignmentDate2Text);

            }
        });

        ModuleCodeText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean HasFocus) {
                if (HasFocus) {
                    // Change the background color when the EditText has been clicked on
//                    ModuleCodeText.setBackgroundColor(Color.BLUE); // Changes the text and background colour
                    ModuleCodeText.setHintTextColor(Color.RED); // Changes the text and background colour
                    ModuleCodeText.setTextColor(Color.RED); // Changes the text and background colour

                } else {
                    OGThemeColour();
                }
            }
        });

        ModuleNameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean HasFocus) {
                if (HasFocus) {
                    // Change the background color when the EditText has been clicked on
//                    ModuleNameText.setBackgroundColor(Color.BLUE); // Changes the text and background colour
                    ModuleNameText.setHintTextColor(Color.RED);// Changes the text and background colour
                    ModuleNameText.setTextColor(Color.RED); // Changes the text and background colour
                } else {
                    OGThemeColour();
                }
            }
        });



        AssignmentName1Text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean HasFocus) {
                if (HasFocus) {
                    // Change the background color when the EditText has been clicked on
//                    AssignmentName1Text.setBackgroundColor(Color.BLUE); // Changes the text and background colour
                    AssignmentName1Text.setHintTextColor(Color.RED); // Changes the text and background colour
                    AssignmentName1Text.setTextColor(Color.RED); // Changes the text and background colour

                } else {
                    OGThemeColour();
                }
            }
        });

        AssignmentName2Text.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean HasFocus) {
                if (HasFocus) {
                    // Change the background color when the EditText has been clicked on
//                    AssignmentName2Text.setBackgroundColor(Color.BLUE); // Changes the text and background colour
                    AssignmentName2Text.setHintTextColor(Color.RED); // Changes the text and background colour
                    AssignmentName2Text.setTextColor(Color.RED); // Changes the text and background colour


                } else {
                    OGThemeColour();
                }
            }
        });
        ExamNameText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean HasFocus) {
                if (HasFocus) {
                    // Change the background color when the EditText has been clicked on
//                    ExamNameText.setBackgroundColor(Color.BLUE); // Changes the text and background colour
                    ExamNameText.setHintTextColor(Color.RED); // Changes the text and background colour
                    ExamNameText.setTextColor(Color.RED); // Changes the text and background colour


                } else {
                    OGThemeColour();
                }
            }
        });


        LectureRoomText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean HasFocus) {
                if (HasFocus) {
                    // Change the background color when the EditText has been clicked on
//                    LectureRoomText.setBackgroundColor(Color.BLUE); // Changes the text and background colour
                    LectureRoomText.setHintTextColor(Color.RED); // Changes the text and background colour
                    LectureRoomText.setTextColor(Color.RED); // Changes the text and background colour

                } else {
                    OGThemeColour();
                }
            }
        });

        TutorialRoomText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean HasFocus) {
                if (HasFocus) {
                    // Change the background color when the EditText has been clicked on
//                    TutorialRoomText.setBackgroundColor(Color.BLUE); // Changes the text and background colour
                    TutorialRoomText.setHintTextColor(Color.RED); // Changes the text and background colour
                    TutorialRoomText.setTextColor(Color.RED); // Changes the text and background colour

                } else {
                    OGThemeColour();
                }
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

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatDelegate;
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
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.PopupEditModuleBinding;

import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleViewModel;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDatabase;



public class ModuleEditFragment extends Fragment {

//    private int OGBackgroundColor, OGTextColor;

//    EditText ModuleNameText, ModuleCodeText, AssignmentDate1Text, AssignmentName1Text, AssignmentDate2Text, AssignmentName2Text, ExamDateText, ExamNameText, LectureRoomText, TutorialRoomText;
    EditText ModuleNameText, ModuleCodeText, AssignmentName1Text, AssignmentName2Text, ExamNameText, LectureRoomText, TutorialRoomText;
    TextView AssignmentDate1Text, AssignmentDate2Text,ExamDateText;

    TextView AssignmentDate1Label , AssignmentDate2Label, ExamDateLabel;
    Button buttonChangeModule, ButttonEditCancel;
    ImageView clearCalender;

    ModuleDatabase moduleDB;
    List<Module> moduleList;
    ModuleViewModel moduleViewModel;

    private PopupEditModuleBinding binding;
    private Module module;
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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        module = ModuleEditFragmentArgs.fromBundle(getArguments()).getCurrentModules();

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
        View view = inflater.inflate(R.layout.popup_edit_module, container, false);

        module = ModuleEditFragmentArgs.fromBundle(getArguments()).getCurrentModules();


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
//        ButttonEditCancel = view.findViewById(R.id.ButttonEditCancel);
        clearCalender = view.findViewById(R.id.clearCalender);

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

        // this is the drawable Date icon
        AssignmentDate1Label = view.findViewById(R.id.AssignmentDate1Label);
        AssignmentDate2Label= view.findViewById(R.id.AssignmentDate2Label);
        ExamDateLabel= view.findViewById(R.id.ExamDateLabel);

        clearCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Clears All Dates of the Module
                ExamDateText.setText(null);
                AssignmentDate1Text.setText(null);
                AssignmentDate2Text.setText(null);

            }
        });

        ExamDateLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opens the Date picker dialog with and adds date as a string to the field in brackets
                openDialog(ExamDateText);

            }
        });

        ExamDateText.setOnClickListener(new View.OnClickListener() {
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

        AssignmentDate1Text.setOnClickListener(new View.OnClickListener() {
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

        AssignmentDate2Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //opens the Date picker dialog with and adds date as a string to the field in brackets
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
        buttonChangeModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // will set once the button is clicked it will save the data to be set to what ever the data is set.
                module.setModuleCode(ModuleCodeText.getText().toString().trim().toUpperCase());
                module.setModuleName(ModuleNameText.getText().toString());

                module.setAssignmentDate1(AssignmentDate1Text.getText().toString());
                module.setAssignmentName1(AssignmentName1Text.getText().toString());

                module.setAssignmentDate2(AssignmentDate2Text.getText().toString());
                module.setAssignmentName2(AssignmentName2Text.getText().toString());

                module.setExamdate(ExamDateText.getText().toString());
                module.setExamName(ExamNameText.getText().toString());

                module.setTutorialRoom(TutorialRoomText.getText().toString().trim().toUpperCase());
                module.setLectureRoom(LectureRoomText.getText().toString().trim().toUpperCase());


                EditModuleInBackground(module);

                Log.i("MS","changing the module");
                // after it edited the module it will send you back to the main Screen
                NavHostFragment.findNavController(ModuleEditFragment.this).navigate(R.id.action_nav_moduleEdit_to_nav_module);


            }
        });

//        ButttonEditCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                NavHostFragment.findNavController(ModuleEditFragment.this).navigate(R.id.action_nav_moduleEdit_to_nav_module);
//
//                Log.i("MS","Canceling Edit");
//            }
//        });

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

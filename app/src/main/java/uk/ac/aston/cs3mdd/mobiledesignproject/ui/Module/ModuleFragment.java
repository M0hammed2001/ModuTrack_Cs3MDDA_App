package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;

import java.util.Objects;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;

public class ModuleFragment extends Fragment {

    private Button add; // Button to add a new module card
    private AlertDialog dialog; // Dialog for entering module details
    private LinearLayout layout; // Layout for displaying module cards
    private ModuleList moduleList; // Module list manager
    private SharedPreferences sharedPreferences; // SharedPreferences for data storage

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_module, container, false);

        // Initialize SharedPreferences
        sharedPreferences = requireContext().getSharedPreferences("ModuleData", Context.MODE_PRIVATE);

        // Initialize UI elements
        add = rootView.findViewById(R.id.button_addmodule);
        layout = rootView.findViewById(R.id.container);
        moduleList = new ModuleList();

        // Restore data from SharedPreferences
        restoreData();

        // Build the dialog for entering module details
        buildDialog();

        // Set an OnClickListener for the "Add" button
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the dialog when the "Add" button is clicked
                dialog.show();
            }
        });

        return rootView;
    }

    private void buildDialog() {
        // Create a dialog for entering module details
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        // Initialize EditText fields for module details
        final EditText modulename = view.findViewById(R.id.modulename_add);
        final EditText assignmentsName = view.findViewById(R.id.assignmentsName_add);
        final EditText assignmentsDate = view.findViewById(R.id.assignmentsDateTime_add);
        final EditText examName = view.findViewById(R.id.examName_add);
        final EditText examDate = view.findViewById(R.id.examDate_add);
        final EditText lessonDateTime = view.findViewById(R.id.lessonDateTime_add);
        final EditText LessonRoom = view.findViewById(R.id.RoomNumber_add);

        builder.setView(view);
        builder.setTitle("Enter Module Details");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String moduleName = modulename.getText().toString();
                String AssignmentsName = assignmentsName.getText().toString();
                String AssignmentsDate = assignmentsDate.getText().toString();
                String ExamName = examName.getText().toString();
                String ExamDate = examDate.getText().toString();
                String LessonDateTime = lessonDateTime.getText().toString();
                String lessonRoom = LessonRoom.getText().toString();

                Module module = new Module(moduleName, AssignmentsName, AssignmentsDate, ExamName, ExamDate, LessonDateTime, lessonRoom);

                // Add the module to the ModuleList
                moduleList.add(module);
                // Update the UI to display the new module card
                addCard(module);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the "Cancel" button click (you can add functionality here)
            }
        });

        dialog = builder.create(); // Create the dialog
    }

    private void addCard(Module module) {
        // Create a new module card and populate it with the module's details
        final View view = getLayoutInflater().inflate(R.layout.modulecard, null);

        // Initialize TextView fields on the module card
        TextView ModulenameView = view.findViewById(R.id.ModulenameField);
        TextView assignmentNameView = view.findViewById(R.id.AssignmentsNameField);
        TextView assignmentsDateView = view.findViewById(R.id.AssignmentsDateField);
        TextView examNameView = view.findViewById(R.id.ExamNameField);
        TextView examDateView = view.findViewById(R.id.ExamDateField);
        TextView lessonDateTimeView = view.findViewById(R.id.LessonDateTimeField);
        TextView lessonRoomView = view.findViewById(R.id.RoomField);
        Button delete = view.findViewById(R.id.button_delete);

        // Set the details on the card from the module
        ModulenameView.setText(module.getModuleName());
        assignmentNameView.setText("Assignments: " + module.getAssignmentsName());
        assignmentsDateView.setText("Date: " + module.getAssignmentsDate());
        examNameView.setText("Exam: " + module.getExamName());
        examDateView.setText("Date: " + module.getExamDate());
        lessonDateTimeView.setText("Lesson: " + module.getLessonDateTime());
        lessonRoomView.setText("Room: " + module.getLessonRoom());

        // Set a click listener to remove the card when the "Delete" button is clicked
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
                // Remove the module from the ModuleList
                moduleList.delete(module);
                // Update data in SharedPreferences after deletion
                saveData();
            }
        });

        // Add the module card to the layout
        layout.addView(view);

        // Save the added module to SharedPreferences
        saveData();
    }

    private void saveData() {
        // Save the data of all modules in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        StringBuilder data = new StringBuilder();

        for (Module module : moduleList.getModules()) {
            data.append(module.getModuleName()).append(",");
            data.append(module.getAssignmentsName()).append(",");
            data.append(module.getAssignmentsDate()).append(",");
            data.append(module.getExamName()).append(",");
            data.append(module.getExamDate()).append(",");
            data.append(module.getLessonDateTime()).append(",");
            data.append(module.getLessonRoom()).append(";");
        }

        editor.putString("module_data", data.toString());
        editor.apply();
    }

    private void restoreData() {
        // Retrieve data from SharedPreferences and create Module instances
        String data = sharedPreferences.getString("module_data", "");

        if (!data.isEmpty()) {
            String[] modulesData = data.split(";");
            for (String moduleData : modulesData) {
                String[] moduleDetails = moduleData.split(",");
                if (moduleDetails.length == 7) {
                    Module module = new Module(
                            moduleDetails[0], moduleDetails[1], moduleDetails[2],
                            moduleDetails[3], moduleDetails[4], moduleDetails[5], moduleDetails[6]
                    );
                    moduleList.add(module);
                    addCard(module);
                }
            }
        }
    }
}

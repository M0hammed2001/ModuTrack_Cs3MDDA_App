package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Button;
import android.widget.LinearLayout;
import uk.ac.aston.cs3mdd.mobiledesignproject.R;

public class ModuleFragment extends Fragment {

    private Button add; // Button to add a new module card
    private AlertDialog dialog; // Dialog for entering module details
    private LinearLayout layout; // Layout for displaying module cards

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment's layout
        View rootView = inflater.inflate(R.layout.fragment_module, container, false);

        // Initialize UI elements
        add = rootView.findViewById(R.id.button_addmodule);
        layout = rootView.findViewById(R.id.container);

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

        return rootView; // Return the fragment's view
    }

    private void buildDialog() {
        // Create a dialog for entering module details
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        View view = getLayoutInflater().inflate(R.layout.dialog, null);

        // Initialize EditText fields for module details
        final EditText modulenameEdit = view.findViewById(R.id.modulename_Edit);
        final EditText assignmentsDueEdit = view.findViewById(R.id.assignmentsDue_Edit);
        final EditText examDueEdit = view.findViewById(R.id.examDue_Edit);
        final EditText lessonDateTimeEdit = view.findViewById(R.id.lessonDateTime_Edit);

        builder.setView(view);
        builder.setTitle("Enter Module Details")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // When the "OK" button is clicked, add a new module card with details
                        String moduleName = modulenameEdit.getText().toString();
                        String assignmentsDue = assignmentsDueEdit.getText().toString();
                        String examDue = examDueEdit.getText().toString();
                        String lessonDateTime = lessonDateTimeEdit.getText().toString();
                        addCard(moduleName, assignmentsDue, examDue, lessonDateTime);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the "Cancel" button click (you can add functionality here)
                    }
                });

        dialog = builder.create(); // Create the dialog
    }

    private void addCard(String modulename, String assignmentsDue, String examDue, String lessonDateTime) {
        // Create a new module card and populate it with the entered details
        final View view = getLayoutInflater().inflate(R.layout.modulecard, null);

        // Initialize TextView fields on the module card
        TextView ModulenameView = view.findViewById(R.id.ModulenameField);
        TextView assignmentsDueView = view.findViewById(R.id.AssignmentsDueField);
        TextView examDueView = view.findViewById(R.id.ExamDueField);
        TextView lessonDateTimeView = view.findViewById(R.id.LessonDateTimeField);
        Button delete = view.findViewById(R.id.button_delete);

        // Set the details on the card
        ModulenameView.setText(modulename);
        assignmentsDueView.setText("Assignments Due: " + assignmentsDue);
        examDueView.setText("Exam Due: " + examDue);
        lessonDateTimeView.setText("Lesson Date and Time: " + lessonDateTime);

        // Set a click listener to remove the card when the "Delete" button is clicked
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.removeView(view);
            }
        });

        // Add the module card to the layout
        layout.addView(view);
    }
}
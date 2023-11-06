package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.HashSet;
import java.util.Set;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;

import android.widget.TextView;


public class DataEntryDialogFragment extends DialogFragment {

    private SharedPreferences sharedPrefs;
    private TextView dataTextView; // Add dataTextView reference

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize SharedPreferences
        sharedPrefs = requireActivity().getSharedPreferences("MyDataPrefs", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.popup_layout, null);
        EditText dataEditText = view.findViewById(R.id.dataEditText);
        dataTextView = requireActivity().findViewById(R.id.dataTextView); // Initialize dataTextView

        AlertDialog dialog = new AlertDialog.Builder(requireContext())
                .setTitle("Add Data")
                .setView(view)
                .setPositiveButton("Save", null)
                .create();

        // Override the positive button's click listener
        dialog.setOnShowListener(dialogInterface -> {
            Button saveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
            saveButton.setOnClickListener(v -> {
                String newData = dataEditText.getText().toString();
                if (!newData.isEmpty()) {
                    saveData(newData);

                    // Update dataTextView with the saved data
                    dataTextView.append(newData + "\n");

                    dialog.dismiss();
                }
            });
        });

        return dialog;
    }

    private void saveData(String newData) {
        // Get the existing set of data
        Set<String> dataSet = sharedPrefs.getStringSet("dataSet", new HashSet<>());

        // Add the new data
        dataSet.add(newData);

        // Save the updated data to SharedPreferences
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putStringSet("dataSet", dataSet);
        editor.apply();
    }
}

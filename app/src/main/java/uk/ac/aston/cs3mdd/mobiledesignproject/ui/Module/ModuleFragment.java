package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

//import android.content.Context;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Switch;
//import android.widget.TextView;
//import android.widget.Toast;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.HashSet;
import java.util.Set;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.DataEntryDialogFragment;

public class ModuleFragment extends Fragment {

    private SharedPreferences sharedPrefs;
    private TextView dataTextView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize SharedPreferences
        sharedPrefs = requireActivity().getSharedPreferences("MyDataPrefs", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.testmodule_fragment, container, false);

        Button addDataButton = view.findViewById(R.id.addDataButton);
        dataTextView = view.findViewById(R.id.dataTextView);

        addDataButton.setOnClickListener(v -> openPopup());

        // Display saved data
        displaySavedData();

        return view;
    }

    private void openPopup() {
        DialogFragment dialogFragment = new DataEntryDialogFragment();
        dialogFragment.show(getFragmentManager(), "DataEntryDialog");
    }

    public void displaySavedData() {
        Set<String> dataSet = sharedPrefs.getStringSet("dataSet", new HashSet<>());
        StringBuilder dataBuilder = new StringBuilder();

        for (String data : dataSet) {
            dataBuilder.append(data).append("\n");
        }

        dataTextView.setText(dataBuilder.toString());
    }
}




//    // Declare UI elements
//    private TextView textView;
//    private EditText editText;
//    private Button applyTextButton;
//    private Button saveButton;
//    private Switch switch1;
//
//    // Declare constants for shared preferences keys
//    public static final String SHARED_PREFS = "sharedPrefs";
//    public static final String TEXT = "text";
//    public static final String SWITCH1 = "switch1";
//
//    // Declare variables to store data
//    private String text;
//    private boolean switchOnOff;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // Inflate the fragment's layout from an XML resource
//        View rootView = inflater.inflate(R.layout.testmodule_fragment, container, false);
//
//        // Initialize UI elements by finding them in the inflated view
//            //textview is what displays it
//        textView = rootView.findViewById(R.id.textview);
//
//            //edittext is the box
//        editText = rootView.findViewById(R.id.testModuleName);
//
//        // what adds the text to the text view
//
//        //saves the data
//        saveButton = rootView.findViewById(R.id.save_button);
//        switch1 = rootView.findViewById(R.id.switch1);
//
//        // Set click listeners for the buttons
//        applyTextButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // When the "Apply" button is clicked, set the TextView's text to the EditText's text
//                textView.setText(editText.getText().toString());
//            }
//        });
//
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // When the "Save" button is clicked, save data using SharedPreferences
//                saveData();
//            }
//        });
//
//        // Load data from SharedPreferences and update the UI
//        loadData();
//        updateViews();
//
//        // Return the root view
//        return rootView;
//    }
//
//    // Method to save data to SharedPreferences
//    public void saveData() {
//        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        // Save the text from the TextView and the state of the Switch
//        editor.putString(TEXT, textView.getText().toString());
//        editor.putBoolean(SWITCH1, switch1.isChecked());
//
//        // Apply the changes
//        editor.apply();
//
//        // Display a toast message to indicate that data is saved
//        Toast.makeText(requireContext(), "Data saved", Toast.LENGTH_SHORT).show();
//    }
//
//    // Method to load data from SharedPreferences
//    public void loadData() {
//        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
//
//        // Load the saved text and switch state (defaulting to empty string and 'false' if not found)
//        text = sharedPreferences.getString(TEXT, "");
//        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false);
//    }
//
//    // Method to update the UI elements with the loaded data
//    public void updateViews() {
//        // Set the TextView's text and the Switch's state
//        textView.setText(text);
//        switch1.setChecked(switchOnOff);
//    }
}
package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;

public class ModuleFragment extends Fragment {
    private TextView textView;
    private EditText editText;
    private Button applyTextButton;
    private Button saveButton;
    private Switch switch1;

    // Define keys for shared preferences
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    private String text;
    private boolean switchOnOff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.testmodule_layout, container, false);

        // Initialize UI elements
        textView = view.findViewById(R.id.textview);
        editText = view.findViewById(R.id.edittext);
        applyTextButton = view.findViewById(R.id.apply_text_button);
        saveButton = view.findViewById(R.id.save_button);
        switch1 = view.findViewById(R.id.switch1);

        // Set click listener for applyTextButton
        applyTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update the text view with the text from the edit text
                textView.setText(editText.getText().toString());
            }
        });

        // Set click listener for saveButton
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Save data to shared preferences
                saveData();
            }
        });

        // Load data from shared preferences and update the views
        loadData();
        updateViews();

        return view;
    }

    public void saveData() {
        Context context = requireContext(); // Use the context of the fragment
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the text and switch state to shared preferences
        editor.putString(TEXT, textView.getText().toString());
        editor.putBoolean(SWITCH1, switch1.isChecked());

        editor.apply();

        Toast.makeText(context, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        Context context = requireContext(); // Use the context of the fragment
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // Load text and switch state from shared preferences
        text = sharedPreferences.getString(TEXT, "");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false);
    }

    public void updateViews() {
        // Update the text view and switch state based on loaded data
        textView.setText(text);
        switch1.setChecked(switchOnOff);
    }
}
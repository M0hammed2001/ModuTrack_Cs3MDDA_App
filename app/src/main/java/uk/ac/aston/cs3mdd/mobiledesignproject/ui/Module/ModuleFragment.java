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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;

public class ModuleFragment extends Fragment {
    // UI elements
    private LinearLayout itemContainer;
    private Button addItemButton;

    // Shared preferences keys
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ITEM_COUNT = "itemCount";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the fragment layout
        View view = inflater.inflate(R.layout.testmodule_layout, container, false);

        // Initialize UI elements
        itemContainer = view.findViewById(R.id.item_container);
        addItemButton = view.findViewById(R.id.add_item_button);

        // Set click listener for the "Add Item" button
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(); // Call a method to add a new item
            }
        });

        // Restore previously added items
        int itemCount = loadData(); // Load the number of items from shared preferences

        // Add the saved number of items to the UI
        for (int i = 0; i < itemCount; i++) {
            addItem();
        }

        return view;
    }

    // Method to add a new item to the UI
    private void addItem() {
        // Inflate the item layout
        View itemView = getLayoutInflater().inflate(R.layout.testmodule_layout, null);

        // Initialize UI elements for the item
        TextView textView = itemView.findViewById(R.id.textview);
        EditText editText = itemView.findViewById(R.id.edittext);
        Button applyTextButton = itemView.findViewById(R.id.apply_text_button);
        Switch switch1 = itemView.findViewById(R.id.switch1);

        // Set click listener for the "Apply Text" button
        applyTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update the text view within the item
                textView.setText(editText.getText().toString());
            }
        });

        // Add the item to the container
        itemContainer.addView(itemView);
    }

    // Method to save the number of added items to shared preferences
    private void saveData(int itemCount) {
        Context context = requireContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the number of items
        editor.putInt(ITEM_COUNT, itemCount);

        editor.apply();
    }

    // Method to load the number of added items from shared preferences
    private int loadData() {
        Context context = requireContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);

        // Load the number of items (default to 0)
        return sharedPreferences.getInt(ITEM_COUNT, 0);
    }
}

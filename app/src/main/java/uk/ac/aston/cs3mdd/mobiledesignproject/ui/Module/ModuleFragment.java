package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import java.util.ArrayList;

public class ModuleFragment extends Fragment {
    // UI elements
    private LinearLayout itemContainer;
    private Button addItemButton;
    private ArrayList<String> itemTextList;

    // Shared preferences keys
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ITEM_COUNT = "itemCount";
    public static final String ITEM_TEXT = "itemText";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.testmodule_layout, container, false);

        // Initialize UI elements
        itemContainer = view.findViewById(R.id.item_container);
        addItemButton = view.findViewById(R.id.add_item_button);

        // Initialize the ArrayList for item text
        itemTextList = new ArrayList<>();

        // Set click listener for the "Add Item" button
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(); // Add a new item
            }
        });

        // Restore previously added items
        int itemCount = loadData(); // Load the number of items from shared preferences
        loadItemText(); // Load item text

        // Add the saved number of items to the UI
        for (int i = 0; i < itemCount; i++) {
            addItem();
            if (i < itemTextList.size()) {
                updateItemText(i, itemTextList.get(i));
            }
        }

        return view;
    }

    // Method to add a new item to the UI
    private void addItem() {
        View itemView = getLayoutInflater().inflate(R.layout.modulecard, null);

        // Add the item view to the container
        itemContainer.addView(itemView);

        // Initialize UI elements for the item
        TextView moduleNameField = itemView.findViewById(R.id.ModulenameField);
        // Initialize other TextView elements here

        Button buttonDelete = itemView.findViewById(R.id.button_delete);
        Button buttonEdit = itemView.findViewById(R.id.button_Edit);

        // Set click listener for the "Edit" button
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the edit action
                handleEditClick(itemContainer.indexOfChild(itemView));
            }
        });

        // Set click listener for the "Delete" button
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the delete action
                handleDeleteClick(itemContainer.indexOfChild(itemView));
            }
        });
    }

    // Handle the edit button click (customize this logic)
    private void handleEditClick(int itemIndex) {
        if (itemIndex >= 0) {
            // Add your custom logic for editing here
            Toast.makeText(requireContext(), "Edit clicked for item " + itemIndex, Toast.LENGTH_SHORT).show();
        }
    }

    // Handle the delete button click
    private void handleDeleteClick(int itemIndex) {
        if (itemIndex >= 0) {
            // Remove the item from the container
            itemContainer.removeViewAt(itemIndex);
            // Remove the associated item text
            removeItemText(itemIndex);
        }
    }
}

// Method to remove an item's text

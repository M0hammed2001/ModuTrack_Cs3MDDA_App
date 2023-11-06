package uk.ac.aston.cs3mdd.mobiledesignproject.ui.TestModule;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.Module;

public class TestModuleFragment extends Fragment {
//    private EditText moduleNameEditText;
//    private Button addModuleButton;
//    private RecyclerView moduleListRecyclerView;
//
//    private DatabaseHelper databaseHelper;
//    private ModuleAdapter moduleAdapter;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.testmodule_fragment, container, false);
//
//        moduleNameEditText = view.findViewById(R.id.moduleName);
//        addModuleButton = view.findViewById(R.id.addModuleButton);
//        moduleListRecyclerView = view.findViewById(R.id.moduleList);
//
//        // Initialize the database helper and adapter
//        databaseHelper = new DatabaseHelper(getActivity());
//        moduleAdapter = new ModuleAdapter(getModulesFromDatabase());
//
//        // Set up RecyclerView
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        moduleListRecyclerView.setLayoutManager(layoutManager);
//        moduleListRecyclerView.setAdapter(moduleAdapter);
//
//        // Set click listener for the "Add" button
//        addModuleButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String moduleName = moduleNameEditText.getText().toString();
//                if (!moduleName.isEmpty()) {
//                    // Insert the new module into the database
//                    databaseHelper.insertModule(new Module(moduleName));
//                    // Update the RecyclerView
//                    moduleAdapter.updateModules(getModulesFromDatabase());
//                    moduleNameEditText.setText("");
//                }
//            }
//        });
//
//        return view;
//    }
//
//    private List<Module> getModulesFromDatabase() {
//        // Retrieve modules from the database using your database helper
//        return databaseHelper.getAllModules();
//    }
//
//    public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder> {
//        private List<Module> modules;
//
//        public ModuleAdapter(List<Module> modules) {
//            this.modules = modules;
//        }
//
//        @NonNull
//        @Override
//        public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            return null;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
//
//        }
//
//        @Override
//        public int getItemCount() {
//            return 0;
//        }
//
//        // Implement RecyclerView adapter methods (onCreateViewHolder, onBindViewHolder, getItemCount)
//
//        public class ModuleViewHolder extends RecyclerView.ViewHolder {
//            private TextView moduleNameTextView;
//            private Button editButton;
//            private Button deleteButton;
//
//            public ModuleViewHolder(View itemView) {
//                super(itemView);
//                moduleNameTextView = itemView.findViewById(R.id.moduleNameTextView);
//                editButton = itemView.findViewById(R.id.editButton);
//                deleteButton = itemView.findViewById(R.id.deleteButton);
//            }
//        }
//    }
//    public ModuleAdapter.ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        // Inflate the item layout and create a ViewHolder
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_list_item, parent, false);
//        return new ModuleViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
//        // Bind data to the ViewHolder
//        Module module = modules.get(position);
//        holder.moduleNameTextView.setText(module.getName());
//        // Handle edit and delete buttons here
//    }
//
//    @Override
//    public int getItemCount() {
//        return modules.size();
//    }
}

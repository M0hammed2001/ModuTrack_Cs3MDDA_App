package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.information.ModuleInformation;

public class ModuleFragment extends Fragment {
    private ModuleViewModel moduleinformation;


    private FragmentModuleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ModuleViewModel moduleViewModel =
                new ViewModelProvider(this).get(ModuleViewModel.class);

        binding = FragmentModuleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //final TextView textView = binding.textModule;
       // moduleViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        moduleinformation = new ViewModelProvider(requireActivity()).get(ModuleViewModel.class);
        binding.textviewModulename.setText(moduleinformation.getCurrentModule().getValue().getModuleName());
        updateUI();

        binding.editModulename.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Fires right as the text is being changed (even supplies the range of text)
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Fires right before text is changing
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Fires right after the text has changed
                moduleinformation.setCurrentModuleTitle(s.toString());
            }
        });
        moduleinformation.getCurrentModule().observe(getViewLifecycleOwner(), moduleInformation -> {
            // Update the UI.
            String modulename = moduleInformation.getModuleCode() + ": " + moduleInformation.getModuleName() + " (" + moduleInformation.getCredits() + " credits)";
            binding.textviewModulename.setText(modulename);
        });
    }

    private void updateUI() {
        ModuleInformation m = moduleinformation.getCurrentModule().getValue();
        String modulename = m.getModuleCode() + ": " + m.getModuleName() + " (" + m.getCredits() + " credits)";
        binding.textviewModulename.setText(modulename);
        binding.textviewDescription.setText(m.getDescription());
//        binding.textviewLo.setText(m.getLearningOutcomes());

        binding.editModulename.setText(m.getModuleName());
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
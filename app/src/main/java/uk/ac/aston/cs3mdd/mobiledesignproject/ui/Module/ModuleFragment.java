package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import android.os.Bundle;
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
        binding.textviewTitle.setText(moduleinformation.getCurrentModule().getValue().getModuleName());
        updateUI();
    }

    private void updateUI() {
        ModuleInformation m = moduleinformation.getCurrentModule().getValue();
        String title = m.getModuleCode() + ": " + m.getModuleName() + " (" + m.getCredits() + " credits)";
        binding.textviewTitle.setText(title);
        binding.textviewDescription.setText(m.getDescription());
//        binding.textviewLo.setText(m.getLearningOutcomes());
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
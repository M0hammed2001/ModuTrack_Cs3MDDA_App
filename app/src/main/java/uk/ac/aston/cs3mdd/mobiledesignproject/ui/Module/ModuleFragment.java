package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentModuleBinding;

public class ModuleFragment extends Fragment {

    private FragmentModuleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ModuleViewModel moduleViewModel =
                new ViewModelProvider(this).get(ModuleViewModel.class);

        binding = FragmentModuleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textModule;
        moduleViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
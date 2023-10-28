package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Trainmap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentMapBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentTrainmapBinding;

public class TrainMapFragment extends Fragment {

    private FragmentTrainmapBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        TrainMapViewModel trainMapViewModel =
                new ViewModelProvider(this).get(TrainMapViewModel.class);

        binding = FragmentTrainmapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTrainmap;
        trainMapViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
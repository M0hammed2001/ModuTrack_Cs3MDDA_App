package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Trainmap;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentMapBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentTrainmapBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainService;

public class TrainMapFragment extends Fragment {

    private FragmentTrainmapBinding binding;

    private TrainService trainService;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TrainMapViewModel trainMapViewModel = new ViewModelProvider(this).get(TrainMapViewModel.class);

        trainService = TrainMapFragmentArgs.fromBundle(getArguments()).getTrainervices();

        binding = FragmentTrainmapBinding.inflate(inflater, container, false);
//        final TextView textView = binding.textTrainmap;
//        trainMapViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonTrainmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(TrainMapFragment.this)
                        .navigate(R.id.action_nav_trainmap_to_nav_train);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Map;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
//import androidx.navigation.fragment.NavHostFragment;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentMapBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainService;

public class MapFragment extends Fragment {

    private FragmentMapBinding binding;

    private TrainService trainService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState
    ) {
        MapViewModel mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);

//        trainService = MapFragmentArgs.fromBundle(getArguments()).getTrains();

        binding = FragmentMapBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textMap;
//        mapViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        binding.buttonMap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                NavHostFragment.findNavController(MapFragment.this)
//                        .navigate(R.id.action_Mapfragment_to_Trainservices);
//            }
//        });
////        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
////                .findFragmentById(R.id.nav_map);
////        mapFragment.getMapAsync(this);
//        binding.textMap.setText(trainService.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
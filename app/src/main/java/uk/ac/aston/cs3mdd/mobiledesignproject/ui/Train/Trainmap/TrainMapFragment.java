package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.Trainmap;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PolylineOptions;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentTrainmapBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainService;

public class TrainMapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentTrainmapBinding binding;
    private TrainService trainService;
    private GoogleMap mMap;




    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Define the coordinates for Birmingham New Street and Aston University
        LatLng birminghamNewStreet = new LatLng(52.477662, -1.898012); // Birmingham New Street coordinates
        LatLng astonUniversity = new LatLng(52.487144, -1.886977); // Aston University coordinates

        // Added markers for Birmingham New Street and Aston University which you can see by clicking it
        mMap.addMarker(new MarkerOptions()
                .position(birminghamNewStreet)
                .title("Birmingham New Street"));

        mMap.addMarker(new MarkerOptions()
                .position(astonUniversity)
                .title("Aston University"));

        // Create a line between Birmingham New Street and Aston University for quick judgement
        PolylineOptions polylineOptions = new PolylineOptions()
                .add(birminghamNewStreet, astonUniversity)
                .width(5) // Line width in pixels
                .color(Color.BLUE); // Line color

        // Add the line to the map
        mMap.addPolyline(polylineOptions);

        // set the map view to a good level so you can see both markers
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(birminghamNewStreet);
        builder.include(astonUniversity);
        LatLngBounds bounds = builder.build();
        int padding = 140; // Padding in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.moveCamera(cu);
    }



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        TrainMapViewModel trainMapViewModel = new ViewModelProvider(this).get(TrainMapViewModel.class);

        trainService = TrainMapFragmentArgs.fromBundle(getArguments()).getTrainervices();

        binding = FragmentTrainmapBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.TrainMap);
        mapFragment.getMapAsync(this);

        binding.buttonTrainmap.setOnClickListener(new View.OnClickListener() {
         @Override
          public void onClick(View view) {
           NavHostFragment.findNavController(TrainMapFragment.this)
                   .navigate(R.id.action_nav_trainmap_to_nav_train);
           }
        });
    binding.textTrainmap.setText(trainService.toString());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
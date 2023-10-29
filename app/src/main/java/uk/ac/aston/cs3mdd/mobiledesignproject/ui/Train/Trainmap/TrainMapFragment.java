package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.Trainmap;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.Destination;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainService;

public class TrainMapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentTrainmapBinding binding;
    private TrainService trainService;
    private  Location location;
    private GoogleMap mMap;
    public Destination destination;


//    private LatLng geocodeLocation(String locationName) {
//        // Implement geocoding logic here to get coordinates from the location name
//        // Example:
//        if (locationName.equals("Wolverhampton")) {
//            return new LatLng(52.5862, -2.1288);
//        } else if (locationName.equals("Telford Central")) {
//            return new LatLng(52.6815, -2.4463);
//        } else if (locationName.equals("Sandwell & Dudley")) {
//            return new LatLng(52.5023, -2.0214);
//        } else if (locationName.equals("Birmingham International")) {
//            return new LatLng(52.4520, -1.7381);
//        } else if (locationName.equals("Birmingham New Street")) {
//            return new LatLng(52.4520, -1.7381);
//        } else if (locationName.equals("Shrewsbury")) {
//            return new LatLng(52.7101, -2.7521);
//        } else if (locationName.equals("Walsall")) {
//            return new LatLng(52.5844, -1.9787);
//        } else if (locationName.equals("Bescot Stadium")) {
//            return new LatLng(52.5640, -1.9913);
//        } else if (locationName.equals("Tame Bridge Parkway")) {
//            return new LatLng(52.5466, -1.9741);
//        } else if (locationName.equals("Hamstead")) {
//            return new LatLng(52.5542, -1.9288);
//        } else if (locationName.equals("Perry Barr")) {
//            return new LatLng(52.5145, -1.8978);
//        } else if (locationName.equals("Witton")) {
//            return new LatLng(52.5093, -1.8849);
//        } else if (locationName.equals("Aston")) {
//            return new LatLng(52.5097, -1.8844);
//        } else {
//            // Shows a toast message advising mentioned location is not on the system yet.
//            Context context = requireContext(); // or use getActivity() if applicable
//            CharSequence text = "Location not found in the system";
//            int duration = Toast.LENGTH_SHORT;
//            Toast toast = Toast.makeText(context, text, duration);
//            toast.show();
//
//            return new LatLng(0, 0); // Default to (0, 0) if not found
//        }
//    }

//    @Override
//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Geocode the destination location name to get its coordinates (latitude and longitude)
//        LatLng loc = geocodeLocation(destination.getLocationName());
//
//        // Add a marker at the destination location and move the camera
//        mMap.addMarker(new MarkerOptions()
//                .position(loc)
//                .title("Location of this Station is"));
//
//        // Move the camera to the destination location
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 10));
//        mMap.getUiSettings().setZoomControlsEnabled(true);
//    }

//    public void onMapReady(@NonNull GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Geocode the destination location name to get its coordinates (latitude and longitude)
//        LatLng loc = geocodeLocation(destination.getLocationName());
//
//        // Add a marker at the destination location
//        mMap.addMarker(new MarkerOptions()
//                .position(loc)
//                .title("Location of this Station is"));
//
//        // Move the camera to the destination location
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 10));
//        mMap.getUiSettings().setZoomControlsEnabled(true);
//
//        // Define the coordinates for Aston University and Birmingham New Street
//        LatLng astonUniversity = new LatLng(52.5097, -1.8844); // Replace with the actual coordinates
//        LatLng birminghamNewStreet = new LatLng(52.4520, -1.7381); // Replace with the actual coordinates
//
//        // Create a PolylineOptions to specify the line's style
//        PolylineOptions lineOptions = new PolylineOptions()
//                .add(astonUniversity, birminghamNewStreet) // Add the two LatLng coordinates
//                .width(5) // Line width in pixels
//                .color(Color.BLUE); // Line color
//
//        // Add the line to the map
//        mMap.addPolyline(lineOptions);
//    }

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
        int padding = 100; // Padding in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.moveCamera(cu);
    }
    private LatLng geocodeLocation(String locationName) {

        return geocodeLocation(locationName);
    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TrainMapViewModel trainMapViewModel = new ViewModelProvider(this).get(TrainMapViewModel.class);

        trainService = TrainMapFragmentArgs.fromBundle(getArguments()).getTrainervices();

        binding = FragmentTrainmapBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
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
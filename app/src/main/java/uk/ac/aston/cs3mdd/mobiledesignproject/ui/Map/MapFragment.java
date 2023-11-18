package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Map;
import android.graphics.Color;
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

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentMapBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainService;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapBinding binding;

    private TrainService trainService;

//    private MapViewModel mapViewModel;

    private GoogleMap mMap;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        MapViewModel mapViewModel = new ViewModelProvider(this).get(MapViewModel.class);

        binding = FragmentMapBinding.inflate(inflater, container, false);


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.AstonMap);
        mapFragment.getMapAsync(this);

    }

    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Define the coordinates for all the location i want to show on the map

        // the food places
        LatLng Greggs = new LatLng(52.48582884188431, -1.8910272846843958); // Birmingham New Street coordinates
        LatLng Tesco = new LatLng(52.48551440801394, -1.8904186776985403); // Birmingham New Street coordinates
        LatLng WokandGo = new LatLng(52.48554522703285, -1.8910799271371763); // Birmingham New Street coordinates

        // aston locations
        LatLng astonMosque = new LatLng(52.484103960792446, -1.8887222355078963); // Aston University coordinates
        LatLng AstonLibrary = new LatLng(52.485762, -1.888777); // Birmingham New Street coordinates
        LatLng astonSU = new LatLng(52.484441, -1.889462); // Aston University coordinates
        LatLng MainBuildingMain = new LatLng(52.486505, -1.889672); // Birmingham New Street coordinates
        LatLng MainbuildingOOH = new LatLng(52.486307, -1.890072); // Aston University coordinates

        // Added markers for Birmingham New Street and Aston University which you can see by clicking it
//        mMap.addMarker(new MarkerOptions()
//                .position(birminghamNewStreet)
//                .title("Birmingham New Street"));


        mMap.addMarker(new MarkerOptions().position(AstonLibrary).title("Aston Library 'L' "));

        mMap.addMarker(new MarkerOptions().position(astonSU).title("Aston Student Union"));

        mMap.addMarker(new MarkerOptions().position(MainBuildingMain).title("Aston Main Building 'MB' Main Entrace "));

        mMap.addMarker(new MarkerOptions().position(MainbuildingOOH).title("Aston Main Building 'MB' OOH Entrace "));

        mMap.addMarker(new MarkerOptions().position(astonMosque).title("Aston Mosque"));


        mMap.addMarker(new MarkerOptions().position(Tesco).title("Tesco"));

        mMap.addMarker(new MarkerOptions().position(Greggs).title("Greggs"));

        mMap.addMarker(new MarkerOptions().position(WokandGo).title("Wok and Go"));

        // Create a line between Birmingham New Street and Aston University for quick judgement
//        PolylineOptions polylineOptions = new PolylineOptions()
//                .add(birminghamNewStreet, astonUniversity,Mainbuilding)
//                .width(5) // Line width in pixels
//                .color(Color.BLUE); // Line color
//
////         Add the line to the map
//        mMap.addPolyline(polylineOptions);

        // set the map view to a good level so you can see both markers
        LatLngBounds.Builder builder = new LatLngBounds.Builder();


            builder.include(Tesco);
            builder.include(Greggs);
            builder.include(WokandGo);
            builder.include(AstonLibrary);
            builder.include(astonSU);
            builder.include(MainBuildingMain);
            builder.include(MainbuildingOOH);
            builder.include(astonMosque);

            LatLngBounds bounds = builder.build();
            int padding = 140; // Padding in pixels
            CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
            mMap.moveCamera(cu);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
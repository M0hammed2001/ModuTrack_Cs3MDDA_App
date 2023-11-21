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

import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentMapBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Map.Data.LocationViewModel;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainService;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private FragmentMapBinding binding;

    private TrainService trainService;

    private FusedLocationProviderClient fusedLocationClient;

    private GoogleMap mMap;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.AstonMap);
        mapFragment.getMapAsync(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());


    }

    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);


        // Define the coordinates for all the location i want to show on the map

        // the food places
        LatLng Greggs = new LatLng(52.48582884188431, -1.8910272846843958);
        LatLng Tesco = new LatLng(52.48551440801394, -1.8904186776985403);
        LatLng WokAndGo = new LatLng(52.48554522703285, -1.8910799271371763);

        // aston locations
        LatLng astonMosque = new LatLng(52.484103960792446, -1.8887222355078963);
        LatLng AstonLibrary = new LatLng(52.485762, -1.888777);
        LatLng astonSU = new LatLng(52.484441, -1.889462);
        LatLng MainBuildingMain = new LatLng(52.486505, -1.889672);
        LatLng MainBuildingOOH = new LatLng(52.486307, -1.890072);



        mMap.addMarker(new MarkerOptions().position(AstonLibrary).title("Aston Library 'L' "));

        mMap.addMarker(new MarkerOptions().position(astonSU).title("Aston Student Union"));

        mMap.addMarker(new MarkerOptions().position(MainBuildingMain).title("Aston Main Building 'MB' Main Entrance "));

        mMap.addMarker(new MarkerOptions().position(MainBuildingOOH).title("Aston Main Building 'MB' OOH Entrance "));

        mMap.addMarker(new MarkerOptions().position(astonMosque).title("Aston Mosque"));

        mMap.addMarker(new MarkerOptions().position(Tesco).title("Tesco"));

        mMap.addMarker(new MarkerOptions().position(Greggs).title("Greggs"));

        mMap.addMarker(new MarkerOptions().position(WokAndGo).title("Wok and Go"));


        // set the map view to a good level so you can see both markers
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
            //shops
            builder.include(Tesco);
            builder.include(Greggs);
            builder.include(WokAndGo);
            //uni
            builder.include(AstonLibrary);
            builder.include(astonSU);
            builder.include(MainBuildingMain);
            builder.include(MainBuildingOOH);
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
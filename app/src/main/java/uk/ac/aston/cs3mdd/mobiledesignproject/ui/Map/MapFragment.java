package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Map;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentMapBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainService;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private int OGButtonBackgroundColor, OGButtonTextColor;

    private FragmentMapBinding binding;

    Button MainentranceMB, button_AstonMBOOH, button_AstonLibrary, button_AstonSU, AddBackAllMarkers, button_shop, button_Aston, button_Mosque;

    private TrainService trainService;

    private FusedLocationProviderClient fusedLocationClient;


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


    // set the map view to a good level so you can see both markers
    LatLngBounds.Builder builder = new LatLngBounds.Builder();


    private GoogleMap mMap;


    private void addMarkers() {

        mMap.addMarker(new MarkerOptions().position(AstonLibrary).title("Aston Library 'L' "));
        mMap.addMarker(new MarkerOptions().position(astonSU).title("Aston Student Union"));
        mMap.addMarker(new MarkerOptions().position(MainBuildingMain).title("Aston Main Building 'MB' Main Entrance "));
        mMap.addMarker(new MarkerOptions().position(MainBuildingOOH).title("Aston Main Building 'MB' OOH Entrance "));
        mMap.addMarker(new MarkerOptions().position(astonMosque).title("Aston Mosque"));

//         mMap.addMarker(new MarkerOptions().position(Tesco).title("Tesco").icon(BitmapDescriptorFactory.fromResource(R.drawable.tescomarker)));
        mMap.addMarker(new MarkerOptions().position(Tesco).title("Tesco").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions().position(Greggs).title("Greggs").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions().position(WokAndGo).title("Wok and Go").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));


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

    }

    private void addShops() {

//        mMap.addMarker(new MarkerOptions().position(Tesco).title("Tesco").icon(BitmapDescriptorFactory.fromAsset("tesco_logo")));
        mMap.addMarker(new MarkerOptions().position(Tesco).title("Tesco").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions().position(Greggs).title("Greggs").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.addMarker(new MarkerOptions().position(WokAndGo).title("Wok and Go").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        //shops
        builder.include(Tesco);
        builder.include(Greggs);
        builder.include(WokAndGo);



    }


    private void addUniMarkers() {

        mMap.addMarker(new MarkerOptions().position(AstonLibrary).title("Aston Library 'L' "));
        mMap.addMarker(new MarkerOptions().position(astonSU).title("Aston Student Union"));
        mMap.addMarker(new MarkerOptions().position(MainBuildingMain).title("Aston Main Building 'MB' Main Entrance "));
        mMap.addMarker(new MarkerOptions().position(MainBuildingOOH).title("Aston Main Building 'MB' OOH Entrance "));
        mMap.addMarker(new MarkerOptions().position(astonMosque).title("Aston Mosque"));
        //uni
        builder.include(AstonLibrary);
        builder.include(astonSU);
        builder.include(MainBuildingMain);
        builder.include(MainBuildingOOH);
        builder.include(astonMosque);


    }

    private void camera() {
        LatLngBounds bounds = builder.build();
        int padding = 120; // Padding in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
        mMap.moveCamera(cu);

    }


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMapBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.AstonMap);
        mapFragment.getMapAsync(this);

        MainentranceMB = view.findViewById(R.id.button_AstonMBMain);
        button_AstonMBOOH = view.findViewById(R.id.button_AstonMBOOH);
        button_AstonLibrary = view.findViewById(R.id.button_AstonLibrary);
        button_AstonSU = view.findViewById(R.id.button_AstonSU);
        AddBackAllMarkers = view.findViewById(R.id.AddBackAllMarkers);

        button_shop = view.findViewById(R.id.button_shop);
        button_Aston = view.findViewById(R.id.button_Aston);
        button_Mosque= view.findViewById(R.id.button_Mosque);


        //sets the original button Colours when called in the ResetButtonColour function
        OGButtonBackgroundColor = Color.BLUE;
        OGButtonTextColor = Color.WHITE;

        // sets them on view created
        ResetButtonColour();

        AddBackAllMarkers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clears all the markers off the map
                addMarkers();

                // resets camara View
                camera();

                //resets the button colour to default or makes sure that other button clicks are reset
                ResetButtonColour();

                //sets the focus colour
                AddBackAllMarkers.setTextColor(Color.WHITE);
                AddBackAllMarkers.setBackgroundColor(Color.rgb(128, 0, 128));

                //resets the camara position
                camera();

                camera();
                Log.i("MAP", "all Markers added back");
            }
        });

        button_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clears all the markers off the map
                mMap.clear();

                //resets the button colour to default or makes sure that other button clicks are reset
                ResetButtonColour();

                //sets the focus colour
                button_shop.setTextColor(Color.WHITE);
                button_shop.setBackgroundColor(Color.rgb(128, 0, 128));

                //resets the camara position
                camera();

                //adds all Shop makers to map
                addShops();
                Log.i("MAP", "all Shop Markers added back");
            }
        });

        button_Aston.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clears all the markers off the map
                mMap.clear();

                //resets the button colour to default or makes sure that other button clicks are reset
                ResetButtonColour();

                //sets the focus colour
                button_Aston.setTextColor(Color.WHITE);
                button_Aston.setBackgroundColor(Color.rgb(128, 0, 128));

                //resets the camara position
                camera();

                //adds all uni makers to map
                addUniMarkers();
                Log.i("MAP", "all University Markers added back");
            }
        });


        button_AstonLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clears all the markers off the map
                mMap.clear();
                //adds the marker i want back the the map
                mMap.addMarker(new MarkerOptions().position(AstonLibrary).title("Aston Library 'L' "));



                //resets the button colour to default or makes sure that other button clicks are reset
                ResetButtonColour();

                //resets the camara position
                camera();
//                // sets Camara Possition to Focus on the marker
//                CameraUpdate cu = CameraUpdateFactory.newLatLng(AstonLibrary);
//                mMap.moveCamera(cu);

                //sets the focus colour
                button_AstonLibrary.setTextColor(Color.WHITE);
                button_AstonLibrary.setBackgroundColor(Color.rgb(128, 0, 128));

                Log.i("MAP", "Aston Library Pressed");
            }
        });

        MainentranceMB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clears all the markers off the map
                mMap.clear();

                //resets the button colour to default or makes sure that other button clicks are reset
                ResetButtonColour();



                //sets the focus colour
                MainentranceMB.setTextColor(Color.WHITE);
                MainentranceMB.setBackgroundColor(Color.rgb(128, 0, 128));

                //resets the camara position
                camera();
                // sets Camara Possition to Focus on the marker
//                CameraUpdate cu = CameraUpdateFactory.newLatLng(MainBuildingMain);
//                mMap.moveCamera(cu);

                //adds the marker i want back the the map
                mMap.addMarker(new MarkerOptions().position(MainBuildingMain).title("Aston Main Building 'MB' Main Entrance "));
                Log.i("MAP", "Aston OOH Pressed");
            }
        });

        button_AstonMBOOH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clears all the markers off the map
                mMap.clear();

                //resets the button colour to default or makes sure that other button clicks are reset
                ResetButtonColour();



                //sets the focus colour
                button_AstonMBOOH.setTextColor(Color.WHITE);
                button_AstonMBOOH.setBackgroundColor(Color.rgb(128, 0, 128));

                //resets the camara position
                camera();
                // sets Camara Possition to Focus on the marker
//                CameraUpdate cu = CameraUpdateFactory.newLatLng(MainBuildingOOH);
//                mMap.moveCamera(cu);

                //adds the marker i want back the the map
                mMap.addMarker(new MarkerOptions().position(MainBuildingOOH).title("Aston Main Building 'MB' OOH Entrance "));
                Log.i("MAP", "Aston OOH Pressed");
            }
        });

        button_AstonSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clears all the markers off the map
                mMap.clear();

                //resets the button colour to default or makes sure that other button clicks are reset
                ResetButtonColour();

                //sets the focus colour
                button_AstonSU.setTextColor(Color.WHITE);
                button_AstonSU.setBackgroundColor(Color.rgb(128, 0, 128));

                //resets the camara position
                camera();
//                // sets Camara Possition to Focus on the marker
//                CameraUpdate cu = CameraUpdateFactory.newLatLng(astonSU);
//                mMap.moveCamera(cu);

                //adds the marker i want back the the map
                mMap.addMarker(new MarkerOptions().position(astonSU).title("Aston Student Union"));
                Log.i("MAP", "Aston SU Pressed");
            }
        });

        button_Mosque.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clears all the markers off the map
                mMap.clear();

                //resets the button colour to default or makes sure that other button clicks are reset
                ResetButtonColour();

                //sets the focus colour
                button_Mosque.setTextColor(Color.WHITE);
                button_Mosque.setBackgroundColor(Color.rgb(128, 0, 128));

                //resets the camara position
                camera();
//                // sets Camara Possition to Focus on the marker
//                CameraUpdate cu = CameraUpdateFactory.newLatLng(astonMosque);
//                mMap.moveCamera(cu);

                //adds the marker i want back the the map
                mMap.addMarker(new MarkerOptions().position(astonMosque).title("Aston Mosque"));
                Log.i("MAP", "Aston Mosque Pressed");
            }
        });
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity());


    }

    private void ResetButtonColour(){
        MainentranceMB.setBackgroundColor(OGButtonBackgroundColor);
        MainentranceMB.setTextColor(OGButtonTextColor);

        button_AstonMBOOH.setBackgroundColor(OGButtonBackgroundColor);
        button_AstonMBOOH.setTextColor(OGButtonTextColor);

        button_AstonLibrary.setBackgroundColor(OGButtonBackgroundColor);
        button_AstonLibrary.setTextColor(OGButtonTextColor);

        button_AstonSU.setBackgroundColor(OGButtonBackgroundColor);
        button_AstonSU.setTextColor(OGButtonTextColor);

        AddBackAllMarkers.setBackgroundColor(OGButtonBackgroundColor);
        AddBackAllMarkers.setTextColor(OGButtonTextColor);

        button_shop.setBackgroundColor(OGButtonBackgroundColor);
        button_shop.setTextColor(OGButtonTextColor);

        button_Aston.setBackgroundColor(OGButtonBackgroundColor);
        button_Aston.setTextColor(OGButtonTextColor);

        button_Mosque.setBackgroundColor(OGButtonBackgroundColor);
        button_Mosque.setTextColor(OGButtonTextColor);

    }

    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

//        mMap.getMyLocation();



        //adds all the markers stored in the the add marker method
         addMarkers();

         // will set the bounds to the preset camara settings
         camera();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
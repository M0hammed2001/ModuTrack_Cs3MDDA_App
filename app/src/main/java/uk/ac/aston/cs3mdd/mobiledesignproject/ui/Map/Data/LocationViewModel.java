package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Map.Data;

import android.location.Location;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LocationViewModel extends ViewModel {
    private MutableLiveData<Location> currentLocation;

    private LocationViewModel() {
        super();
        currentLocation = new MutableLiveData<>(null);
    }

    public LiveData<Location> getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location mCurrentLocation) {
        this.currentLocation.setValue(mCurrentLocation);
    }
}

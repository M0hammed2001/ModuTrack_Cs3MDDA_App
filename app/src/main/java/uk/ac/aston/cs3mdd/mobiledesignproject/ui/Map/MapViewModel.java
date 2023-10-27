package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MapViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This will show you the map of the university fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
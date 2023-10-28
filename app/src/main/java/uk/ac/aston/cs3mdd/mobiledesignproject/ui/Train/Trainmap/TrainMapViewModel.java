package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.Trainmap;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrainMapViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TrainMapViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This will show you the map from where to get to of the university fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
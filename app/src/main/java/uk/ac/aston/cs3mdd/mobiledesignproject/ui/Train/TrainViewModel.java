package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrainViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TrainViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Train information fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
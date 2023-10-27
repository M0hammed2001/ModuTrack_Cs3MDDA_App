package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ModuleViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ModuleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This Displays Module information, and allows you to track it fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
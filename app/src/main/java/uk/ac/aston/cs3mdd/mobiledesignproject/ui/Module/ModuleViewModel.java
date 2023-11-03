package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.information.DataSource;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.information.ModuleInformation;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.information.MyApplication;

public class ModuleViewModel extends ViewModel {


    private MutableLiveData<ModuleInformation> currentModule;

    public LiveData<ModuleInformation> getCurrentModule() {
        return currentModule;
    }

    public ModuleViewModel() {
        super();
        currentModule = new MutableLiveData<ModuleInformation>();
        setModuleData(); // only for dummy data
    }

    private void setModuleData() {
        currentModule.setValue(DataSource.getInstance(MyApplication.getAppContext()).getRealModule());
    }


}
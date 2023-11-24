package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;


public class ModuleViewModel extends ViewModel {

    private MutableLiveData<List<Module>> AllModules;

    public ModuleViewModel() {
        super();
        AllModules = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<Module>> getAllModules() {return AllModules;}

    public void updateModule(List<Module> modules) {
        this.AllModules.getValue().addAll(modules);
        this.AllModules.setValue(this.AllModules.getValue());
    }

//    public void deleteModule(List<Module> modules) {
//        this.AllModules.getValue().remove(modules);
//        this.AllModules.setValue(this.AllModules.getValue());
//    }









}
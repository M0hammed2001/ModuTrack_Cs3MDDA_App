package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.service.TrainRepository;

public class TrainsViewModel extends ViewModel {
    private MutableLiveData<List<TrainService>> allTrains;

    public TrainsViewModel() {
        super();
        allTrains = new MutableLiveData<>(new ArrayList<>());
    }

    public LiveData<List<TrainService>> getAllTrains() {
        return allTrains;
    }

    public void addAll(TrainList list) {
        allTrains.getValue().addAll(list.getResults());
        allTrains.setValue(allTrains.getValue());
        Log.i("MS", "Printing " + allTrains.getValue().size() + " Trains");
        for (TrainService train : allTrains.getValue()) {
            Log.i("MS", train.toString());
        }
    }

    public void requestRandomTrains(TrainRepository trainRepository) {
        if (allTrains.getValue().size() == 0) {
            Call<TrainList> TrainCall = trainRepository.getListOfTrains(30, "gb");
            TrainCall.enqueue(new Callback<TrainList>() {
                @Override
                public void onResponse(Call<TrainList> call, Response<TrainList> response) {
                    if (response.isSuccessful()) {
                        Log.i("MS", response.body().toString());
                        addAll(response.body());
                    }
                }

                    @Override
                    public void onFailure(Call<TrainList> call, Throwable t) {
                        // show error message to user
                        Log.i("MS", "Error: " + t.toString());
                    }
            });
        } else {
            Log.i("MS", "list of Trains has been provided, unfortunately you will not receive any more");
        }
    }


}

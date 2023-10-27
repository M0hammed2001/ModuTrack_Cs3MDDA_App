package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.service;

import retrofit2.Call;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainList;
//import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.Train;

public class TrainRepository {
    private RandomTrain randomTrainService;

    public TrainRepository(RandomTrain TrainService) {
        this.randomTrainService = TrainService;
    }

    public  Call<TrainList> getListOfTrains(int results, String nat) {
        return randomTrainService.getTrains(results, nat);
    }
}

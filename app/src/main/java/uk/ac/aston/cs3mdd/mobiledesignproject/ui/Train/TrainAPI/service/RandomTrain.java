package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainList;

public interface RandomTrain {

    @GET("/all/bhm?expand=true")
    Call<TrainList> getTrains(@Query("trainServices") Integer trainServices, @Query("areServicesAvailable") String areServicesAvailable);


}

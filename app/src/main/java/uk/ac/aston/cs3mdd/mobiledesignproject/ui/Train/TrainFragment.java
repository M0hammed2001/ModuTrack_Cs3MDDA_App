package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentTrainBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainListAdapter;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainService;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.TrainsViewModel;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.service.RandomTrain;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI.service.TrainRepository;

public class TrainFragment extends Fragment {

    private FragmentTrainBinding binding;

    private TrainsViewModel viewModel;

    private RecyclerView mRecyclerView;
    private TrainListAdapter mAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new TrainListAdapter(getContext(), viewModel.getAllTrains().getValue());
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://huxley2.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RandomTrain service = retrofit.create(RandomTrain.class);

        viewModel.requestRandomTrains(new TrainRepository(service));
        final Observer<List<TrainService>> TrainListObserver = new Observer<List<TrainService>>() {
            @Override
            public void onChanged(@Nullable final List<TrainService> Trainlist) {
                // Update the UI, in this case, a Toast.
//                Toast.makeText(getContext(),
//                        "We got a list of " + Trainlist.size() + " Trains",
//                        Toast.LENGTH_LONG).show();

                // Update the UI
                mAdapter.updateData(Trainlist);
            }
        };

        viewModel.getAllTrains().observe(getViewLifecycleOwner(), TrainListObserver);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TrainViewModel trainViewModel = new ViewModelProvider(this).get(TrainViewModel.class);

        viewModel = new ViewModelProvider(requireActivity()).get(TrainsViewModel.class);

        binding = FragmentTrainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
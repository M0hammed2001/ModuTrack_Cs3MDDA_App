package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.Module;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainFragmentDirections;

public class TrainListAdapter extends RecyclerView.Adapter<TrainListAdapter.TrainViewHolder> {

    private List<TrainService> mTrainList;
        private final LayoutInflater mInflater;



        public TrainListAdapter(Context context, List<TrainService> TrainList) {
            mInflater = LayoutInflater.from(context);
            this.mTrainList = TrainList;
        }


    @NonNull
        @Override
        public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View mItemView = mInflater.inflate(R.layout.train_information,
                    parent, false);
            return new TrainViewHolder(mItemView, this);
        }



    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
        TrainService trainService = mTrainList.get(position);
        holder.trainService = trainService;


        List<Destination> destinations = trainService.getDestination();

                    // Handle the case when data is not null and the CRS is "BHM"
                    String operator = trainService.getOperator();
                    String etd = trainService.getEtd();
                    String std = trainService.getStd();
                    String nrccMessages = trainService.getNrccMessages();
                    String DestinationText = destinations.toString();

                    // Handle null values
                    DestinationText = (DestinationText != null) ? DestinationText : "No Destination";
                    operator = (operator != null) ? operator : "Not available";
                    std = (std != null) ? std : "TBC";
                    etd = (etd != null) ? etd : "no delays";
                    nrccMessages = (nrccMessages != null) ? nrccMessages : "No ongoing issues";

                holder.DestinationText.setText(Html.fromHtml(DestinationText));

                holder.TrainTimeText.setText(Html.fromHtml(std));

                holder.OperatorText.setText(Html.fromHtml(operator));

                holder.DelaysText.setText(Html.fromHtml(etd));

        }


        @Override
        public int getItemCount() {
            return this.mTrainList.size();
        }

        public void updateData(List<TrainService> list) {
            this.mTrainList = list;
            notifyDataSetChanged();
        }

        class TrainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//            public final TextView TraindestinationView;
            public final TextView  DestinationText, TrainTimeText, OperatorText, DelaysText;

            final TrainListAdapter mAdapter;
            public TrainService trainService;
            Button ButtonTrainInformation;


            public TrainViewHolder(@NonNull View itemView, TrainListAdapter adapter) {
                super(itemView);
                itemView.setOnClickListener(this);
//                TraindestinationView = itemView.findViewById(R.id.traininformation);
                ButtonTrainInformation = itemView.findViewById(R.id.ButtonTrainInformation);

                DestinationText = itemView.findViewById(R.id.DestinationText);

                TrainTimeText = itemView.findViewById(R.id.TrainTimeText);

                OperatorText = itemView.findViewById(R.id.OperatorText);

                DelaysText = itemView.findViewById(R.id.DelaysText);

                this.mAdapter = adapter;

                ButtonTrainInformation.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("MS", "You Selected " + trainService.toString());
                        TrainFragmentDirections.ActionNavTrainToNavTrainmap action = TrainFragmentDirections.actionNavTrainToNavTrainmap(trainService);
                        Navigation.findNavController(v).navigate(action);

                        Log.i("MS", "Module Clicked");
                    }
                });

            }




            @Override
            public void onClick(View view) {
                // this will make it so on click of the text it will display the text

                    // Log.i("MS", "You Selected " + trainService.toString());
                    // TrainFragmentDirections.ActionNavTrainToNavTrainmap action = TrainFragmentDirections.actionNavTrainToNavTrainmap(trainService);
                    // Navigation.findNavController(view).navigate(action);


            }
        }

}
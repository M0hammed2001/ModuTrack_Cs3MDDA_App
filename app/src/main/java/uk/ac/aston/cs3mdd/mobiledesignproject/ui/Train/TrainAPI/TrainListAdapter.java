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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
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
                    String destinationText = destinations.toString();

                    // Handle null values
                    destinationText = (destinationText != null) ? destinationText : "No Destination";
                    operator = (operator != null) ? operator : "Not available";
                    std = (std != null) ? std : "TBC";
                    etd = (etd != null) ? etd : "no delays";
                    nrccMessages = (nrccMessages != null) ? nrccMessages : "No ongoing issues";

                    //displays the message in the text field
                    holder.DestinationText.setText(Html.fromHtml(destinationText));
                    holder.TrainTimeText.setText(Html.fromHtml(std));
                    holder.OperatorText.setText(Html.fromHtml(operator));
                    holder.DelaysText.setText(Html.fromHtml(etd));
                    holder.NRCmessageText.setText(Html.fromHtml(nrccMessages));
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
            public final TextView  DestinationText, TrainTimeText, OperatorText, DelaysText, NRCmessageText;

            final TrainListAdapter mAdapter;
            public TrainService trainService;
            Button ButtonTrainInformation;


            public TrainViewHolder(@NonNull View itemView, TrainListAdapter adapter) {
                super(itemView);
                itemView.setOnClickListener(this);
                ButtonTrainInformation = itemView.findViewById(R.id.ButtonTrainInformation);
                DestinationText = itemView.findViewById(R.id.DestinationText);
                TrainTimeText = itemView.findViewById(R.id.TrainTimeText);
                OperatorText = itemView.findViewById(R.id.OperatorText);
                DelaysText = itemView.findViewById(R.id.DelaysText);
                NRCmessageText = itemView.findViewById(R.id.NRCmessageText);

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
                //builds the String that will display on the
                StringBuilder sb = new StringBuilder();
                    for(TrainService m : mTrainList){
                        sb.append(m.getDestination()+" : "+m.getStd());
                        sb.append("\n");
                    }
                String TrainPopup = sb.toString();
                    //will display the Finaldata Sring in a format
                Snackbar.make(view, TrainPopup, Snackbar.LENGTH_LONG).setAction("Action", null).show();

                     Log.i("MS", "You Selected " + trainService.toString());

            }
        }

}
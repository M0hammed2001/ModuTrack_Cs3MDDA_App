package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;



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

                    // Handle null values
                    operator = (operator != null) ? operator : "Not available";
                    std = (std != null) ? std : "TBC";
                    etd = (etd != null) ? etd : "no delays";
                    nrccMessages = (nrccMessages != null) ? nrccMessages : "No ongoing issues";

                    // Create the text with line breaks
                    String displayText = "Destination: " + destinations.toString() + "<br>" +
                            "<br>" +
                            "Operator: " + operator + "<br>" +
                            "<br>" +
                            "Departure: " + std + "<br>" +
                            "<br>" +
                            "Delays: " + etd + "<br>" +
                            "<br>" +
                            "NRCC Messages: " + nrccMessages;

                    // Set the text with line breaks in the TextView if the build version supports it
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        holder.TraindestinationView.setText(Html.fromHtml(displayText, Html.FROM_HTML_MODE_LEGACY));
                    } else {
                        holder.TraindestinationView.setText(Html.fromHtml(displayText));
                    }

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
            public final TextView TraindestinationView;
            final TrainListAdapter mAdapter;
            public TrainService trainService;

            public TrainViewHolder(@NonNull View itemView, TrainListAdapter adapter) {
                super(itemView);
                itemView.setOnClickListener(this);
                TraindestinationView = itemView.findViewById(R.id.traininformation);
                this.mAdapter = adapter;

            }

            @Override
            public void onClick(View view) {
                Log.i("MS", "You Selected " + trainService.toString());
                TrainFragmentDirections.ActionNavTrainToNavTrainmap action = TrainFragmentDirections.actionNavTrainToNavTrainmap(trainService);
                Navigation.findNavController(view).navigate(action);


            }
        }

}
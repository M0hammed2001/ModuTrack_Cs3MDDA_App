package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainAPI;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;

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

        @Override
        public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {
            TrainService trainService = mTrainList.get(position);
            holder.trainService = trainService;
//            Destination destination =  trainService.getAreServicesAvailable();
            String displaydestination = trainService.getDestination() + " " + trainService.getOperator()+ " " + trainService.getEtd()+ " " + trainService.getStd() + " " + trainService.getNrccMessages();
            holder.TraindestinationView.setText(displaydestination);
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
                TraindestinationView = itemView.findViewById(R.id.username);
                this.mAdapter = adapter;
            }

            @Override
            public void onClick(View view) {
                Log.i("MS", "You Selected " + trainService.toString());
            }
        }

}
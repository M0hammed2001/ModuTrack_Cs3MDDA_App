package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentTrainBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleViewModel;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Train.TrainFragmentDirections;


public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ModuleViewModel>{
    private FragmentModuleBinding binding;
    private List<Module> mModuleList;
    private final LayoutInflater mInflater;


    public ModuleListAdapter(Context context, List<Module> ModuleList) {
        mInflater = LayoutInflater.from(context);
        this.mModuleList = ModuleList;
    }

    @NonNull
    @Override
    public ModuleListAdapter.ModuleViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.module_information,
                parent, false);
        return new ModuleListAdapter.ModuleViewModel(mItemView, this);
    }



    public void onBindViewHolder(@NonNull ModuleListAdapter.ModuleViewModel holder, int position) {


    }


    @Override
    public int getItemCount() {
        return this.mModuleList.size();
    }

    public void updateData(List<Module> list) {
        this.mModuleList = list;
        notifyDataSetChanged();
    }

    class ModuleViewModel extends RecyclerView.ViewHolder implements View.OnClickListener{
        public final TextView ModuleView;
        final ModuleListAdapter mAdapter;
        public Module modules;

        public ModuleViewModel(@NonNull View itemView, ModuleListAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);
            ModuleView = itemView.findViewById(R.id.Moduleinformation);
            this.mAdapter = adapter;

        }

        @Override
        public void onClick(View view) {
            Log.i("MS", "You Selected " + modules.toString());
//            TrainFragmentDirections.ActionNavTrainToNavTrainmap action = TrainFragmentDirections.actionNavTrainToNavTrainmap(modules);
//            Navigation.findNavController(view).navigate(action);

        }
    }
}

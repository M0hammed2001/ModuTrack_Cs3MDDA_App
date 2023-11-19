package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentModuleBinding;




public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>{
    private FragmentModuleBinding binding;
    private List<Module> mModuleList;
    private final LayoutInflater mInflater;


    public ModuleListAdapter(Context context, List<Module> ModuleList) {
        mInflater = LayoutInflater.from(context);
        this.mModuleList = ModuleList;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.module_item, parent, false);
        return new ModuleViewHolder(mItemView, this);
    }



    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        Module module = mModuleList.get(position);
        holder.modules = module;

        // Handle the case when data is not null and the CRS is "BHM"
        String moduleCode = module.getModuleCode();
        String moduleName = module.getModuleName();

        String ExamName = module.getExamName();
        String ExamDate = module.getExamdate();

        String AssignmentName = module.getAssignmentName();
        String AssignmentDate = module.getAssignmentDate();


        // Handle null values
        moduleCode = (moduleCode != null) ? moduleCode : "Module Code";
        moduleName = (moduleName != null) ? moduleName : "Module Name";


        ExamName = (ExamName != null) ? ExamName : "Exam Name";
        ExamDate = (ExamDate != null) ? ExamDate : "Exam Date";

        AssignmentName = (AssignmentName != null) ? AssignmentName : "Assignment Name";
        AssignmentDate = (AssignmentDate != null) ? AssignmentDate : "Assignment Date";




        // Create the text with line breaks
        String displayText =
                "Module: " + moduleCode + "<br>" + moduleName +
                "<br>" +
                "Assignment: " + ExamName + "<br>" + ExamDate +
                "<br>" +
                "Exam: " + AssignmentName + "<br>" + AssignmentDate;
//                "<br>" +
//                "Room: " + module. ;




            holder.ModuleView.setText(Html.fromHtml(displayText));


    }


    @Override
    public int getItemCount() {
        return this.mModuleList.size();
    }

    public void updateData(List<Module> list) {
        this.mModuleList = list;
        notifyDataSetChanged();
    }

    class ModuleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView ModuleView;

        final ModuleListAdapter mAdapter;
        public Module modules;

        public ModuleViewHolder(@NonNull View itemView, ModuleListAdapter adapter) {
            super(itemView);
            itemView.setOnClickListener(this);
            ModuleView = itemView.findViewById(R.id.Moduleinformation);
            this.mAdapter = adapter;

        }

        @Override
        public void onClick(View view) {

//            Log.i("MS", "You Selected " + modules.toString());
//            PopupAddModuleBinding.action = TrainFragmentDirections.actionNavTrainToNavTrainmap(modules);
//            Navigation.findNavController(view).navigate(action);

        }
    }


}

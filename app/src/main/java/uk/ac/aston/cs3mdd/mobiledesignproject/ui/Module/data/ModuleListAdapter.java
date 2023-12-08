package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;

import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleFragment;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleFragmentDirections;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleViewModel;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.managedata.ModuleEditFragment;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.managedata.ModuleEditFragmentDirections;



public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>{

    ModuleDatabase moduleDB;
//    private PopupEditModuleBinding binding;

    private List<Module> mModuleList;
    private final LayoutInflater mInflater;

    private OnDeleteClickListener listener;

    public ModuleListAdapter(Context context, List<Module> ModuleList, OnDeleteClickListener listener) {
        mInflater = LayoutInflater.from(context);
        this.mModuleList = ModuleList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ModuleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.module_item, parent, false);
        return new ModuleViewHolder(mItemView, this);
    }

    public void setModuleDB(ModuleDatabase moduleDB) {
        this.moduleDB = moduleDB;
    }

    public void onBindViewHolder(@NonNull ModuleViewHolder holder, int position) {
        Module module = mModuleList.get(position);
        holder.modules = module;

        // Handle the case when data is not null and the CRS is "BHM"
        String moduleCode = module.getModuleCode();
        String moduleName = module.getModuleName();

        String AssignmentName = module.getAssignmentName();
        String AssignmentDate = module.getAssignmentDate();

        String ExamName = module.getExamName();
        String ExamDate = module.getExamdate();

        String lectureRoom = module.getLectureRoom();
        String tutorialRoom = module.getTutorialRoom();

        // Handle null values
//        moduleCode = (moduleCode != null) ? moduleCode : "Module Code";
//        moduleName = (moduleName != null) ? moduleName : "Module Name";


//        ExamName = (ExamName != null) ? ExamName : "Exam Name";
//        ExamDate = (ExamDate != null) ? ExamDate : "Exam Date";
//
//        AssignmentName = (AssignmentName != null) ? AssignmentName : "Assignment Name";
//        AssignmentDate = (AssignmentDate != null) ? AssignmentDate : "Assignment Date";

        lectureRoom = (lectureRoom != null) ? lectureRoom : "Room details Unavailable";
        tutorialRoom = (tutorialRoom != null) ? tutorialRoom : "Room details Unavailable";


        holder.ModuleNameText.setText(Html.fromHtml(moduleName));
        holder.ModuleCodeText.setText(Html.fromHtml(moduleCode));

        holder.AssignmentDateText.setText(Html.fromHtml(AssignmentDate));
        holder.AssignmentNameText.setText(Html.fromHtml(AssignmentName));

        holder.ExamDateText.setText(Html.fromHtml(ExamDate));
        holder.ExamNameText.setText(Html.fromHtml(ExamName));

        holder.LectureRoomText.setText(Html.fromHtml(lectureRoom));
        holder.TutorialRoomText.setText(Html.fromHtml(tutorialRoom));
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
        public final TextView  ModuleNameText, ModuleCodeText, AssignmentDateText, AssignmentNameText, ExamDateText, ExamNameText, LectureRoomText, TutorialRoomText;
        EditText FilterModule;
        final ModuleListAdapter mAdapter;
        public Module modules;
        public final Button FilterModuleButton;
        public final Button ButtonDeleteModule, ButtonEdit;


        public ModuleViewHolder(@NonNull View itemView, ModuleListAdapter adapter) {
            super(itemView);
            ModuleNameText = itemView.findViewById(R.id.ModuleNameText);
            ModuleCodeText = itemView.findViewById(R.id.ModuleCodeText);

            AssignmentDateText = itemView.findViewById(R.id.AssignmentDateText);
            AssignmentNameText = itemView.findViewById(R.id.AssignmentNameText);

            ExamDateText = itemView.findViewById(R.id.ExamDateText);
            ExamNameText = itemView.findViewById(R.id.ExamNameText);

            LectureRoomText = itemView.findViewById(R.id.LectureRoomText);
            TutorialRoomText = itemView.findViewById(R.id.TutorialRoomText);
            
            
            ButtonDeleteModule = itemView.findViewById(R.id.ButtonDeleteModule);
            ButtonEdit = itemView.findViewById(R.id.ButtonEdit);

            FilterModule = itemView.findViewById(R.id.FilterModule);
            FilterModuleButton = itemView.findViewById(R.id.FilterModuleButton);

            this.mAdapter = adapter;

            ButtonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Calling all fields
                    ModuleFragmentDirections.ActionModuleToModuleEdit action = ModuleFragmentDirections.actionModuleToModuleEdit(modules);
                    Navigation.findNavController(view).navigate(action);

                    Log.i("MS", "Module Clicked");
//                    UpdateModuleInBackground(modules);

                }
            });

            ButtonDeleteModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //used the delete method i created to delete the specified module from the database
                    //DeleteModuleInBackground(modules);
                    listener.onDeleteClick(modules);
                    Log.i("MS", "Deleted");
                }
            });


//            FilterModuleButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v2) {
////                    FilterModule =
//                    Log.i("MS", "Data Filtered");
//                }
//            });







        }




        @Override
        public void onClick(View view) {

            Log.i("MS", "You Selected " + modules.toString());
//            AddModuleBinding.action = TrainFragmentDirections.actionNavTrainToNavTrainmap(modules);
//            Navigation.findNavController(view).navigate(action);

        }
    }


}

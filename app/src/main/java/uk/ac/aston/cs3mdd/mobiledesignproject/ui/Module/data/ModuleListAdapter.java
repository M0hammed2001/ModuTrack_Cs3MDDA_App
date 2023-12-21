package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data;

import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleFragmentDirections;


public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>{

    ModuleDatabase moduleDB;

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
        String moduleCode = module.getModuleCode().trim();
        String moduleName = module.getModuleName().trim();

        String AssignmentName1 = module.getAssignmentName1().trim();
        String AssignmentDate1 = module.getAssignmentDate1().trim();

        String AssignmentName2 = module.getAssignmentName2().trim();
        String AssignmentDate2 = module.getAssignmentDate2().trim();

        String ExamName = module.getExamName().trim();
        String ExamDate = module.getExamdate();

        String lectureRoom = module.getLectureRoom().trim();
        String tutorialRoom = module.getTutorialRoom().trim();


        holder.ModuleNameText.setText(Html.fromHtml(moduleName));
        holder.ModuleCodeText.setText(Html.fromHtml(moduleCode));

        if(AssignmentName1.isEmpty()){
            Log.i("MS","Assignment 1 empty");
            holder.AssignmentName1Label.setVisibility(View.GONE);
            holder.AssignmentDate1Label.setVisibility(View.GONE);
            holder.Assingment1.setVisibility(View.GONE);
        }else {
//            String D1 = AssignmentDate1.toString();
//            String N1 = AssignmentName1.toString();
//            Toast.makeText(mInflater.getContext(), "failed Ass 1: "+  D1 +" and " + N1 , Toast.LENGTH_LONG).show();
            holder.AssignmentDate1Text.setText(Html.fromHtml(AssignmentDate1));
            holder.AssignmentName1Text.setText(Html.fromHtml(AssignmentName1));
        }


        if(AssignmentName2.isEmpty()){
            Log.i("MS","Assignment 2 empty");
            holder.AssignmentName2Label.setVisibility(View.GONE);
            holder.AssignmentDate2Label.setVisibility(View.GONE);
            holder.Assingment2.setVisibility(View.GONE);
        }else {
            holder.AssignmentDate2Text.setText(Html.fromHtml(AssignmentDate2));
            holder.AssignmentName2Text.setText(Html.fromHtml(AssignmentName2));
        }

        if(ExamName.isEmpty()){
            Log.i("MS","Exam is empty");
            holder.Exam.setVisibility(View.GONE);
            holder.ExamNameLabel.setVisibility(View.GONE);
            holder.ExamDateLabel.setVisibility(View.GONE);
            holder.ExamNameText.setVisibility(View.GONE);
            holder.ExamDateText.setVisibility(View.GONE);
        }else {
            holder.ExamDateText.setText(Html.fromHtml(ExamDate));
            holder.ExamNameText.setText(Html.fromHtml(ExamName));
        }



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
        public final TextView  ModuleNameText, ModuleCodeText, AssignmentDate1Text, AssignmentName1Text, AssignmentDate2Text, AssignmentName2Text, ExamDateText, ExamNameText, LectureRoomText, TutorialRoomText, Assingment2,AssignmentDate2Label, AssignmentName2Label, Assingment1,AssignmentDate1Label, AssignmentName1Label, Exam, ExamDateLabel, ExamNameLabel;
        final ModuleListAdapter mAdapter;
        public Module modules;
        public final Button ButtonDeleteModule, ButtonEdit;


        public ModuleViewHolder(@NonNull View itemView, ModuleListAdapter adapter) {
            super(itemView);
            ModuleNameText = itemView.findViewById(R.id.ModuleNameText);
            ModuleCodeText = itemView.findViewById(R.id.ModuleCodeText);

            AssignmentDate1Text = itemView.findViewById(R.id.AssignmentDate1Text);
            AssignmentName1Text = itemView.findViewById(R.id.AssignmentName1Text);

            AssignmentDate2Text = itemView.findViewById(R.id.AssignmentDate2Text);
            AssignmentName2Text = itemView.findViewById(R.id.AssignmentName2Text);

            //assignment 2 Labels
            Assingment2 = itemView.findViewById(R.id.Assingment2);
            AssignmentDate2Label = itemView.findViewById(R.id.AssignmentDate2Label);
            AssignmentName2Label = itemView.findViewById(R.id.AssignmentName2Label);

            //assignment 1 Labels
            Assingment1 = itemView.findViewById(R.id.Assingment1);
            AssignmentDate1Label = itemView.findViewById(R.id.AssignmentDate1Label);
            AssignmentName1Label = itemView.findViewById(R.id.AssignmentName1Label);

            ExamDateText = itemView.findViewById(R.id.ExamDateText);
            ExamNameText = itemView.findViewById(R.id.ExamNameText);
            Exam = itemView.findViewById(R.id.Exam);
            ExamDateLabel = itemView.findViewById(R.id.ExamDateLabel);
            ExamNameLabel = itemView.findViewById(R.id.ExamNameLabel);

            LectureRoomText = itemView.findViewById(R.id.LectureRoomText);
            TutorialRoomText = itemView.findViewById(R.id.TutorialRoomText);
            
            
            ButtonDeleteModule = itemView.findViewById(R.id.ButtonDeleteModule);
            ButtonEdit = itemView.findViewById(R.id.ButtonEdit);


            this.mAdapter = adapter;

            ButtonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Calling all field in that module view and navigating that information to the new fragment called ModuleEditFragment, similar to my API.
                    ModuleFragmentDirections.ActionModuleToModuleEdit action = ModuleFragmentDirections.actionModuleToModuleEdit(modules);
                    Navigation.findNavController(view).navigate(action);

                    Log.i("MS", "Module Clicked");

                }
            });

            ButtonDeleteModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //calls the delete function in the interface
                    listener.onDeleteClick(modules);
                    Log.i("MS", "Deleted");
                }
            });

        }

        @Override
        public void onClick(View view) {

            Log.i("MS", "You Selected " + modules.toString());

        }
    }


}

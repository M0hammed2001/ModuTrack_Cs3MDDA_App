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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import uk.ac.aston.cs3mdd.mobiledesignproject.R;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.FragmentModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.PopupAddModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.databinding.PopupEditModuleBinding;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleFragment;


public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>{

    ModuleDatabase moduleDB;
//    private PopupEditModuleBinding binding;

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


        // Handle null values
        moduleCode = (moduleCode != null) ? moduleCode : "Module Code";
        moduleName = (moduleName != null) ? moduleName : "Module Name";


        ExamName = (ExamName != null) ? ExamName : "Exam Name";
        ExamDate = (ExamDate != null) ? ExamDate : "Exam Date";

        AssignmentName = (AssignmentName != null) ? AssignmentName : "Assignment Name";
        AssignmentDate = (AssignmentDate != null) ? AssignmentDate : "Assignment Date";


//
        String ModuleCodeText = "Module:" + moduleCode + "<br>";
        String ModuleNameText = moduleName;



       String AssignmentDateText ="Assignment:" +  AssignmentDate + "<br>";
       String AssignmentNameText = AssignmentName;

       String ExamDateText ="Exam:" +  ExamDate + "<br>";
       String ExamNameText = ExamName;


        holder.ModuleNameText.setText(Html.fromHtml(ModuleNameText));
        holder.ModuleCodeText.setText(Html.fromHtml(ModuleCodeText));

        holder.AssignmentDateText.setText(Html.fromHtml(AssignmentDateText));
//        holder.AssignmentdueText.setText(Html.fromHtml(AssignmentdueText));
        holder.AssignmentNameText.setText(Html.fromHtml(AssignmentNameText));

        holder.ExamDateText.setText(Html.fromHtml(ExamDateText));
//        holder.ExamdueText.setText(Html.fromHtml(ExamdueText));
        holder.ExamNameText.setText(Html.fromHtml(ExamNameText));


        holder.ModuleNameText.setText(Html.fromHtml(ModuleNameText));

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
        public final TextView  ModuleNameText, ModuleCodeText, AssignmentDateText, AssignmentNameText, ExamDateText, ExamNameText;

        final ModuleListAdapter mAdapter;
        public Module modules;
        public final Button ButtonDeleteModule, ButtonEdit ;


        public ModuleViewHolder(@NonNull View itemView, ModuleListAdapter adapter) {
            super(itemView);
            ModuleNameText = itemView.findViewById(R.id.ModuleNameText);
            ModuleCodeText = itemView.findViewById(R.id.ModuleCodeText);

            AssignmentDateText = itemView.findViewById(R.id.AssignmentDateText);
//            AssignmentdueText = itemView.findViewById(R.id.AssignmentdueText);
            AssignmentNameText = itemView.findViewById(R.id.AssignmentNameText);

            ExamDateText = itemView.findViewById(R.id.ExamDateText);
//            ExamdueText = itemView.findViewById(R.id.ExamdueText);
            ExamNameText = itemView.findViewById(R.id.ExamNameText);
            
            
            ButtonDeleteModule = itemView.findViewById(R.id.ButtonDeleteModule);
            ButtonEdit = itemView.findViewById(R.id.ButtonEdit);


            this.mAdapter = adapter;



            ButtonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Calling all fields
                    String moduleName = modules.getModuleName();
                    String moduleCode = modules.getModuleCode();

                    String assignmentDate = modules.getAssignmentDate();
                    String assignmentdue = modules.getAssignmentdue();
                    String assignmentName = modules.getAssignmentName();

                    String examdate = modules.getExamdate();
                    String examdue = modules.getExamdue();
                    String examName = modules.getExamName();

                    // Updates the Module from the database using the UpdateInBackground task
                    Module updateModule = new Module(moduleName, moduleCode, assignmentName, assignmentdue, assignmentDate, examName, examdue, examdate);
                    UpdateModuleInBackground(updateModule);

                    Log.i("MS", "Updated");
                }
            });

            ButtonDeleteModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Calling all fields
//                String moduleName = modules.getModuleName();
//                String moduleCode = modules.getModuleCode();
//
//                String assignmentDate = modules.getAssignmentDate();
//                String assignmentDue = modules.getAssignmentdue();
//                String assignmentName = modules.getAssignmentName();
//
//                String examDate = modules.getExamdate();
//                String examDue = modules.getExamdue();
//                String examName = modules.getExamName();
//
////                    String ModuleID = modules.getId();
//
//                // Deletes the Module from the database using the delete background task
//                Module DeleteModule = new Module(moduleName, moduleCode, assignmentName, assignmentDue, assignmentDate, examName, examDate, examDue);
                    DeleteModuleInBackground(modules);

                    Log.i("MS", "Deleted");
                }
            });




        }

        public void DeleteModuleInBackground(Module module) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // Check if moduleDB is not null before accessing getModuleDAO()
//                if (moduleDB != null) {
                    // Background task
                    moduleDB.getModuleDAO().deleteModule(module);

                    // On finish task
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ButtonDeleteModule.getContext(), "Deleted From DataBase", Toast.LENGTH_LONG).show();
                        }
                    });
//                } else {
//                    // Log an error or handle the situation where moduleDB is null
//                    Log.e("DeleteModuleInBackground", "ModuleDatabase is null");
//                }
                }
            });

        }


        public void UpdateModuleInBackground(Module module) {
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Handler handler = new Handler(Looper.getMainLooper());

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    // Check if moduleDB is not null before accessing getModuleDAO()
                    if (moduleDB != null) {
                        // Background task
//                        moduleDB.getModuleDAO().updateModule(module);

                        // On finish task
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ButtonEdit.getContext(), "updated In DataBase", Toast.LENGTH_LONG).show();
                            }
                        });
                    } else {
                        // Log an error or handle the situation where moduleDB is null
                        Log.e("UpdateModuleInBackground", "ModuleDatabase is null");
                    }
                }
            });
        }


        @Override
        public void onClick(View view) {

            Log.i("MS", "You Selected " + modules.toString());
//            AddModuleBinding.action = TrainFragmentDirections.actionNavTrainToNavTrainmap(modules);
//            Navigation.findNavController(view).navigate(action);

        }
    }


}

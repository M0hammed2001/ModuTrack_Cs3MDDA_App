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
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.ModuleFragment;


public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ModuleViewHolder>{
    ModuleDatabase moduleDB;
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
        String ModuleInformation =
                "Module: " + moduleCode + "<br>" + moduleName +
                "<br>" +
                 "Exam: " + AssignmentName + "<br>" + AssignmentDate +
                "<br>" +
                "Assignment: " + ExamName + "<br>" + ExamDate ;
//                "<br>" +
//                "Room: " + module. ;




            holder.ModuleView.setText(Html.fromHtml(ModuleInformation));


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
        public final Button ButtonDeleteModule, ButtonEdit ;


        public ModuleViewHolder(@NonNull View itemView, ModuleListAdapter adapter) {
            super(itemView);
            ModuleView = itemView.findViewById(R.id.Moduleinformation);
            ButtonDeleteModule = itemView.findViewById(R.id.ButtonDeleteModule);
            ButtonEdit = itemView.findViewById(R.id.ButtonEdit);


            this.mAdapter = adapter;

            ButtonDeleteModule.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    DeleteModuleInBackground(modules);

                    Log.i("MS", "Deleted");
                }
            });

            ButtonEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    NavHostFragment.findNavController(ModuleFragment.this).navigate(R.id.action_module_to_moduleAdd);
                    Log.i("MS", "Edited");
                }
            });


        }

        public void DeleteModuleInBackground(Module module) {

            ExecutorService executorService = Executors.newSingleThreadExecutor();

            Handler handler = new Handler(Looper.getMainLooper());

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    //background task
                    moduleDB.getModuleDAO().deleteModule(module);
                    //on finish task
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            Toast.makeText(ButtonDeleteModule.getContext(), "Deleted From DataBase", Toast.LENGTH_LONG).show();

                        }
                    });
                }
            });
        }


        @Override
        public void onClick(View view) {

            Log.i("MS", "You Selected " + modules.toString());
//            PopupAddModuleBinding.action = TrainFragmentDirections.actionNavTrainToNavTrainmap(modules);
//            Navigation.findNavController(view).navigate(action);

        }
    }


}

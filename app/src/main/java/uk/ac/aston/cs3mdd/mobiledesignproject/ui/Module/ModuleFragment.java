package uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.AppDatabase;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.DataSource;
import uk.ac.aston.cs3mdd.mobiledesignproject.ui.Module.data.ModuleDao;


public class ModuleFragment extends Fragment {
    private DataSource dataSource;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize database and data source
        AppDatabase appDatabase = AppDatabase.getInstance(requireContext());
        ModuleDao moduleDao = appDatabase.moduleDao();
        dataSource = new DataSource(moduleDao);
    }

}
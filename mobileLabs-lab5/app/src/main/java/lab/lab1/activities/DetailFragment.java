package lab.lab1.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import lab.lab1.R;


public class DetailFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        TextView department = view.findViewById(R.id.student_department);
        TextView group = view.findViewById(R.id.student_group);
        TextView birth = view.findViewById(R.id.student_birth);
        TextView surname = view.findViewById(R.id.student_surname);
        TextView name = view.findViewById(R.id.student_name);
        getParentFragmentManager().setFragmentResultListener("selectedStudent", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                String[] result = bundle.getString("selectedStudent").split(" ");
                surname.setText(result[0]);
                name.setText(result[1]);
                department.setText(result[2]);
                group.setText(result[3]);
                birth.setText(result[4]);
            }
        });
        return view;
    }
}
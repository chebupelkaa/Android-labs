package lab.lab1.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lab.lab1.Contract;
import lab.lab1.OnItemClickListener;
import lab.lab1.R;
import lab.lab1.Student;
import lab.lab1.StudentAdapter;
import lab.lab1.presenters.StudentPresenter;

public class StudentFragment extends Fragment implements Contract.View.MainView, Find, OnItemClickListener {
    private RecyclerView recyclerView;
    public Contract.Presenter.MainPresenter presenter;
    private StudentAdapter adapter;
    public Student selectedStudent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student, container, false);
        presenter = new StudentPresenter(this);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        presenter.showAll();
        return view;
    }

    @Override
    public void showAllStudents(List<Student> students) {
        adapter = new StudentAdapter(students, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showStudentsByDepartment(List<Student> students) {
        adapter = new StudentAdapter(students, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void find(String criteria) {
        presenter.onCriteriaEnter(criteria);
    }

    @Override
    public void onItemClick(Student student) {
        selectedStudent = student;
        Bundle bundle = new Bundle();
        bundle.putString("selectedStudent", selectedStudent.toString());
        getParentFragmentManager().setFragmentResult("selectedStudent", bundle);

        Log.d("StudentFragment", "Item clicked: " + student.toString());
    }
}
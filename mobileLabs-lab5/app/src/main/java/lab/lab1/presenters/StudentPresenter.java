package lab.lab1.presenters;

import android.content.Intent;

import java.io.Serializable;
import java.util.stream.Collectors;

import lab.lab1.Contract;
import lab.lab1.Model;
import lab.lab1.Student;
import lab.lab1.activities.AddStudentActivity;
import lab.lab1.activities.EditStudentActivity;
import lab.lab1.activities.MainActivity;
import lab.lab1.activities.StudentFragment;

public class StudentPresenter implements Contract.Presenter.MainPresenter {
    private StudentFragment fragment;
    private Contract.Model model;
    private String criteria;

    public StudentPresenter(StudentFragment fragment) {
        this.fragment = fragment;
        model = Model.getModel(fragment.getActivity());
    }

    @Override
    public void onCriteriaEnter(String criteria) {
        this.criteria = criteria;
    }

    @Override
    public void showAll() {
        model.getAll().observe(fragment.getActivity(), s ->
                fragment.showAllStudents(s)
        );
    }

    @Override
    public void onAddStudentClick() {
        fragment.startActivity(new Intent(fragment.getActivity(), AddStudentActivity.class));
    }

    @Override
    public void onEditStudentClick(Student student) {
        fragment.startActivity(new Intent(fragment.getActivity(), EditStudentActivity.class).putExtra("student", (Serializable) student));
    }

    @Override
    public void onDeleteStudentClick(Student student) {
        model.delete(student);
        showAll();
    }

    @Override
    public void onStudentsByDepartmentClick() {
        ((MainActivity) fragment.getActivity()).showProgressBar();
        model.getByDepartment(criteria).observe(fragment.getActivity(), s ->
                fragment.showStudentsByDepartment(s)
        );
    }

    @Override
    public void onStudentsByBirthClick() {
        ((MainActivity) fragment.getActivity()).showProgressBar();
        model.getAll().observe(fragment.getActivity(), s ->
                fragment.showStudentsByDepartment(s
                        .stream()
                        .filter(d -> d.getBirth().matches(".*" + criteria))
                        .collect(Collectors.toList()))
        );
    }
}

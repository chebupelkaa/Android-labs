package lab.lab1.presenters;

import androidx.lifecycle.Observer;

import java.util.List;

import lab.lab1.Contract;
import lab.lab1.Model;
import lab.lab1.Student;
import lab.lab1.activities.AddStudentActivity;

public class AddPresenter implements Contract.Presenter.AddPresenter {
    private AddStudentActivity activity;
    private Contract.Model model;

    public AddPresenter(AddStudentActivity activity) {
        this.activity = activity;
        this.model = Model.getModel(activity);
    }

    @Override
    public void onAddStudent(Student student) {
        model.add(student);
        activity.onClose();
    }


    @Override
    public void getDepartments() {
        model.getDistinctDepartments().observe(activity, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                activity.onDepartmentLoad(strings);
            }
        });
    }

    @Override
    public void getGroups() {
        model.getDistinctGroups().observe(activity, new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                activity.onGroupLoad(strings);
            }
        });
    }
}

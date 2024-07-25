package lab.lab1.presenters;

import androidx.lifecycle.Observer;

import java.util.List;

import lab.lab1.Contract;
import lab.lab1.Model;
import lab.lab1.Student;
import lab.lab1.activities.EditStudentActivity;

public class EditPresenter implements Contract.Presenter.EditPresenter {
    private EditStudentActivity activity;
    private Contract.Model model;

    public EditPresenter(EditStudentActivity activity) {
        this.activity = activity;
        this.model = Model.getModel(activity);
    }

    @Override
    public void onEditStudent(Student student) {
        model.edit(student);
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

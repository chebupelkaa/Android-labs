package lab.lab1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import lab.lab1.Contract;
import lab.lab1.R;
import lab.lab1.Student;
import lab.lab1.presenters.AddPresenter;
import lab.lab1.presenters.CallBack;

public class AddStudentActivity extends AppCompatActivity implements Contract.View.AddView, CallBack.DepartmentCallBack, CallBack.GroupCallBack {
    private Contract.Presenter.AddPresenter presenter;
    private TextView surname, name, birth;
    private AutoCompleteTextView department, group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        presenter = new AddPresenter(this);
        presenter.getDepartments();
        presenter.getGroups();

        surname = findViewById(R.id.surname);
        name = findViewById(R.id.name);
        birth = findViewById(R.id.birth);
        department = findViewById(R.id.department);
        group = findViewById(R.id.group);

        findViewById(R.id.add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addStudent();
            }
        });
    }

    @Override
    public void addStudent() {
        presenter.onAddStudent(new Student(surname.getText().toString(),
                name.getText().toString(),
                department.getText().toString(),
                group.getText().toString(),
                birth.getText().toString()));
    }

    @Override
    public void onClose() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onDepartmentLoad(List<String> data) {
        ArrayAdapter<String> departmentsAdapter = new ArrayAdapter<>(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, data);
        department.setAdapter(departmentsAdapter);
    }

    @Override
    public void onGroupLoad(List<String> data) {
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<>(this, androidx.constraintlayout.widget.R.layout.support_simple_spinner_dropdown_item, data);
        group.setAdapter(groupAdapter);
    }
}
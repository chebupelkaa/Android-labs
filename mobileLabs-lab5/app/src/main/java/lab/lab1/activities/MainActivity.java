package lab.lab1.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import lab.lab1.PagerAdapter;
import lab.lab1.R;

public class MainActivity extends AppCompatActivity implements Find {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ProgressDialog progressDialog;
    private StudentFragment studentFragment;
    private InputDialog yearDialog, departmentDialog;
    private AlertDialog.Builder deleteDialog;
    private String command;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Themes.setTheme(this);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);

        tabLayout.setupWithViewPager(viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        studentFragment = new StudentFragment();
        pagerAdapter.addFragment(studentFragment, getResources().getString(R.string.student_tab));
        pagerAdapter.addFragment(new DetailFragment(), getResources().getString(R.string.detail_tab));
        pagerAdapter.addFragment(new MapsFragment(), getResources().getString(R.string.map_tab));
        viewPager.setAdapter(pagerAdapter);

        Bundle bundle1 = new Bundle();
        Bundle bundle2 = new Bundle();
        bundle1.putString("title", "Введите год");
        bundle2.putString("title", "Введите тариф");
        yearDialog = new InputDialog();
        departmentDialog = new InputDialog();
        yearDialog.setArguments(bundle1);
        departmentDialog.setArguments(bundle2);

        deleteDialog = new AlertDialog.Builder(this);
        deleteDialog.setTitle("удалить????")
                .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        studentFragment.presenter.onDeleteStudentClick(studentFragment.selectedStudent);
                    }
                })
                .setNegativeButton("Нет", null)
                .create();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Загрузка");
        progressDialog.setMessage("Происходит что-то страшное");
        progressDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
    }

    public void showProgressBar() {
//        progressDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.find || id == R.id.themes)
            return true;
        else if (id == R.id.all) {
            studentFragment.presenter.showAll();
            return true;
        }
        if (id == R.id.add)
            studentFragment.presenter.onAddStudentClick();
        else if (id == R.id.find_by_birth) {
            yearDialog.show(getSupportFragmentManager(), "Birth");
            command = "Birth";
        } else if (id == R.id.find_by_department) {
            departmentDialog.show(getSupportFragmentManager(), "Department");
            command = "Department";
        } else if (id == R.id.theme_default)
            Themes.changeTheme(this, "default");
        else if (id == R.id.theme_dark)
            Themes.changeTheme(this, "dark");
        else if (id == R.id.theme_color)
            Themes.changeTheme(this, "color");
        else if (studentFragment.selectedStudent == null)
            Toast.makeText(this, "Выберите абонента", Toast.LENGTH_LONG).show();
        else if (id == R.id.edit)
            studentFragment.presenter.onEditStudentClick(studentFragment.selectedStudent);
        else if (id == R.id.delete)
            deleteDialog.show();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void find(String criteria) {
        studentFragment.find(criteria);
        switch (command) {
            case "Birth":
                studentFragment.presenter.onStudentsByBirthClick();
                break;
            case "Department":
                studentFragment.presenter.onStudentsByDepartmentClick();
                break;
        }
    }
}
package lab.lab1;

import android.app.Activity;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import lab.lab1.db.DataBase;
import lab.lab1.db.StudentDAO;

public class Model implements Contract.Model {
    private static Contract.Model model;
    private StudentDAO studentDAO;
    private Activity activity;
    private Executor executor;
//    private List<Student> students = new ArrayList<Student>() {
//        {
//            add(new Student("Ходаковский", "Глеб", "ФАИС", "ИП41", "27.07.2003"));
//            add(new Student("Кобельчук", "Андрей", "ФАИС", "ИП41", "20.08.2002"));
//            add(new Student("Курочкин", "Денис", "МТФ", "МФ42", "05.12.2000"));
//            add(new Student("Гудойть", "Влад", "ФАИС", "ИП41", "31.10.2002"));
//            add(new Student("Падалица", "Максим", "ЭФ", "ИП41", "06.09.2000"));
//            add(new Student("Макеев", "Михаил", "МСФ", "МС41", "12.11.2001"));
//        }
//    };

    public static Contract.Model getModel(Activity activity) {
        if (model == null)
            model = new Model(activity);
        return model;
    }

    private Model(Activity activity) {
        this.activity = activity;
        studentDAO = Room
                .databaseBuilder(activity.getApplicationContext(), DataBase.class, "students")
                .allowMainThreadQueries()
                .build()
                .studentDao();
    }

    @Override
    public LiveData<List<Student>> getAll() {
        return studentDAO.getAll();
    }

    @Override
    public LiveData<List<String>> getDistinctGroups() {
        return studentDAO.getDistinctGroups();
    }

    @Override
    public LiveData<List<String>> getDistinctDepartments() {
        return studentDAO.getDistinctDepartments();
    }

    @Override
    public LiveData<List<Student>> getByDepartment(String department) {
        return studentDAO.getAllByDepartment(department);
    }

    @Override
    public void add(Student student) {
        studentDAO.insert(student);
    }

    @Override
    public void edit(Student student) {
        studentDAO.update(student);
    }

    @Override
    public void delete(Student student) {
        studentDAO.delete(student);
    }
}

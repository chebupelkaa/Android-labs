package lab.lab1;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface Contract {
    interface Model {
        LiveData<List<Student>> getAll();

        LiveData<List<String>> getDistinctGroups();

        LiveData<List<String>> getDistinctDepartments();

        LiveData<List<Student>> getByDepartment(String department);

        void add(Student student);

        void edit(Student student);

        void delete(Student student);
    }

    interface View {

        interface MainView {
            void showAllStudents(List<Student> students);

            void showStudentsByDepartment(List<Student> students);
        }

        interface AddView {
            void addStudent();

            void onClose();
        }

        interface EditView {
            void editStudent();

            void onClose();
        }
    }

    interface Presenter {

        interface MainPresenter {
            void showAll();

            void onAddStudentClick();

            void onEditStudentClick(Student student);

            void onDeleteStudentClick(Student student);

            void onStudentsByDepartmentClick();

            void onStudentsByBirthClick();

            void onCriteriaEnter(String criteria);
        }

        interface AddPresenter {
            void onAddStudent(Student student);

            void getDepartments();

            void getGroups();
        }

        interface EditPresenter {
            void onEditStudent(Student student);

            void getDepartments();

            void getGroups();
        }
    }
}

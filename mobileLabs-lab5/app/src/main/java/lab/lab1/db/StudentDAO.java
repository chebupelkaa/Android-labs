package lab.lab1.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import lab.lab1.Student;

@Dao
public interface StudentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Student student);

    @Delete
    void delete(Student student);

    @Update
    void update(Student student);

    @Query("SELECT * FROM student")
    LiveData<List<Student>> getAll();

    @Query("SELECT * FROM student WHERE department==:department")
    LiveData<List<Student>> getAllByDepartment(String department);

    @Query("SELECT DISTINCT `group` FROM student")
    LiveData<List<String>> getDistinctGroups();

    @Query("SELECT DISTINCT department FROM student")
    LiveData<List<String>> getDistinctDepartments();
}

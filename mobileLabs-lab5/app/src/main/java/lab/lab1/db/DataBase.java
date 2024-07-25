package lab.lab1.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import lab.lab1.Student;

@Database(entities = Student.class, version = 1)
public abstract class DataBase extends RoomDatabase {
    public abstract StudentDAO studentDao();
}

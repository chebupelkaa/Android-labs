package lab.lab1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Student implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String surname, name, department, group, birth;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public String getGroup() {
        return group;
    }

    public String getBirth() {
        return birth;
    }

    @Ignore
    public Student(String surname, String name, String department, String group, String birth) {
        this.surname = surname;
        this.name = name;
        this.department = department;
        this.group = group;
        this.birth = birth;
    }

    public Student(long id, String surname, String name, String department, String group, String birth) {
        this(surname, name, department, group, birth);
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("%s %s %s %s %s", surname, name, department, group, birth);
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Student)) return false;
        Student o = (Student) obj;
        return o.surname.equals(this.surname) &&
                o.name.equals(this.name) &&
                o.department.equals(this.department) &&
                o.group.equals(this.group) &&
                o.birth.equals(this.birth);
    }
}
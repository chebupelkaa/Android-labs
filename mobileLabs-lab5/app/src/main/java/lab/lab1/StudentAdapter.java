package lab.lab1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {
    private List<Student> students;
    private OnItemClickListener listener;

    public StudentAdapter(List<Student> students, OnItemClickListener listener) {
        this.students = students;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StudentViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = students.get(position);
        holder.surnameTextView.setText(student.getSurname());
        holder.nameTextView.setText(student.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(student);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        public TextView surnameTextView;
        public TextView nameTextView;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            surnameTextView = itemView.findViewById(R.id.student_surname);
            nameTextView = itemView.findViewById(R.id.student_name);
        }
    }
}
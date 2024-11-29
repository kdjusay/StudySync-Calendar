package com.example.studysynccalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> tasks;

    public TaskAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the task_item layout for each task
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        // Bind the task data to the view holder
        Task task = tasks.get(position);
        holder.tvTaskTitle.setText(task.getTitle());
        holder.tvTaskDescription.setText(task.getDescription());
        holder.tvTaskDeadline.setText(task.getDeadline());
    }

    @Override
    public int getItemCount() {
        return tasks.size(); // Return the number of tasks
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {

        TextView tvTaskTitle, tvTaskDescription, tvTaskDeadline;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            // Bind the TextViews from task_item.xml to the view holder
            tvTaskTitle = itemView.findViewById(R.id.tvTaskTitle);
            tvTaskDescription = itemView.findViewById(R.id.tvTaskDescription);
            tvTaskDeadline = itemView.findViewById(R.id.tvTaskDeadline);
        }
    }
}

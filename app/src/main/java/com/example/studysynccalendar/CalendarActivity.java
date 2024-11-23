package com.example.studysynccalendar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTasks;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList;
    private CalendarView calendarView;
    private Button btnAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Initialize Views
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        calendarView = findViewById(R.id.calendarView);
        btnAddTask = findViewById(R.id.btnAddTask);

        // Initialize task list
        taskList = new ArrayList<>();
        loadDummyTasks(); // Load some dummy tasks for now

        // Set up RecyclerView
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(taskList);
        recyclerViewTasks.setAdapter(taskAdapter);

        // Add Task button click event
        btnAddTask.setOnClickListener(view -> {
            // Add logic to show task creation dialog or activity
            Toast.makeText(CalendarActivity.this, "Add Task clicked", Toast.LENGTH_SHORT).show();
        });

        // CalendarView date change listener
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Handle date selection
            Toast.makeText(CalendarActivity.this, "Selected date: " + dayOfMonth + "/" + (month + 1) + "/" + year, Toast.LENGTH_SHORT).show();
        });
    }

    private void loadDummyTasks() {
        // Add some dummy tasks for now
        taskList.add(new Task("Mobile Dev", "Creating a Prototype", "Sept 28, 2024"));
        taskList.add(new Task("WebSys", "Final HTML and CSS Template Submission", "Oct 21, 2024"));
        taskList.add(new Task("Soc&Prof", "Milestone #2 Submission", "Oct 28, 2024"));
    }
}

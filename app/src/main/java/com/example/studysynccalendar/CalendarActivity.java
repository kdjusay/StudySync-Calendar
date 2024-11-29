package com.example.studysynccalendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity implements TaskDialog.TaskDialogListener, NoteDialog.NoteDialogListener {

    // Views for tasks and notes
    private RecyclerView recyclerViewTasks, recyclerViewNotes;
    private TaskAdapter taskAdapter;
    private NoteAdapter noteAdapter;
    private ArrayList<Task> taskList;
    private ArrayList<Note> noteList;
    private Button btnAddTask, btnAddNote;
    private Button btnTasks, btnNotes;
    private LinearLayout tasksContainer, notesContainer;
    private CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // Initialize Task and Note Views
        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);
        recyclerViewNotes = findViewById(R.id.recyclerViewNotes);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnAddNote = findViewById(R.id.btnAddNote);
        btnTasks = findViewById(R.id.btnTasks);
        btnNotes = findViewById(R.id.btnNotes);
        tasksContainer = findViewById(R.id.tasks_notes_container);
        notesContainer = findViewById(R.id.notes_container);
        calendarView = findViewById(R.id.calendarView);

        // Initialize Task and Note lists
        taskList = new ArrayList<>();
        noteList = new ArrayList<>();

        // Set up RecyclerView for tasks
        taskAdapter = new TaskAdapter(taskList);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewTasks.setAdapter(taskAdapter);

        // Set up RecyclerView for notes
        noteAdapter = new NoteAdapter(noteList);
        recyclerViewNotes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewNotes.setAdapter(noteAdapter);

        // Set initial visibility (show tasks first)
        showTasksView();

        // Add Task button click listener
        btnAddTask.setOnClickListener(view -> showTaskDialog());

        // Add Note button click listener
        btnAddNote.setOnClickListener(view -> showNoteDialog());

        // Switch to "Tasks" tab when the Tasks button is clicked
        btnTasks.setOnClickListener(view -> showTasksView());

        // Switch to "Notes" tab when the Notes button is clicked
        btnNotes.setOnClickListener(view -> showNotesView());

        // CalendarView date change listener
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Handle calendar date selection (if needed)
        });

        // Load initial data (dummy tasks and notes)
        loadDummyTasks();
        loadDummyNotes();
    }

    // Method to show the TaskDialog to add a new task
    private void showTaskDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        TaskDialog taskDialog = new TaskDialog();
        taskDialog.show(fragmentManager, "Task Dialog");
    }

    // Method to show the NoteDialog to add a new note
    private void showNoteDialog() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        NoteDialog noteDialog = new NoteDialog();
        noteDialog.show(fragmentManager, "Note Dialog");
    }

    // Show the tasks view and hide the notes view
    private void showTasksView() {
        recyclerViewTasks.setVisibility(View.VISIBLE);
        recyclerViewNotes.setVisibility(View.GONE);
        btnTasks.setBackground(getDrawable(R.drawable.tab_selected_background));
        btnNotes.setBackground(getDrawable(R.drawable.tab_unselected_background));
    }

    // Show the notes view and hide the tasks view
    private void showNotesView() {
        recyclerViewTasks.setVisibility(View.GONE);
        recyclerViewNotes.setVisibility(View.VISIBLE);
        btnTasks.setBackground(getDrawable(R.drawable.tab_unselected_background));
        btnNotes.setBackground(getDrawable(R.drawable.tab_selected_background));
    }

    // Callback when a new task is added from the TaskDialog
    @Override
    public void onTaskAdded(String title, String description, String deadline) {
        // Add the new task to the list and refresh the RecyclerView
        taskList.add(new Task(title, description, deadline));
        taskAdapter.notifyDataSetChanged();
    }

    // Callback when a new note is added from the NoteDialog
    @Override
    public void onNoteAdded(String title, String content) {
        // Add the new note to the list and refresh the RecyclerView
        noteList.add(new Note(title, content));
        noteAdapter.notifyDataSetChanged();
    }

    // Load some dummy tasks (for testing purposes)
    private void loadDummyTasks() {
        taskList.add(new Task("Mobile Development", "Create a prototype", "Sept 28, 2024"));
        taskList.add(new Task("Web Systems", "Final submission", "Oct 21, 2024"));
        taskList.add(new Task("Sociology", "Milestone submission", "Oct 28, 2024"));
        taskAdapter.notifyDataSetChanged();
    }

    // Load some dummy notes (for testing purposes)
    private void loadDummyNotes() {
        noteList.add(new Note("Meeting notes", "Discuss the project goals and requirements."));
        noteList.add(new Note("Shopping list", "Buy groceries: milk, eggs, bread."));
        noteList.add(new Note("Homework", "Finish reading the book for English class."));
        noteAdapter.notifyDataSetChanged();
    }
}

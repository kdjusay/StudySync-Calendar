package com.example.studysynccalendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class TaskDialog extends DialogFragment {

    private TaskDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_task, null);

        // Initialize views
        EditText etTaskTitle = view.findViewById(R.id.etTaskTitle);
        EditText etTaskDescription = view.findViewById(R.id.etTaskDescription);
        EditText etTaskDeadline = view.findViewById(R.id.etTaskDeadline);
        Button btnSaveTask = view.findViewById(R.id.btnSaveTask);
        Button btnCancelTask = view.findViewById(R.id.btnCancelTask);

        // Handle Save button click
        btnSaveTask.setOnClickListener(v -> {
            String title = etTaskTitle.getText().toString().trim();
            String description = etTaskDescription.getText().toString().trim();
            String deadline = etTaskDeadline.getText().toString().trim();

            // Ensure title, description, and deadline are not empty
            if (!title.isEmpty() && !description.isEmpty() && !deadline.isEmpty()) {
                listener.onTaskAdded(title, description, deadline); // Pass the task back to activity
                dismiss(); // Close the dialog
            } else {
                if (title.isEmpty()) etTaskTitle.setError("Title is required");
                if (description.isEmpty()) etTaskDescription.setError("Description is required");
                if (deadline.isEmpty()) etTaskDeadline.setError("Deadline is required");
            }
        });

        // Handle Cancel button click
        btnCancelTask.setOnClickListener(v -> dismiss());  // Simply dismiss the dialog

        builder.setView(view).setTitle("Add Task");
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (TaskDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement TaskDialogListener");
        }
    }

    // Interface to communicate with parent activity
    public interface TaskDialogListener {
        void onTaskAdded(String title, String description, String deadline);
    }
}

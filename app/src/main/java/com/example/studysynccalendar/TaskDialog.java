package com.example.studysynccalendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class TaskDialog extends Dialog {

    private EditText edtTaskDescription;
    private Button btnDone;
    private ImageButton btnClose;

    public TaskDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_entry_dialog);

        edtTaskDescription = findViewById(R.id.edt_task_description);
        btnDone = findViewById(R.id.btn_done);
        btnClose = findViewById(R.id.btn_close);

        // Close button functionality
        btnClose.setOnClickListener(v -> dismiss());

        // Done button functionality
        btnDone.setOnClickListener(v -> {
            String taskDescription = edtTaskDescription.getText().toString().trim();
            if (!taskDescription.isEmpty()) {
                Toast.makeText(getContext(), "Task Added: " + taskDescription, Toast.LENGTH_SHORT).show();
                dismiss();
            } else {
                Toast.makeText(getContext(), "Please enter a task description.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
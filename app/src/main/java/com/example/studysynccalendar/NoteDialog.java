package com.example.studysynccalendar;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class NoteDialog extends Dialog {

    private EditText edtNoteDescription;
    private Button btnDoneNote;
    private ImageButton btnCloseNote;

    public NoteDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_entry_dialog);

        edtNoteDescription = findViewById(R.id.edt_note_description);
        btnDoneNote = findViewById(R.id.btn_done_note);
        btnCloseNote = findViewById(R.id.btn_close_note);

        // Close button functionality
        btnCloseNote.setOnClickListener(v -> dismiss());

        // Done button functionality
        btnDoneNote.setOnClickListener(v -> {
            String noteDescription = edtNoteDescription.getText().toString().trim();
            if (!noteDescription.isEmpty()) {
                Toast.makeText(getContext(), "Note Added: " + noteDescription, Toast.LENGTH_SHORT).show();
                dismiss();
            } else {
                Toast.makeText(getContext(), "Please enter a note.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
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

public class NoteDialog extends DialogFragment {

    private NoteDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_note, null);

        // Initialize views
        EditText etNoteTitle = view.findViewById(R.id.etNoteTitle);
        EditText etNoteContent = view.findViewById(R.id.etNoteContent);
        Button btnSaveNote = view.findViewById(R.id.btnSaveNote);
        Button btnCancelNote = view.findViewById(R.id.btnCancelNote);

        // Handle Save button click
        btnSaveNote.setOnClickListener(v -> {
            String title = etNoteTitle.getText().toString().trim();
            String content = etNoteContent.getText().toString().trim();

            // Ensure title or content is not empty
            if (!title.isEmpty() && !content.isEmpty()) {
                listener.onNoteAdded(title, content); // Pass the note back to activity
                dismiss(); // Close the dialog
            } else {
                etNoteTitle.setError("Title is required");
                etNoteContent.setError("Content is required");
            }
        });

        // Handle Cancel button click
        btnCancelNote.setOnClickListener(v -> dismiss());

        builder.setView(view).setTitle("Add Note");
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (NoteDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement NoteDialogListener");
        }
    }

    // Interface to communicate with parent activity
    public interface NoteDialogListener {
        void onNoteAdded(String title, String content);
    }
}

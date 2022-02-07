package com.example.notesapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NoteViewModel n_VM;
    EditText editText, deletetext;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        //deletetext=findViewById(R.id.deleteText);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Note> arr = new ArrayList<>();
        n_VM = ViewModelProviders.of(this).get(NoteViewModel.class);
        n_VM.getAllNote().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                //if (notes.size() > 0) {
                recyclerView.setAdapter(new notesRecViewAdapter(notes, MainActivity.this, n_VM));
                //}

            }
        });


    }

    public void submit(View view) {
        if (editText.getText().toString().trim().length() != 0) {
            n_VM.insert(new Note(editText.getText().toString().trim()));
            editText.setText("");
        }
    }

    public void delete(View view) {
        if (deletetext.getText().toString().trim().length() != 0) {
            n_VM.delete(new Note("1234"));
            //deletetext.setText("");
        }
    }

}
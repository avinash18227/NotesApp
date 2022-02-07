package com.example.notesapp;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    NoteDatabase n_db;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        n_db=NoteDatabase.getInstance(application.getApplicationContext());
    }

    public LiveData<List<Note>> getAllNote(){
        return n_db.getNoteDao().getAllNote();
    }

    public void insert(Note n){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                n_db.getNoteDao().insert(n);
            }
        });
        thread.start();
    }

    void delete(Note note){
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                n_db.getNoteDao().delete(note);
                Log.d("clicked","this is clinking "+note.text);
            }
        });
        thread.start();
    }
}

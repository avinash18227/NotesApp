package com.example.notesapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    EditText editText;
    int id=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        editText = findViewById(R.id.editNote);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("note_text");
            editText.setText(value);
            id=(extras.getInt("note_id"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater in=getMenuInflater();
        in.inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.check){
            //Toast.makeText(this,"ediited sucess..",Toast.LENGTH_SHORT).show();
            if(id != -1){
                NoteDatabase n_db=NoteDatabase.getInstance(this);
                Thread thread=new Thread(new Runnable() {
                    @Override
                    public void run() {
                        n_db.getNoteDao().updateNote(id,editText.getText().toString());
                    }
                });
                thread.start();
            }
//            Intent intent=new Intent(this,MainActivity.class);
//            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
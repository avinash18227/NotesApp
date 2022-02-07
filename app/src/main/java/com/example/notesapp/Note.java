package com.example.notesapp;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.PrimaryKey;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

import java.util.List;

@Entity(tableName ="notes_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "text")
    String text;

    Note(String text){
        this.text=text;
    }
}




@Dao
interface NoteDao{

    @Delete
    void delete(Note note);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Note note);

    @Query("SELECT * FROM notes_table ORDER BY id ASC")
    LiveData<List<Note>> getAllNote();

    @Query("UPDATE notes_table set text=:tex WHERE id=:input_id")
    void updateNote(int input_id,String tex);

}





@Database(entities = {Note.class},version = 1)
abstract class NoteDatabase extends RoomDatabase{
    private static NoteDatabase instance;
    public static synchronized NoteDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context,NoteDatabase.class,"note_database").build();
        }
        return instance;
    }

    public abstract NoteDao getNoteDao();

}

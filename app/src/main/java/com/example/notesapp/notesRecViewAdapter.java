package com.example.notesapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class notesRecViewAdapter extends RecyclerView.Adapter<notesRecViewAdapter.ViewHolder> {

//    public interface onNoteListener{
//        void onNoteClick(Note note);
//    }
    List<Note> contactArr=new ArrayList<>();                  //initialize it in case of error
    NoteViewModel n_VM;
    Context context;
    public notesRecViewAdapter(List<Note> arr, Context context,NoteViewModel n_VM) {
        this.contactArr.clear();
        contactArr = arr;
        this.context=context;
        this.n_VM=n_VM;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(contactArr.get(position).text);

    }

    @Override
    public int getItemCount() {
        return contactArr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView txtName;
        private final ImageView image,editImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.note);
            image= itemView.findViewById(R.id.delete);
            image.setOnClickListener(this);
            editImage=itemView.findViewById(R.id.edit);
            editImage.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if(view.getId()==R.id.delete) {
                n_VM.delete(contactArr.get(getAdapterPosition()));
            }
            else if(view.getId()==R.id.edit){
                Log.d("editing","editing......."+contactArr.get(getAdapterPosition()).text);
                Intent intent=new Intent(context,EditActivity.class);
                intent.putExtra("note_text",contactArr.get(getAdapterPosition()).text);
                intent.putExtra("note_id",contactArr.get(getAdapterPosition()).id);
                context.startActivity(intent);
            }
        }

    }


}

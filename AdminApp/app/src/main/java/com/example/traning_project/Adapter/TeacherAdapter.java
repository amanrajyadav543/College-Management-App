package com.example.traning_project.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.traning_project.R;
import com.example.traning_project.TeachersData;
import com.example.traning_project.UpdateTeacherActivity;
import com.squareup.picasso.Picasso;

import java.security.PrivateKey;
import java.util.List;

public class TeacherAdapter  extends RecyclerView.Adapter<TeacherAdapter.TeacherViewAdapter> {

    private List<TeachersData> list;
    private Context context;
private  String category;
    public TeacherAdapter(List<TeachersData> list, Context context, String category) {
        this.list = list;
        this.context = context;
        this.category = category;
    }

    @NonNull
    @Override
    public TeacherViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout,parent,false);
        return new TeacherViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAdapter.TeacherViewAdapter holder, int position) {
       TeachersData item = list.get(position);
       holder.name.setText(item.getName());
       holder.post.setText(item.getPost());
       holder.email.setText(item.getPost());
       //image setup
        if(item.getImage() != null && !item.getImage().trim().isEmpty())
            Picasso.get().load(item.getImage()).into(holder.imageView);
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, UpdateTeacherActivity.class);
                intent.putExtra("name", item.getName());
                intent.putExtra("Post", item.getPost());
                intent.putExtra("email", item.getEmail());
                intent.putExtra("image", item.getImage());
                intent.putExtra("Key", item.getKey());
                intent.putExtra("category", category);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class TeacherViewAdapter extends RecyclerView.ViewHolder{
        private TextView name, email , post;
        private Button update;
        private ImageView imageView;

        public TeacherViewAdapter(@NonNull View itemView) {
            super(itemView);
            name= itemView.findViewById(R.id.teachername);
            email= itemView.findViewById(R.id.teacheremail);
            post= itemView.findViewById(R.id.teacherpost);
            update= itemView.findViewById(R.id.teacherupdate);
            imageView= itemView.findViewById(R.id.teacherimage);
        }
    }
}

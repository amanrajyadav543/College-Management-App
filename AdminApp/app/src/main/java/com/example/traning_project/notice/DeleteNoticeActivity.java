package com.example.traning_project.notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.traning_project.NoticeData;
import com.example.traning_project.R;
import com.example.traning_project.databinding.ActivityDeleteNoticeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DeleteNoticeActivity extends AppCompatActivity {

    ActivityDeleteNoticeBinding binding;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteNoticeBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        reference= FirebaseDatabase.getInstance().getReference().child("Notice");
        binding.deleteNoticeRecycler.setLayoutManager(new LinearLayoutManager(this));
        binding.deleteNoticeRecycler.setHasFixedSize(true);
        getNotice();

    }
    private  void getNotice(){
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot snapshot1 : snapshot.getChildren() ){
                    NoticeData data = snapshot1.getValue(NoticeData.class);
                    list.add(data);
                }
                adapter = new NoticeAdapter(DeleteNoticeActivity.this, list);;
                adapter.notifyDataSetChanged();
                binding.progressbas.setVisibility(View.GONE);
                binding.deleteNoticeRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                adapter.notifyDataSetChanged();
                Toast.makeText(DeleteNoticeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
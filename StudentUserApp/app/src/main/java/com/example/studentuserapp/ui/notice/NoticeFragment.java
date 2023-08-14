package com.example.studentuserapp.ui.notice;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studentuserapp.R;
import android.app.Application;
import com.example.studentuserapp.databinding.FragmentNoticeBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class NoticeFragment extends Fragment {
             FragmentNoticeBinding binding;
    private ArrayList<NoticeData> list;
    private NoticeAdapter adapter;
    private DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View view= inflater.inflate(R.layout.fragment_notice, container, false);
        binding= FragmentNoticeBinding.inflate(inflater,container,false);
        FirebaseApp.initializeApp(getContext());
 reference = FirebaseDatabase.getInstance().getReference().child("Notice");
        binding.deleteNoticeRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.deleteNoticeRecycler.setHasFixedSize(true);
        getNotice();

        return binding.getRoot();

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
                adapter = new NoticeAdapter(getContext(), list);;
                adapter.notifyDataSetChanged();
                binding.progressbas.setVisibility(View.GONE);
                binding.deleteNoticeRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
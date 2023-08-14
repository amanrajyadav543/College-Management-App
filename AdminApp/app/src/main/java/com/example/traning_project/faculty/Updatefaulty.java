package com.example.traning_project.faculty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.traning_project.Adapter.TeacherAdapter;
import com.example.traning_project.TeachersData;
import com.example.traning_project.databinding.ActivityUpdatefaultyBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class Updatefaulty extends AppCompatActivity {
    ActivityUpdatefaultyBinding binding;
    private List<TeachersData> list1 , list2 , list3, list4;
     private DatabaseReference references, dbref;
     private TeacherAdapter adapter;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUpdatefaultyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    references = FirebaseDatabase.getInstance().getReference().child("teacher");

             csDepartment();
             civilDepartment();
             ElectricalDepartment();
             MechanicalDepartment();

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Updatefaulty.this, Addteacher.class));
            }
        });
    }

    private void csDepartment() {
        dbref= references.child("Computer Science");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1= new ArrayList<>();
                Log.e("data", snapshot.toString());
                if(!snapshot.exists()){
                    binding.csnodata.setVisibility(View.VISIBLE);
                    binding.csDepartment.setVisibility(View.GONE);
                }else {
                    binding.csnodata.setVisibility(View.GONE);
                    binding.csDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        TeachersData data = snapshot1.getValue(TeachersData.class);
                        list1.add(data);
                    }
                    binding.csDepartment.setHasFixedSize(true);
                    binding.csDepartment.setLayoutManager(new LinearLayoutManager(Updatefaulty.this));
                    adapter = new TeacherAdapter(list1, Updatefaulty.this,"Computer Science");
                    binding.csDepartment.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaulty.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void civilDepartment() {
        dbref= references.child("Civil");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list2= new ArrayList<>();
                if(!snapshot.exists()){
                    binding.civilnodata.setVisibility(View.VISIBLE);
                    binding.civilDepartment.setVisibility(View.GONE);
                }else {
                    binding.civilnodata.setVisibility(View.GONE);
                    binding.civilDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        TeachersData data = snapshot1.getValue(TeachersData.class);
                        list2.add(data);
                    }
                    binding.civilDepartment.setHasFixedSize(true);
                    binding.civilDepartment.setLayoutManager(new LinearLayoutManager(Updatefaulty.this));
                    adapter = new TeacherAdapter(list2, Updatefaulty.this,"Civil");
                    binding.civilDepartment.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaulty.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void ElectricalDepartment() {
        dbref= references.child("Electrical");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list3= new ArrayList<>();
                if(!snapshot.exists()){
                    binding.Eecsnodata.setVisibility(View.VISIBLE);
                    binding.EeDepartment.setVisibility(View.GONE);
                }else {
                    binding.Eecsnodata.setVisibility(View.GONE);
                    binding.EeDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        TeachersData data = snapshot1.getValue(TeachersData.class);
                        list3.add(data);
                    }
                    binding.EeDepartment.setHasFixedSize(true);
                    binding.EeDepartment.setLayoutManager(new LinearLayoutManager(Updatefaulty.this));
                    adapter = new TeacherAdapter(list3, Updatefaulty.this,"Electrical");
                    binding.EeDepartment.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaulty.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void  MechanicalDepartment() {
        dbref= references.child("Mechanical");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4= new ArrayList<>();
                if(!snapshot.exists()){
                    binding.menodata.setVisibility(View.VISIBLE);
                    binding.meDepartment.setVisibility(View.GONE);
                }else {
                    binding.menodata.setVisibility(View.GONE);
                    binding.meDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        TeachersData data = snapshot1.getValue(TeachersData.class);
                        list4.add(data);
                    }
                    binding.meDepartment.setHasFixedSize(true);
                    binding.meDepartment.setLayoutManager(new LinearLayoutManager(Updatefaulty.this));
                    adapter = new TeacherAdapter(list4, Updatefaulty.this, "Mechanical");
                    binding.meDepartment.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Updatefaulty.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}
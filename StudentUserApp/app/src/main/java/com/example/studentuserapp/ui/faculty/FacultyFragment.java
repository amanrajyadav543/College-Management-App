package com.example.studentuserapp.ui.faculty;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.studentuserapp.R;
import com.example.studentuserapp.databinding.FragmentNoticeBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;


public class FacultyFragment extends Fragment {

    FragmentNoticeBinding binding;
    private List<TeachersData> list1 , list2 , list3, list4;
    private DatabaseReference references, dbref;
    private TeacherAdapter adapter;
    FirebaseStorage firebaseStorage;
    private RecyclerView csDepartment ,civilDepartment, EeDepartment, meDepartment;
    private LinearLayout csnodata, civilnodata ,Eecsnodata ,menodata;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_faculty, container, false);

        csDepartment=view.findViewById(R.id.csDepartment);
        civilDepartment=view.findViewById(R.id.civilDepartment);
        EeDepartment=view.findViewById(R.id.EeDepartment);
        meDepartment=view.findViewById(R.id.meDepartment);

        csnodata = view.findViewById(R.id.csnodata);
        civilnodata = view.findViewById(R.id.civilnodata);
        Eecsnodata = view.findViewById(R.id.Eecsnodata);
        menodata = view.findViewById(R.id.menodata);

        FirebaseApp.initializeApp(getContext());

        references = FirebaseDatabase.getInstance().getReference().child("teacher");

        csDepartment();
        civilDepartment();
        ElectricalDepartment();
        MechanicalDepartment();



        return view;
    }
    private void csDepartment() {
        dbref= references.child("Computer Science");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list1= new ArrayList<>();
                Log.e("data", snapshot.toString());
                if(!snapshot.exists()){
                    csnodata.setVisibility(View.VISIBLE);
                    csDepartment.setVisibility(View.GONE);
                }else {
                    csnodata.setVisibility(View.GONE);
                    csDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        TeachersData data = snapshot1.getValue(TeachersData.class);
                        list1.add(data);
                    }
                 csDepartment.setHasFixedSize(true);
                    csDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list1, getContext(),"Computer Science");
                    csDepartment.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

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
                    civilnodata.setVisibility(View.VISIBLE);
                    civilDepartment.setVisibility(View.GONE);
                }else {
                    civilnodata.setVisibility(View.GONE);
                  civilDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        TeachersData data = snapshot1.getValue(TeachersData.class);
                        list2.add(data);
                    }
                   civilDepartment.setHasFixedSize(true);
                 civilDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list2, getContext(),"Civil");
          civilDepartment.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

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
                    Eecsnodata.setVisibility(View.VISIBLE);
                    EeDepartment.setVisibility(View.GONE);
                }else {
                    Eecsnodata.setVisibility(View.GONE);
                    EeDepartment.setVisibility(View.VISIBLE);
                    for(DataSnapshot snapshot1 : snapshot.getChildren()){
                        TeachersData data = snapshot1.getValue(TeachersData.class);
                        list3.add(data);
                    }
                    EeDepartment.setHasFixedSize(true);
EeDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list3, getContext(),"Electrical");
                    EeDepartment.setAdapter(adapter);
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void  MechanicalDepartment() {
        dbref= references.child("Mechanical");
        dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list4 = new ArrayList<>();
                if (!snapshot.exists()) {
                    menodata.setVisibility(View.VISIBLE);
                    meDepartment.setVisibility(View.GONE);
                } else {
                    menodata.setVisibility(View.GONE);
                    meDepartment.setVisibility(View.VISIBLE);
                    for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                        TeachersData data = snapshot1.getValue(TeachersData.class);
                        list4.add(data);
                    }
                    meDepartment.setHasFixedSize(true);
                    meDepartment.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new TeacherAdapter(list4, getContext(), "Mechanical");
                    meDepartment.setAdapter(adapter);
                }


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


}
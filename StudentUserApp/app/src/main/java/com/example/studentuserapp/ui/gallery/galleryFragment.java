package com.example.studentuserapp.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studentuserapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class galleryFragment extends Fragment {

     RecyclerView convorecycler, othersrecycler;

     GalleryAdapter adapter;
     DatabaseReference reference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view=  inflater.inflate(R.layout.fragment_gallery, container, false);
        convorecycler= view.findViewById(R.id.convorecycler);
        othersrecycler= view.findViewById(R.id.othersrecycler);
        reference = FirebaseDatabase.getInstance().getReference().child("gallery");

        getOtherImages();
     getConvoImage();


         return view;
    }

    private void getOtherImages(){
        reference.child("Other Events").addValueEventListener(new ValueEventListener() {
                  List<String> imagelist = new ArrayList<>();
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
                    String data = (String)  snapshot1.getValue();
                    imagelist.add(data);
                }
                adapter= new GalleryAdapter(getContext(),imagelist);
                othersrecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                othersrecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void getConvoImage(){
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {
            List<String>imagelist = new ArrayList<>();
            @Override

            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snapshot1:snapshot.getChildren()){
//                    Gson gson = new Gson();
//                    String jsonString = gson.toJson( snapshot1.getValue());
//                    imagelist.add(jsonString);
                    String data =(String)  snapshot1.getValue();
                    imagelist.add(data);


                }
                adapter= new GalleryAdapter(getContext(),imagelist);
                convorecycler.setLayoutManager(new GridLayoutManager(getContext(),3));
                convorecycler.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

    }
}
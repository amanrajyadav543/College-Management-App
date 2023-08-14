package com.example.traning_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.traning_project.databinding.ActivityUpdateTeacherBinding;
import com.example.traning_project.faculty.Addteacher;
import com.example.traning_project.faculty.Updatefaulty;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class UpdateTeacherActivity extends AppCompatActivity {
    private  String name , email, post , image;
    private  final int REQ=1;
    private Bitmap bitmap = null;
    private ProgressDialog progressDialog;
    private  StorageReference storageReference;
    private DatabaseReference reference;
    private  String downloadUrl, category , uniqueKey;
    ActivityUpdateTeacherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUpdateTeacherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
        post = getIntent().getStringExtra("post");
        image = getIntent().getStringExtra("image");
         storageReference = FirebaseStorage.getInstance().getReference();
         reference = FirebaseDatabase.getInstance().getReference();

         category = getIntent().getStringExtra("category");
         uniqueKey = getIntent().getStringExtra("key");

        try {
            Picasso.get().load(image).into(binding.updateteacherimage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        binding.updateteachename.setText(name);
        binding.updateteacheEmail.setText(email);
        binding.updateteacherpost.setText(post);

        binding.updatephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengallery();
            }
        });
        binding.updateteacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.updateteachename.getText().toString();
                email = binding.updateteacheEmail.getText().toString();
                post = binding.updateteacherpost.getText().toString();

                checkvalidation();
            }
        });
        binding.deleteteacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });


    }
     private void checkvalidation(){
        if(name.isEmpty()){
            binding.updateteachename.setError("Empty");
            binding.updateteachename.requestFocus();
        } else if (email.isEmpty()) {
            binding.updateteacheEmail.setError("Empty");
            binding.updateteacheEmail.requestFocus();
        } else if (post.isEmpty()) {
            binding.updateteacherpost.setError("Empty");
            binding.updateteacherpost.requestFocus();
        } else if (bitmap ==null) {
                   updateData(image);
        } else {
            uploadImage();
        }

     }
    private void updateData(String s){
        HashMap hp = new HashMap();
        hp.put("name", name);
        hp.put("email", email);
        hp.put("post", post);
        hp.put("image", s) ;


        reference.child(category).child(uniqueKey).updateChildren(hp).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(UpdateTeacherActivity.this, "Teacher Updated successfully", Toast.LENGTH_SHORT).show();
              Intent intent= new Intent(UpdateTeacherActivity.this, Updatefaulty.class);
              intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
              startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateTeacherActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();

            }
        });

    }
     private void deleteData(){
        reference.child(category).child(uniqueKey).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(UpdateTeacherActivity.this, "Teacher Deleted successfully", Toast.LENGTH_SHORT).show();
                        Intent intent= new Intent(UpdateTeacherActivity.this, Updatefaulty.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdateTeacherActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });



     }
    private void uploadImage(){
        progressDialog.setMessage("Uploading....");
        progressDialog.setCancelable(false);
        progressDialog.show();
        //Image compression
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50 ,baos);
        byte[] finalImage = baos.toByteArray();

        final StorageReference filePath = storageReference.child("Teachers").child(finalImage + "jpg");
        final UploadTask uploadTask = filePath.putBytes(finalImage);
        uploadTask.addOnCompleteListener(UpdateTeacherActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                    updateData(downloadUrl);

                                }
                            })   ;
                        }
                    });
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(UpdateTeacherActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();

                }


            }
        });




    }
    private void opengallery(){
        Intent pickImage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage, REQ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ && resultCode==RESULT_OK){
            Uri uri = data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            binding.updatephoto.setImageBitmap(bitmap);
        }
    }
}
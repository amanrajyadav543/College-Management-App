package com.example.traning_project.faculty;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.traning_project.NoticeData;
import com.example.traning_project.R;
import com.example.traning_project.TeachersData;
import com.example.traning_project.UploadNotice;
import com.example.traning_project.databinding.ActivityAddteacherBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Addteacher extends AppCompatActivity {
    ActivityAddteacherBinding binding;
    private final int REQ =1;
    private  String  category, name ,post , email, downloadUrl= " ";
    private Bitmap bitmap =null;
    ProgressDialog progressDialog;
 StorageReference storageReference;
 DatabaseReference reference, dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddteacherBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        getSupportActionBar().hide();
         progressDialog = new ProgressDialog(this);

         storageReference = FirebaseStorage.getInstance().getReference();
         reference= FirebaseDatabase.getInstance().getReference().child("teacher");

        String[] items = new String[]{"Select Category", "Computer Science", "Electrical","Civil","Mechanical"};

        binding.teacherCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items));


        binding.teacherCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category= binding.teacherCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengallery();
            }
        });

        binding.btaddteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this function is verify all the validation of the inputs of the teacher's details.
                checkValidation();

            }


        });

    }

    private void checkValidation() {
        name = binding.edtteachername.getText().toString();
        email= binding.edtteacheremail.getText().toString();
        post= binding.edtteacherpost.getText().toString();
        if(name.isEmpty()){
              binding.edtteachername.setError("Empty");
            binding.edtteachername.requestFocus();
        } else if (email.isEmpty()) {
            binding.edtteacheremail.setError("Empty");
            binding.edtteacheremail.requestFocus();
        } else if (post.isEmpty()) {
            binding.edtteacherpost.setError("Empty");
            binding.edtteacherpost.requestFocus();
        } else if (category.equals("Select Category")) {
            Toast.makeText(this, "Please select the category", Toast.LENGTH_SHORT).show();

        } else if (bitmap == null) {
            insertData();
        } else {
            uploadImage();
        }
    }
    private void insertData(){
        dbref = reference.child(category);
        final String uniqueKey = dbref.push().getKey();



        TeachersData teachersData = new TeachersData(name, email, post,downloadUrl, uniqueKey);

        //store in firebase
        dbref.child(uniqueKey).setValue(teachersData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(Addteacher.this, "Teacher Added", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Addteacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
        uploadTask.addOnCompleteListener(Addteacher.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                                    insertData();

                                }
                            })   ;
                        }
                    });
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(Addteacher.this, "Something went Wrong", Toast.LENGTH_SHORT).show();

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
            binding.profileimage.setImageBitmap(bitmap);
        }
    }
}
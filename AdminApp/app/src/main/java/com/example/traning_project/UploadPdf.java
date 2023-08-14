package com.example.traning_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Toast;

import com.example.traning_project.databinding.ActivityUploadNoticeBinding;
import com.example.traning_project.databinding.ActivityUploadPdfBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class UploadPdf extends AppCompatActivity {
    private final int REQ = 1;
    private Uri pdfData;
    private DatabaseReference databasereference;
    String downloadUrl = "";
    private StorageReference storageReference;
    private ProgressDialog progressDialog;
    private String pdfname, title;
    ActivityUploadPdfBinding binding;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUploadPdfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DatabaseReference databasereference= FirebaseDatabase.getInstance().getReference();
        StorageReference Reference= FirebaseStorage.getInstance().getReference();

        progressDialog = new ProgressDialog(this);


        binding.addebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }


        });

        binding.btnuploadebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title= binding.pdftitle.getText().toString();
                if(title.isEmpty()){
                    binding.pdftitle.setError("Empty");
                    binding.pdftitle.requestFocus();

                } else if (pdfData== null) {
                    Toast.makeText(UploadPdf.this, "Please upload pdf", Toast.LENGTH_SHORT).show();

                }else {
                    uploadpdf();
                }

            }


        });


    }
    private void uploadpdf() {
        progressDialog.setTitle("Please wait....");
        progressDialog.setMessage("uploading pdf");
        progressDialog.show();


         StorageReference reference = storageReference.child("pdf/" + pdfname+ "-" +System.currentTimeMillis()+".pdf");
        reference.putFile(pdfData)
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri>  uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uri = uriTask.getResult();
                uploadData(String.valueOf(uri));

            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(UploadPdf.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });


    }
    private void uploadData(String valueOf){
        String uniqueKey = databasereference.child("pdf").push().getKey();
        HashMap data = new HashMap();
        data.put("pdftitel", title);
        data.put("pdfUrl", downloadUrl);
        databasereference.child("pdf").child(uniqueKey).setValue(data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
                Toast.makeText(UploadPdf.this, "Pdf uploadded Successfully", Toast.LENGTH_SHORT).show();
                binding.pdftitle.setText("");


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(UploadPdf.this, "Failed to upload", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void openGallery(){
       Intent intent = new Intent();
       intent.setType("application/pdf/docs/ppt");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
       intent.setAction(Intent.ACTION_GET_CONTENT);
       startActivityForResult(Intent.createChooser(intent, "Select Pdf File"), REQ);
    }

    @SuppressLint("Range")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ && resultCode==RESULT_OK){
            
            if (requestCode== REQ && resultCode== RESULT_OK){
                pdfData= data.getData();
                if(pdfData.toString().startsWith("content://")){
                    Cursor cursor = null;
                    cursor = UploadPdf.this.getContentResolver().query(pdfData, null,null , null,null);
                    if(cursor != null && cursor.moveToFirst()){
                        pdfname= cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                    
                } else if (pdfData.toString().startsWith("file://")) {
                    pdfname= new File(pdfData.toString()).getName();
                    
                }
            }

        }
    }
}
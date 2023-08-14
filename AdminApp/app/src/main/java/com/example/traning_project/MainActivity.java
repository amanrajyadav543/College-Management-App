package com.example.traning_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.traning_project.databinding.ActivityMainBinding;
import com.example.traning_project.faculty.Updatefaulty;
import com.example.traning_project.notice.DeleteNoticeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
 ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
  getSupportActionBar().hide();



        binding.addnotice.setOnClickListener(this);
        binding.gallery.setOnClickListener(this);
        binding.addEbook.setOnClickListener(this);
        binding.deleteNotice.setOnClickListener(this);
        binding.faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Updatefaulty.class));
            }
        });
        binding.deleteNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, DeleteNoticeActivity.class));
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.addnotice:
                startActivity(new Intent(MainActivity.this, UploadNotice.class));
                break;
            case R.id.gallery:
                startActivity(new Intent(MainActivity.this, UploadImage.class));
                break;
            case R.id.addEbook:
                startActivity(new Intent(MainActivity.this, UploadPdf.class));
                break;
//            case R.id.deleteNotice:
//                startActivity(new Intent(MainActivity.this, DeleteNoticeActivity.class));
//                break;


        }

    }
}
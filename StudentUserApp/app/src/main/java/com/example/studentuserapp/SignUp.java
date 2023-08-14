//package com.example.traning_project.Model;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import com.example.studentuserapp.MainActivity;
////import com.example.studentuserapp.SignInActivity;
//import com.example.studentuserapp.databinding.ActivitySignUpBinding;
//import com.example.traning_project.Model.Users;
//import com.example.studentuserapp.databinding.ActivitySignUpBinding;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class SignUp extends AppCompatActivity {
//
//    ActivitySignUpBinding binding;
//    private FirebaseAuth auth;
//    FirebaseDatabase database;
//    ProgressDialog progressDialog;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        getSupportActionBar().hide();
//        auth = FirebaseAuth.getInstance();
//        database = FirebaseDatabase.getInstance();
//
//
//        progressDialog = new ProgressDialog(SignUp.this);
//        progressDialog.setTitle("Creating Account");
//        progressDialog.setMessage("We are Creating your account ");
//        binding.btnsignup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                progressDialog.show();
//                auth.createUserWithEmailAndPassword(binding.etemail.getText().toString(), binding.etpassword.getText().toString())
//                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                            @Override
//                            public void onComplete(@NonNull Task<AuthResult> task) {
//                                progressDialog.dismiss();
//                                if(task.isSuccessful()){
//                                    Users user = new Users(binding.etuserName.getText().toString(), binding.etemail.getText().toString(), binding.etpassword.getText().toString());
//                                    String id = task.getResult().getUser().getUid();
//                                    database.getReference().child("Users").child(id).setValue(user);
//                                    Toast.makeText(SignUp.this , "User Created successfully" , Toast.LENGTH_LONG).show();
//                                    startActivity(new Intent(SignUp.this, MainActivity.class));
//
//                                }
//                                else {
//                                    Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//
//                            }
//                        });
//
//            }
//        });
//        binding.tvalreadtaccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(SignUp.this, SignInActivity.class));
//
//            }
//        });
//    }
//}
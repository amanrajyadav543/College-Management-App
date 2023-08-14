package com.example.studentuserapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.studentuserapp.EBook.EbookActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavController navController ;

    private ActionBarDrawerToggle toggle;
    private  BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerlayout;

    private NavigationView navigation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_main);

        bottomNavigationView= findViewById(R.id.bottomNavigationView);
        drawerlayout= findViewById(R.id.drawerlayout);
        navigation_view= findViewById(R.id.navigation_view);

        navController = Navigation.findNavController(this,R.id.frame_layout);

        NavigationUI.setupWithNavController(bottomNavigationView,navController);


        toggle = new ActionBarDrawerToggle(this, drawerlayout, R.string.start, R.string.close);

        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        navigation_view.setNavigationItemSelectedListener(this);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return  true;
        }
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.navigation_developer:
                Toast.makeText(this, "Developer", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_rate:
                Toast.makeText(this, "Rate Us", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_themes:
                Toast.makeText(this, "Themes", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigaton_about:
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_ebook:
               startActivity(new Intent(this, EbookActivity.class));
                break;
            case R.id.navigation_website:
                Toast.makeText(this, "Website", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
            case R.id.navigation_video:
                Toast.makeText(this, "video", Toast.LENGTH_SHORT).show();
                break;


        }



        return true;
    }
}









































//package com.example.studentuserapp;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.ActionBarDrawerToggle;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.drawerlayout.widget.DrawerLayout;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
//import androidx.navigation.ui.NavigationUI;
//
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.Toast;
//
//import com.example.studentuserapp.databinding.ActivityMainBinding;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//import com.google.android.material.navigation.NavigationView;
//
//import java.util.Objects;
//
//public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
//    ActivityMainBinding binding;
//    private NavController navController ;
//
//    private ActionBarDrawerToggle toggle;
//    private  BottomNavigationView bottomNavigationView;
//    private DrawerLayout drawerLayout;
//
//    private NavigationView navigationView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//          binding = ActivityMainBinding.inflate(getLayoutInflater());
//           setContentView(binding.getRoot());
//
//             navController = Navigation.findNavController(this,R.id.frame_layout);
//
//             NavigationUI.setupWithNavController(binding.bottomNavigationView,navController);
//
//
//             toggle = new ActionBarDrawerToggle(this, binding.drawerlayout, R.string.start, R.string.close);
//
//             binding.drawerlayout.addDrawerListener(toggle);
//             toggle.syncState();
//      Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
//
//      binding.navigationView.setNavigationItemSelectedListener(this);
//      NavigationUI.setupWithNavController(binding.bottomNavigationView,navController);
// }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(toggle.onOptionsItemSelected(item)){
//            return  true;
//        }
//        return true;
//    }
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//
//            case R.id.navigation_developer:
//                Toast.makeText(this, "Developer", Toast.LENGTH_SHORT).show();
//             break;
//            case R.id.navigation_rate:
//                Toast.makeText(this, "Rate Us", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigation_themes:
//                Toast.makeText(this, "Themes", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigaton_about:
//                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigation_ebook:
//                Toast.makeText(this, "E-Book", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigation_website:
//                Toast.makeText(this, "Website", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigation_share:
//                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.navigation_video:
//                Toast.makeText(this, "video", Toast.LENGTH_SHORT).show();
//                break;
//
//
//        }
//
//
//
//        return true;
//    }
//}
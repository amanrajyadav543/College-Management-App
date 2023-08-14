package com.example.studentuserapp.ui.home;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.codebyashish.autoimageslider.AutoImageSlider;
import com.example.studentuserapp.R;
import com.example.studentuserapp.databinding.ActivityMainBinding;
import com.example.studentuserapp.databinding.FragmentHomeBinding;


public class HomeFragment extends Fragment {
    private AutoImageSlider autoImageSlider;

    ImageView map;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//
        View view =inflater.inflate(R.layout.fragment_home, container, false);

         map = container.findViewById(R.id.map);
        if (map != null) {
            map.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openMap();
                }
            });
        }



        return view;


    }
    private void openMap(){
//        Uri uri= Uri.parse("geo:0, 0?q=Buddha Institute of Technology Gorakhpur");
//        Intent intent= new Intent(Intent.ACTION_VIEW,uri);
//        intent.setPackage("com.google.android.apps.map");
//        startActivity(intent);
        String latitude = "26.739450823277014"; // Replace with the desired latitude
        String longitude = "83.27139541007016"; // Replace with the desired longitude

        Uri gmmIntentUri = Uri.parse("geo:" + latitude + "," + longitude);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps"); // Specify the package name to ensure opening in Google Maps app


            startActivity(mapIntent);

    }


}
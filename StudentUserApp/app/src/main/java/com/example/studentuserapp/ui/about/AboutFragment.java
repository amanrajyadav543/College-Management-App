package com.example.studentuserapp.ui.about;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.studentuserapp.R;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {
    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.computer, "Computer Science", "hgerhghhvuithjahgvehghuighvehvuwrhguhvwruhwrghjkhghejkghhadgveger" +
                "gehagvhaghauhuaehtahgvvgjhgurtugvgamvethmvamehtuheruhcagueahrugearjgrjutervgmadngdafngajnc;mdjfhgja"));
        list.add(new BranchModel(R.drawable.settings_24, "Mechanical Engineering", "hgerhghhvuithjahgvehghuighvehvuwrhguhvwruhwrghjkhghejkghhadgveger" +
                "gehagvhaghauhuaehtahgvvgjhgurtugvgamvethmvamehtuheruhcagueahrugearjgrjutervgmadngdafngajnc;mdjfhgja"));

        adapter = new BranchAdapter(getContext(), list);

        ViewPager viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        ImageView imageView = view.findViewById(R.id.clgimg);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/college-admin-5d5e8.appspot.com/o/gallery%2F%5BB%40426c230jpg?alt=media&token=fbb86400-2337-4455-9f45-81ef9179e13c")
                .into(imageView);


        return view;
    }
}
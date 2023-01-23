package com.insacvl.sefkim_flickr.view;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.adapters.RoomAdapter;
import com.insacvl.sefkim_flickr.model.Room;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private View homeView;
    private Switch theme_switcher;
    private SharedPreferences sharedPreferences;

    private List<Room> roomList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RoomAdapter mAdapter;
    private ImageButton settings;
    private ConstraintLayout constraintLayout;
    private ImageButton backButton;


    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home,container,false);
        constraintLayout=homeView.findViewById(R.id.homeBg);
        // Save switch state in shared preferences
//        theme_switcher=homeView.findViewById(R.id.theme_switch);
        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
//        System.err.println("actual state of the switch ========> " + sharedPreferences.getBoolean("save"));
//        theme_switcher.setChecked(sharedPreferences.getBoolean("darkMode",true));
//        if(sharedPreferences.getBoolean("darkMode",true)) {
//            constraintLayout.setBackground(getResources().getDrawable(R.drawable.photography_bg));
//        }else{
//            constraintLayout.setBackground(getResources().getDrawable(R.drawable.background));
//        }
        recyclerView = homeView.findViewById(R.id.recycler_view);
        mAdapter = new RoomAdapter(roomList, getActivity().getApplicationContext());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareRoomData();
        return homeView;
    }

    @Override
    public void onStart() {
        super.onStart();
//        theme_switcher=homeView.findViewById(R.id.theme_switch);
//        theme_switcher.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(theme_switcher.isChecked()){
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
////                    constraintLayout.setBackground(getResources().getDrawable(R.drawable.background_night));
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.putBoolean("darkMode",true);
//                    editor.apply();
//                    theme_switcher.setChecked(true);
//                }else{
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
////                    constraintLayout.setBackground(getResources().getDrawable(R.drawable.background));
//                    SharedPreferences.Editor editor=sharedPreferences.edit();
//                    editor.putBoolean("darkMode",false);
//                    editor.apply();
//                    theme_switcher.setChecked(false);
//                }
//
//            }
//        });
    }
    private void prepareRoomData() {
        Room room = new Room("1", "FASHION");
        roomList.add(room);
        room = new Room("2", "TECH");
        roomList.add(room);
        room = new Room("1", "PHOTOGRAPHY");
        roomList.add(room);
        room = new Room("2", "SPORTS");
        roomList.add(room);
        room = new Room("1", "FOOD");
        roomList.add(room);

        mAdapter.notifyDataSetChanged();
    }
}

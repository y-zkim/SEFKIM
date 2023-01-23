package com.insacvl.sefkim_flickr.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.insacvl.sefkim_flickr.MainActivity;
import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.adapters.SingleRoomAdapter;
import com.insacvl.sefkim_flickr.model.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * Smart Home
 * https://github.com/quintuslabs/SmartHome
 * Created on 27-OCT-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class RoomDetailsActivity extends Fragment {
    private List<Room> roomList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SingleRoomAdapter mAdapter;
    private View settingsView;
    private ImageButton backButton;

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        settingsView = inflater.inflate(R.layout.settings,container,false);
        recyclerView = settingsView.findViewById(R.id.recycler_view);
        backButton = settingsView.findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        mAdapter = new SingleRoomAdapter(roomList, getContext(),getActivity().getPreferences(Context.MODE_PRIVATE));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareRoomData();
        return settingsView;
    }


//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
////        if (Build.VERSION.SDK_INT >= 19) {
////            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
////        }
////        //make fully Android Transparent Status bar
////        if (Build.VERSION.SDK_INT >= 21) {
////            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
////            getWindow().setStatusBarColor(Color.TRANSPARENT);
////        }
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.settings);
//
//        recyclerView = findViewById(R.id.recycler_view);
//
//        mAdapter = new SingleRoomAdapter(roomList, getApplicationContext());
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
//
//        prepareRoomData();
//    }

    private void prepareRoomData() {
        Room room = new Room("1", "Night mode");
        roomList.add(room);
//        room = new Room("2", "Fan");
//        roomList.add(room);

        mAdapter.notifyDataSetChanged();
    }

//    public void onBackClicked(View view) {
//
//
////        startActivity(new Intent(getContext(), MainActivity.class));
//    }
}

package com.insacvl.sefkim_flickr.adapters;


import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.model.Room;
import com.insacvl.sefkim_flickr.utils.SwitchButton;

import java.util.List;


public class SingleRoomAdapter extends RecyclerView.Adapter<SingleRoomAdapter.MyViewHolder> {

    Context context;
    private View itemView;
    private List<Room> roomList;
    SwitchButton darkModeSwitch;
    private SharedPreferences sharedPreferences;

    public SingleRoomAdapter(List<Room> roomList, Context context,SharedPreferences sp) {
        this.roomList = roomList;
        this.sharedPreferences=sp;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_room_row, parent, false);
        darkModeSwitch = itemView.findViewById(R.id.theme_switch_);
        darkModeSwitch.setChecked(sharedPreferences.getBoolean("darkMode",true));

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Room room = roomList.get(position);

        holder.title.setText(room.getName());

        if(room.getName().equals("Night mode")){
            itemView.findViewById(R.id.setting_item_drawable).setBackground(ContextCompat.getDrawable(context,R.drawable.ic_night_mode_1));TextView tv = (TextView)itemView.findViewById(R.id.title);tv.setText(room.getName().toString());
            darkModeSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        darkModeSwitch=view.findViewById(R.id.theme_switch);
                        darkModeSwitch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                if(darkModeSwitch.isChecked()){
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putBoolean("darkMode",true);
                                    editor.apply();
                                    darkModeSwitch.setChecked(true);
                                }else{
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                                    SharedPreferences.Editor editor=sharedPreferences.edit();
                                    editor.putBoolean("darkMode",false);
                                    editor.apply();
                                    darkModeSwitch.setChecked(false);
                                }

                            }
                        });

                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            cardView = view.findViewById(R.id.card_view);

        }
    }
}
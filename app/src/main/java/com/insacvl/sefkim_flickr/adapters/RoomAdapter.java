package com.insacvl.sefkim_flickr.adapters;


import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.model.Room;

import java.util.List;


public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.MyViewHolder> {

    Context context;
    View itemView;
    private List<Room> roomList;

    public RoomAdapter(List<Room> roomList, Context context) {
        this.roomList = roomList;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Room room = roomList.get(position);

        holder.title.setText(room.getName());
        
        if(room.getName().equals("FASHION")){itemView.findViewById(R.id.card_bg).setBackground(ContextCompat.getDrawable(context,R.drawable.fashion_bg));TextView tv = (TextView)itemView.findViewById(R.id.subtitle);tv.setText("Explore "+room.getName().toString().toLowerCase()+" category.");}
        if(room.getName().equals("TECH")){itemView.findViewById(R.id.card_bg).setBackground(ContextCompat.getDrawable(context,R.drawable.tech_bg));TextView tv = (TextView)itemView.findViewById(R.id.subtitle);tv.setText("Explore "+room.getName().toString().toLowerCase()+" category.");}
        if(room.getName().equals("PHOTOGRAPHY")){itemView.findViewById(R.id.card_bg).setBackground(ContextCompat.getDrawable(context,R.drawable.photography_bg));TextView tv = (TextView)itemView.findViewById(R.id.subtitle);tv.setText("Explore "+room.getName().toString().toLowerCase()+" category.");}
        if(room.getName().equals("SPORTS")){itemView.findViewById(R.id.card_bg).setBackground(ContextCompat.getDrawable(context,R.drawable.sports_bg));TextView tv = (TextView)itemView.findViewById(R.id.subtitle);tv.setText("Explore "+room.getName().toString().toLowerCase()+" category.");}
        if(room.getName().equals("FOOD")){itemView.findViewById(R.id.card_bg).setBackground(ContextCompat.getDrawable(context,R.drawable.foog_bg));TextView tv = (TextView)itemView.findViewById(R.id.subtitle);tv.setText("Explore "+room.getName().toString().toLowerCase()+" category.");}

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                Log.e(TAG, "onClick Room Adapter: "+context.getApplicationContext().toString());
                Bundle bundle = new Bundle();
                bundle.putString("tag", room.getName().toString().toLowerCase());
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_navigation_home_to_navigation_search,bundle);
                Toast t = Toast.makeText(context,"Exploring "+room.getName()+" category.", Toast.LENGTH_SHORT);
                t.show();
            }
        });

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
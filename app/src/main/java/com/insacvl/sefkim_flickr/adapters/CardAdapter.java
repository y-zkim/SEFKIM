/**
* @Author : ZKIM Youssef
*/
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
import com.insacvl.sefkim_flickr.model.Card;

import java.util.List;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                         CardAdapter                                             *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * The class CardAdapter is used to manage the category browsing option, it takes the cards list   *
 * and the context as a parameter to create the cards out of the informations given.               *
 * It is used to create the cards visible at home screen and handles the click to perform a search *
 * based on the category of the selected card.                                                     *
 *                                                                                                 *
 *=================================================================================================*

 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> {

    Context context;
    View itemView;
    private List<Card> cardList;

    // constructor for the adapter, takes in a list of Card objects and a context
    public CardAdapter(List<Card> cardList, Context context) {
        this.cardList = cardList;
        this.context = context;
    }

    // creates a view holder for each item in the list
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflates the home_row layout to be used for each item in the list
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_row, parent, false);

        return new MyViewHolder(itemView);
    }

    // binds the data from the Card object to the view holder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Card card = cardList.get(position);
        // sets the title text to the name of the Card object
        holder.title.setText(card.getName());
        // sets the background and subtitle text based on the Card object's name
        if(card.getName().equals("FASHION")){itemView.findViewById(R.id.card_bg).setBackground(ContextCompat.getDrawable(context,R.drawable.fashion_bg));TextView tv = (TextView)itemView.findViewById(R.id.subtitle);tv.setText("Explore "+ card.getName().toString().toLowerCase()+" category.");}
        if(card.getName().equals("TECH")){itemView.findViewById(R.id.card_bg).setBackground(ContextCompat.getDrawable(context,R.drawable.tech_bg));TextView tv = (TextView)itemView.findViewById(R.id.subtitle);tv.setText("Explore "+ card.getName().toString().toLowerCase()+" category.");}
        if(card.getName().equals("PHOTOGRAPHY")){itemView.findViewById(R.id.card_bg).setBackground(ContextCompat.getDrawable(context,R.drawable.photography_bg));TextView tv = (TextView)itemView.findViewById(R.id.subtitle);tv.setText("Explore "+ card.getName().toString().toLowerCase()+" category.");}
        if(card.getName().equals("SPORTS")){itemView.findViewById(R.id.card_bg).setBackground(ContextCompat.getDrawable(context,R.drawable.sports_bg));TextView tv = (TextView)itemView.findViewById(R.id.subtitle);tv.setText("Explore "+ card.getName().toString().toLowerCase()+" category.");}
        if(card.getName().equals("FOOD")){itemView.findViewById(R.id.card_bg).setBackground(ContextCompat.getDrawable(context,R.drawable.foog_bg));TextView tv = (TextView)itemView.findViewById(R.id.subtitle);tv.setText("Explore "+ card.getName().toString().toLowerCase()+" category.");}

        // sets an onClickListener for the cardView, which navigates to the search page and shows a Toast message
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                // Setting a bundle to send data to the new destination (search)
                // This will open the search fragment and search for the card name so it shows only
                // the images related to the topic
                Bundle bundle = new Bundle();
                bundle.putString("searchTag",card.getName());
                NavController navController = Navigation.findNavController(v);
                navController.navigate(R.id.action_navigation_home_to_navigation_search,bundle);
                // toast showing the actual category
                Toast t = Toast.makeText(context,"Exploring "+ card.getName()+" category.", Toast.LENGTH_SHORT);
                t.show();
            }
        });

    }
    // returns the number of items in the list
    @Override
    public int getItemCount() {
        return cardList.size();
    }
    // inner class for the view holder
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
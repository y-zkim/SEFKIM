package com.insacvl.sefkim_flickr.adapters;

/**
* @Author : ZKIM Youssef
*/

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
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
 *                                       SingleRowAdapter                                          *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * SingleRowAdapter is an adapter class for a RecyclerView that displays a list of cards. The class*
 * takes in a list of card objects, context, and shared preferences as parameters.                 *
 * The onCreateViewHolder() method is used to inflate the layout for the single row and find the   *
 * dark mode switch. The onBindViewHolder() method is used to bind data to the view holder, where  *
 * if the card is the night mode card, it sets the icon for the night mode card, the text of the   *
 * night mode card, and an on checked change listener for the switch, where if the switch is       *
 * checked it set the night mode to yes,put the boolean value of true for the "darkMode" key in the*
 * shared preferences,apply the changes to the shared preferences and set the switch to checked and*
 * if it is not checked it set the night mode to no,put the boolean value of false for the         *
 * "darkMode" key in the shared preferences,apply the changes to the shared preferences and set the*
 * switch to unchecked.                                                                            *
 *                                                                                                 *
 *=================================================================================================*

 */

public class SingleRowAdapter extends RecyclerView.Adapter<SingleRowAdapter.MyViewHolder> {
    //context variable to hold the context of the activity that calls this adapter
    Context context;
    //View variable to hold the item view
    private View itemView;
    //List of card objects to hold the data to be displayed
    private List<Card> cardList;
    //Switch variable to hold the dark mode switch
    Switch darkModeSwitch;
    //SharedPreferences variable to hold shared preferences
    private SharedPreferences sharedPreferences;

    //constructor that takes in a list of cards, context, and shared preferences
    public SingleRowAdapter(List<Card> cardList, Context context, SharedPreferences sp) {
        this.cardList = cardList;
        this.sharedPreferences = sp;
        this.context = context;
    }

    //override method that creates a new view holder for each item in the list
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout for the single row
        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_room_row, parent, false);
        //find the dark mode switch
        darkModeSwitch = itemView.findViewById(R.id.theme_switch_);
        //set the switch to the value stored in shared preferences
        darkModeSwitch.setChecked(sharedPreferences.getBoolean("darkMode", false));
        //return a new view holder
        return new MyViewHolder(itemView);
    }

    //override method that binds data to the view holder
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //get the card object at the current position
        Card card = cardList.get(position);
        //set the title of the card
        holder.title.setText(card.getName());

        //check if the card is the night mode card
        if (card.getName().equals("Night mode")) {
            //set the icon for the night mode card
            itemView.findViewById(R.id.setting_item_drawable).setBackground(ContextCompat.getDrawable(context, R.drawable.ic_night_mode_1));
            //set the text of the night mode card
            TextView tv = (TextView) itemView.findViewById(R.id.title);
            tv.setText(card.getName().toString());
            //set an on checked change listener for the switch
            darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                    //if the switch is checked
                    if (checked) {
                        //set the night mode to yes
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        //get the editor for the shared preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        //put the boolean value of true for the "darkMode" key in the shared preferences
                        editor.putBoolean("darkMode", true);
                        //apply the changes to the shared preferences
                        editor.apply();

                        darkModeSwitch.setChecked(true);
                    } else {
                        // Same treatment as in the if clause, the difference is that we're setting mode to light.
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("darkMode", false);
                        editor.apply();
                        darkModeSwitch.setChecked(false);
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return cardList.size();
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
package com.insacvl.sefkim_flickr.view;
/**
* @Author : ZKIM Youssef
*/
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.adapters.SingleRowAdapter;
import com.insacvl.sefkim_flickr.model.Card;

import java.util.ArrayList;
import java.util.List;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                        SettingsActivity                                         *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * This class will inflate the settings layout and call the singleRow adapter to create the rows of*
 * settings available (the data for the rows are passed with method prepareRoomData                *
 *                                                                                                 *
 *=================================================================================================*

 */
public class SettingsActivity extends Fragment {
    private List<Card> cardList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SingleRowAdapter mAdapter;
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

        mAdapter = new SingleRowAdapter(cardList, getContext(),getActivity().getPreferences(Context.MODE_PRIVATE));
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareRoomData();
        return settingsView;
    }

    private void prepareRoomData() {
        Card card = new Card("1", "Night mode");
        cardList.add(card);
//        room = new Room("2", "Fan");
//        roomList.add(room);

        mAdapter.notifyDataSetChanged();
    }
}

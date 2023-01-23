package com.insacvl.sefkim_flickr.view;

/**
* @Author : ZKIM Youssef
*/

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.insacvl.sefkim_flickr.R;
import com.insacvl.sefkim_flickr.adapters.CardAdapter;
import com.insacvl.sefkim_flickr.model.Card;

import java.util.ArrayList;
import java.util.List;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                         HomeFragment                                            *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * HomeFragment calls the CardAdapter for the categories list by passing the necessary data provided  *
 * by the prepareRoomData() method                                                                 *
 *                                                                                                 *
 *=================================================================================================*

 */

public class HomeFragment extends Fragment {
    private View homeView;

    private List<Card> cardList = new ArrayList<>();
    private RecyclerView recyclerView;
    private CardAdapter mAdapter;


    @SuppressLint("UseCompatLoadingForDrawables")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView = homeView.findViewById(R.id.recycler_view);
        mAdapter = new CardAdapter(cardList, getActivity().getApplicationContext());
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
    }

    private void prepareRoomData() {
        Card card = new Card("1", "FASHION");
        cardList.add(card);
        card = new Card("2", "TECH");
        cardList.add(card);
        card = new Card("1", "PHOTOGRAPHY");
        cardList.add(card);
        card = new Card("2", "SPORTS");
        cardList.add(card);
        card = new Card("1", "FOOD");
        cardList.add(card);

        mAdapter.notifyDataSetChanged();
    }
}

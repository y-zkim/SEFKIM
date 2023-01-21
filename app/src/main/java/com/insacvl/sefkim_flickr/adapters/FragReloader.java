package com.insacvl.sefkim_flickr.adapters;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.insacvl.sefkim_flickr.view.FavouriteFragment;
/**
* @Author : ZKIM Youssef
*/
public class FragReloader extends FragmentManager {
    public void FavFragReloader() {
        System.err.println("=============> inside fav frag reloader");
        FavouriteFragment fragment = (FavouriteFragment) findFragmentByTag("favourites_fragment");
        System.err.println("hello line 14");
        FragmentTransaction ft= beginTransaction();
        System.err.println("hello line 15");
        assert fragment != null;
        System.err.println("before last line in favourites reloader");
        ft.detach(fragment).attach(fragment).commit();
        System.err.println("normalement tout le code a été executé\n======================");
    }
}

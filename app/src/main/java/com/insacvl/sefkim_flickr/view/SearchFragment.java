package com.insacvl.sefkim_flickr.view;
/**
* @Author : ZKIM Youssef
*/
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.Fragment;

import com.insacvl.sefkim_flickr.utils.FlickrAPI;
import com.insacvl.sefkim_flickr.R;

import java.util.Objects;

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                          SearchFragment                                         *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * The details have the image, it's description, as well as the like button and the download.      *
 * Those features are handled by this class by calling the necessary adapter and putting listeners.*
 *                                                                                                 *
 *=================================================================================================*

 */

public class SearchFragment extends Fragment {
    private SearchView searchView;
    private ImageView image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        searchView = view.findViewById(R.id.search_view);
        image= searchView.findViewById(R.id.imageView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                performSearch(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if(query.equals("")) onQueryTextSubmit("");
                performSearch(query);
                return false;
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle = getArguments();
        performSearch(Objects.isNull(bundle)?
                "":
                bundle.getString("searchTag"));
    }

    /**
     * This function performs the search and send the results to the adapter in order to update the listView with results
     * @param searchTerm
     */


    public void performSearch(String searchTerm) {
        new FlickrAPI(getView().findViewById(R.id.listview), getContext()).execute(searchTerm);
    }


}

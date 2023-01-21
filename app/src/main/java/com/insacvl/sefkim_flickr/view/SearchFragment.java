package com.insacvl.sefkim_flickr.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.SearchView;

import androidx.fragment.app.Fragment;

import com.insacvl.sefkim_flickr.utils.FlickrImages;
import com.insacvl.sefkim_flickr.R;

public class SearchFragment extends Fragment {
    private SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        searchView = view.findViewById(R.id.search_view);
//        Button download_button = searchView.findViewById(R.id.download_button);
//        download_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String url = "http://www.google.com";
//                Intent i = new Intent(Intent.ACTION_VIEW);
//                i.setData(Uri.parse(url));
//                startActivity(i);
//            }
//        });
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
//        favouriteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LinearLayout linearLayout = (LinearLayout) view.getParent();
//                linearLayout.getChildAt(1);
//                System.out.println("linearLayout.getChildAt(1) = " + linearLayout.getChildAt(1));
//                System.out.println("linearLayout.getChildAt(2) = " + linearLayout.getChildAt(2));
//            }
//        });
        return view;
    }

    /**
     * This function performs the search and send the results to the adapter in order to update the listView with results
     * @param searchTerm
     */
    public void performSearch(String searchTerm) {
        new FlickrImages(getView().findViewById(R.id.listview), getContext()).execute(searchTerm);
    }


}

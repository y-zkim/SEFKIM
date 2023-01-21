package com.insacvl.sefkim_flickr.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.insacvl.sefkim_flickr.R;

public class OptionsFragment extends Fragment {

    private Button searchImageButton;
    private Button favouritImageButton;
    private Button otherAppsButton;
    private Button settingsButton;
    private FragmentManager fragmentManager;

    public OptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_options, container, false);

        // Initialize buttons
        favouritImageButton = view.findViewById(R.id.favourites_button);
        searchImageButton= view.findViewById(R.id.search_image_button);
        otherAppsButton = view.findViewById(R.id.other_apps_button);
        settingsButton = view.findViewById(R.id.settings_button);
        // Set onClickListeners for buttons
        fragmentManager = getActivity().getSupportFragmentManager();
        searchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                navigateToFragment(new SearchFragment());
                System.out.println("Hello You just clicked the search button");
            }
        });

        favouritImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello You just clicked the favourite button");
            }
        });

        otherAppsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello You just clicked the otherApps button");
            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Hello You just clicked the otherApps button");
            }
        });

        return view;
    }
//    public void navigateToFragment(Fragment fragment) {
//        System.out.println("from navigate");
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, fragment)
//                .addToBackStack(null)
//                .commit();
//    }
}

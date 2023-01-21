package com.insacvl.sefkim_flickr.view;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.insacvl.sefkim_flickr.R;

public class HomeFragment extends Fragment {
    private View homeView;
    private Switch theme_switcher;
    private SharedPreferences sharedPreferences;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        homeView = inflater.inflate(R.layout.fragment_home,container,false);
        // Save switch state in shared preferences
        theme_switcher=homeView.findViewById(R.id.theme_switch);
        sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
//        System.err.println("actual state of the switch ========> " + sharedPreferences.getBoolean("save"));
        theme_switcher.setChecked(sharedPreferences.getBoolean("darkMode",true));
        return homeView;
    }

    @Override
    public void onStart() {
        super.onStart();
        theme_switcher=homeView.findViewById(R.id.theme_switch);
        theme_switcher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(theme_switcher.isChecked()){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("darkMode",true);
                    editor.apply();
                    theme_switcher.setChecked(true);
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("darkMode",false);
                    editor.apply();
                    theme_switcher.setChecked(false);
                }

            }
        });



    }
}

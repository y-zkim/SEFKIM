package com.insacvl.sefkim_flickr;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.insacvl.sefkim_flickr.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Button homeButton;
    ImageButton settings;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("darkMode", false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.action_navigation_home_to_settingsFragment,R.id.navigation_home, R.id.navigation_category, R.id.navigation_search, R.id.action_mainActivity_to_settingsFragment,R.id.action_navigation_category_to_imageDetailsFragment, R.id.action_navigation_search_to_imageDetailsFragment,R.id.action_navigation_search_to_imageDetailsFragment,R.id.action_mainActivity_to_settingsFragment)
                .build();
        System.out.println("appBarConfiguration = " + appBarConfiguration);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        System.out.println("navController = " + navController);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        settings = findViewById(R.id.settingsButton);
        settings.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                NavController navController = Navigation.findNavController(MainActivity.this, R.id.nav_host_fragment);
                navController.navigate(R.id.settingsFragment);
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
//        return super.onSupportNavigateUp();
        /**
         *   NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
         *           return NavigationUI.navigateUp(navController, appBarConfiguration)
         *                             || super.onSupportNavigateUp();
         */
        NavController navController=Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
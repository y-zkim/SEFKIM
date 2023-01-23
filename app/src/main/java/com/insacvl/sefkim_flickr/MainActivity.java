package com.insacvl.sefkim_flickr;
/**
* @Author : ZKIM Youssef
*/
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

/*
 *=================================================================================================*
 *                                  Developed by : ZKIM Youssef                                    *
 *=================================================================================================*
 *=================================================================================================*
 *                                                                                                 *
 *                                          MainActivity                                           *
 *                                                                                                 *
 *=================================================================================================*
 * Class Description                                                                               *
 * ----------------                                                                                *
 * This class represents the MainActivity, which serves as the main entry point for the app.       *
 * The Activity uses a BottomNavigationView to navigate between different fragments.               *
 * It also has a settings button which when clicked navigates to the settings fragment.            *
 * It also retrieves the shared preference for dark mode and sets the mode accordingly.            *
 * The class also handles the navigation up event.                                          *
 *                                                                                                 *
 *=================================================================================================*

 */

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Button homeButton;
    ImageButton settings;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getting the dark mode state from the shared preferences
        SharedPreferences sharedPreferences=getPreferences(Context.MODE_PRIVATE);
//        setting the dark mode accordingly
        if(sharedPreferences.getBoolean("darkMode", false)){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
//        loading the main activity
        setContentView(R.layout.activity_main);
//        loading the navigation bar
//        Find the BottomNavigationView and set the appBarConfiguration
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each menu should be considered as top level destinations.
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.action_navigation_home_to_settingsFragment,R.id.navigation_home, R.id.navigation_category, R.id.navigation_search, R.id.action_mainActivity_to_settingsFragment,R.id.action_navigation_category_to_imageDetailsFragment, R.id.action_navigation_search_to_imageDetailsFragment,R.id.action_navigation_search_to_imageDetailsFragment,R.id.action_mainActivity_to_settingsFragment)
                .build();
//        System.out.println("appBarConfiguration = " + appBarConfiguration);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        System.out.println("navController = " + navController);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
//      handling the settings icon in the main actibity
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

    /**
     * This method handles the navigation up event.
     * @return true if the event is handled, false otherwise
     */
    @Override
    public boolean onSupportNavigateUp() {
//        return super.onSupportNavigateUp();
        NavController navController=Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
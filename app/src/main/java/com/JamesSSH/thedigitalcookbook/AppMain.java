package com.JamesSSH.thedigitalcookbook;

import static com.JamesSSH.thedigitalcookbook.R.id.b_Search;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.JamesSSH.thedigitalcookbook.databinding.ActivityAppMainBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class AppMain extends AppCompatActivity {

    private ActivityAppMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityAppMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_Search, R.id.navigation_saved, R.id.navigation_Settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_app_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);


    }



}
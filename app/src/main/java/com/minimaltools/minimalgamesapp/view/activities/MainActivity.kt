package com.minimaltools.minimalgamesapp.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.minimaltools.minimalgamesapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menu: BottomNavigationView = findViewById(R.id.bottom_navigation)
        NavigationUI.setupWithNavController(
            menu,
            Navigation.findNavController(this, R.id.fragment_container)
        )
    }
}
package com.example.pro_valyuta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController:NavController

    private lateinit var drawerToggle: ActionBarDrawerToggle
    private lateinit var toolbar: Toolbar
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val bottomanavgation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val navigationView = findViewById<NavigationView>(R.id.navigation_view)
        val toolbarrr = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar_main)

        drawerLayout = findViewById(R.id.drawer_layout)



        setSupportActionBar(toolbarrr)

        drawerToggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbarrr,
            R.string.open, R.string.close
        )

        drawerToggle.isDrawerIndicatorEnabled = true
        drawerLayout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()


        navigationView.setNavigationItemSelectedListener { menuItem ->


            when (menuItem.itemId) {
                R.id.home_menu -> {

                    drawerLayout.closeDrawers()
                }
                R.id.profile_menu -> {
                    Toast.makeText(this, "Done by Mr_KadiroFF", Toast.LENGTH_LONG).show()
                    drawerLayout.closeDrawers()
                }

            }
            true
        }


        //bottom navigation
        navController = findNavController(R.id.hostFragment)
        bottomanavgation.setupWithNavController(navController)
        bottomanavgation.itemIconTintList = null
//
//        //Navigaiton UP button
//        appBarConfiguration = AppBarConfiguration(navController.graph,drawer_layout)
//        NavigationUI.setupActionBarWithNavController(this,navController,drawer_layout)
//
//        //Drawer navigation
//        NavigationUI.setupWithNavController(navigationView,navController)

    }

//    override fun onSupportNavigateUp(): Boolean {
//        return NavigationUI.navigateUp(navController,appBarConfiguration)
//    }
}
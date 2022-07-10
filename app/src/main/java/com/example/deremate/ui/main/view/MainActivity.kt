package com.example.deremate.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.deremate.R
import com.example.deremate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Setting Splash Screen
        installSplashScreen()

        //Setting Layout and Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Setting Custom App Bar
        setSupportActionBar(binding.topAppBar)

        //Setting Jetpack Navigation Components
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        //Setting Menu Drawer Items
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_main,
                R.id.nav_search,
                R.id.nav_purchases,
                R.id.nav_offers,
                R.id.nav_history,
                R.id.nav_account,
                R.id.nav_help
            ), binding.drawerMain
        )

        //Setting NavigationView to NavController
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navigationView.setupWithNavController(navController)

        //Setting NavigationHeader's Button Clickable
        val btnMenuLogIn = binding.navigationView.getHeaderView(0).findViewById<Button>(R.id.btnMenuLogIn)
        btnMenuLogIn.setOnClickListener { navController.navigate(R.id.action_nav_main_to_nav_login) }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.top_app_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return  item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }
}
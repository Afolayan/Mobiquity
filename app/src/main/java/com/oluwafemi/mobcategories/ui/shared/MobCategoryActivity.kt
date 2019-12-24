package com.oluwafemi.mobcategories.ui.shared

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.oluwafemi.mobcategories.R
import dagger.android.support.DaggerAppCompatActivity

class MobCategoryActivity : DaggerAppCompatActivity() {

    private lateinit var appBarConfig: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController = Navigation.findNavController(this, R.id.home_main_nav)
        NavigationUI.setupActionBarWithNavController(this, navController, null)
        appBarConfig = AppBarConfiguration.Builder(navController.graph).build()

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, appBarConfig)
    }

}

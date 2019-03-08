package com.kuxu.meetups

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext

class MainActivity : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = NavHostFragment.findNavController(my_nav_host_fragment)

        val navigationModule =
            module(override = true) {
                single { navController }
            }

        StandAloneContext.loadKoinModules(
            navigationModule
        )

        setSupportActionBar(findViewById(R.id.main_menu_tool_bar))
    }
}
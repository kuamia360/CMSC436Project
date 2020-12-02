package com.example.stompcovidrumors

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.view.View
import com.google.android.material.bottomnavigation.LabelVisibilityMode

class SearchActivity : Activity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> bottomNavigationListener(item) }
        bottomNavigationView.menu.getItem(1).isChecked = true
    }

    private fun bottomNavigationListener(item : MenuItem) : Boolean {
        when(item.itemId) {
            R.id.home -> {
                // Respond to navigation item 1 click
                val homeIntent = Intent(this, HomeActivity::class.java)
                startActivity(homeIntent)
                return true
            }
            R.id.search -> {
                // Respond to navigation item 2 click
                return true
            }
            R.id.new_post -> {
                // Respond to navigation item 1 click
                val postIntent = Intent(this, NewPostActivity::class.java)
                item.isChecked = true
                startActivity(postIntent)
                return true
            }
            R.id.profile -> {
                // Respond to navigation item 2 click
                val profileIntent = Intent(this, ProfileActivity::class.java)
                startActivity(profileIntent)
                return true
            }
            else -> return false
        }
        return false
    }
}
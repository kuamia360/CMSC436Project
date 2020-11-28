package com.example.stompcovidrumors

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent

class SearchActivity : Activity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> bottomNavigationListener(item) }
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
                return true
            }
            R.id.profile -> {
                // Respond to navigation item 2 click
                return true
            }
            else -> return false
        }
        return false
    }
}
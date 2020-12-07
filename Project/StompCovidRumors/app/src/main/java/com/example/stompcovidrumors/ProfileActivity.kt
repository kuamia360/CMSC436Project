package com.example.stompcovidrumors

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode

class ProfileActivity : Activity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
        bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> bottomNavigationListener(item) }
        bottomNavigationView.menu.getItem(3).isChecked = true
    }

    private fun bottomNavigationListener(item : MenuItem) : Boolean {
        val user = intent.getStringExtra("user")
        when(item.itemId) {
            R.id.home -> {
                val homeIntent = Intent(this, HomeActivity::class.java)
                homeIntent.putExtra("user", user)
                startActivity(homeIntent)
                return true
            }
            R.id.search -> {
                val searchIntent = Intent(this, SearchActivity::class.java)
                searchIntent.putExtra("user", user)
                startActivity(searchIntent)
                return true
            }
            R.id.new_post -> {
                val postIntent = Intent(this, NewPostActivity::class.java)
                postIntent.putExtra("user", user)
                startActivity(postIntent)
                return true
            }
            R.id.profile -> {
                return true
            }
            else -> return true
        }
        return false
    }
}
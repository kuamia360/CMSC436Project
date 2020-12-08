package com.example.stompcovidrumors

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import android.util.Log
import android.widget.Toast


class ProfileActivity : Activity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var saveButton : Button
//    private lateinit var setUserName : EditText
    private lateinit var setPassword : EditText
    private lateinit var logoutBtn: Button
    var user = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)
//        setUserName = findViewById<EditText>(R.id.profile_username)
        setPassword = findViewById<EditText>(R.id.profile_password)
        saveButton = findViewById<Button>(R.id.save_button)
        logoutBtn = findViewById<View>(R.id.logout_button) as Button
        bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> bottomNavigationListener(item) }
        bottomNavigationView.menu.getItem(3).isChecked = true
        saveButton.setOnClickListener { saveUserInfo() }
        logoutBtn.setOnClickListener{ logout() }
    }

    private fun logout() {
        val mainIntent = Intent(this, MainActivity::class.java)
        startActivity(mainIntent)
    }

    fun saveUserInfo() {

        if (user != null && setPassword.text.toString().isNotEmpty()) {
            val newPassword = setPassword.text.toString()

            user!!.updatePassword(newPassword).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("TAG", "User profile updated.")
                        Toast.makeText(
                            this, "Password changed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        setPassword.setText("")
                    }
                    else {
                        Toast.makeText(
                            this, task.exception.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
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


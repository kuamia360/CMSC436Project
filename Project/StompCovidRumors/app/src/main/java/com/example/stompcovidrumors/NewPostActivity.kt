package com.example.stompcovidrumors

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Button
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class NewPostActivity : Activity() {
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var addPostButton: Button
    private lateinit var editTextWhere: EditText
    private lateinit var editTextWhat: EditText

    private lateinit var databaseUsers: DatabaseReference
//    internal lateinit var posts: MutableList<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_post)

        editTextWhat = findViewById<View>(R.id.what) as EditText
        editTextWhere = findViewById<View>(R.id.where) as EditText
        addPostButton = findViewById<View>(R.id.submit_post_button) as Button

        databaseUsers = FirebaseDatabase.getInstance().getReference("stompcovidrumors")
        addPostButton.setOnClickListener{ addNewPost() }

        bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> bottomNavigationListener(item) }
        bottomNavigationView.menu.getItem(2).isChecked = true
    }

    private fun addNewPost() {
        val whatInfo = editTextWhat.text.toString().trim { it <= ' ' }
        val whereInfo = editTextWhere.text.toString().trim { it <= ' ' }

        if (!TextUtils.isEmpty(whatInfo) && !TextUtils.isEmpty(whereInfo)) {
            val id = (databaseUsers.push()).key.toString()
            var user = intent.getStringExtra("user")
            if (user == null) {
                user = id
            }
            val post = Post(user, whatInfo, whereInfo)
            databaseUsers.child(id).setValue(post)
            Toast.makeText(this, "Post saved", Toast.LENGTH_LONG).show()
            editTextWhat.setText("")
            editTextWhere.setText("")
        } else {
            Toast.makeText(this, "Please enter the rumor's what/where details", Toast.LENGTH_LONG).show()
        }
    }


    private fun bottomNavigationListener(item : MenuItem) : Boolean {
        val user = intent.getStringExtra("user")
        when(item.itemId) {
            R.id.home -> {
                // Respond to navigation item 1 click
                val homeIntent = Intent(this, HomeActivity::class.java)
                homeIntent.putExtra("user", user)
                startActivity(homeIntent)
                return true
            }
            R.id.search -> {
                // Respond to navigation item 2 click
                val searchIntent = Intent(this, SearchActivity::class.java)
                searchIntent.putExtra("user", user)
                startActivity(searchIntent)
                return true
            }
            R.id.new_post -> {
                // Respond to navigation item 1 click
                return true
            }
            R.id.profile -> {
                // Respond to navigation item 2 click
                val profileIntent = Intent(this, ProfileActivity::class.java)
                profileIntent.putExtra("user", user)
                startActivity(profileIntent)
                return true
            }
            else -> return true
        }
        return false
    }
}
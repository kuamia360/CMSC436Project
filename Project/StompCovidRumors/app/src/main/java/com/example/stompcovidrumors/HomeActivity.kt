package com.example.stompcovidrumors

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ListView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.firebase.database.*
import java.lang.Exception
import java.util.ArrayList


class HomeActivity : Activity() {

    companion object {
        const val TAG = "Example-FirebaseRealtimeDatabase"
        const val AUTHOR_NAME = "course.examples.firebase.myhomelibrary.authorname"
        const val AUTHOR_ID = "course.examples.firebase.myhomelibrary.authorid"
    }

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var databasePosts: DatabaseReference
    private lateinit var posts: MutableList<Post>
    private lateinit var listViewPosts: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        databasePosts = FirebaseDatabase.getInstance().getReference("stompcovidrumors")
        posts = ArrayList()
        bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        listViewPosts = findViewById<View>(R.id.listViewHome) as ListView
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> bottomNavigationListener(item) }
        bottomNavigationView.menu.getItem(0).isChecked = true
    }

    override fun onStart() {
        super.onStart()
        //attaching value event listener
        databasePosts.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                //clearing the previous artist list
                posts.clear()

                var post: Post? = null
                //iterating through all the nodes
                for (postSnapshot in dataSnapshot.children) {
                    try {
                        //getting artist
                        post = postSnapshot.getValue(Post::class.java)

                    } catch (e: Exception) {
                        //catch exception where the author is not an author
                        Log.e(TAG, e.toString())
                    } finally {
                        //adding author to the list
                        posts.add(post!!)
                    }
                }

                posts = posts.asReversed()
                //creating adapter
                val postAdapter = PostsList(this@HomeActivity, posts)
                //attaching adapter to the listview
                listViewPosts.adapter = postAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
    }

    private fun bottomNavigationListener(item : MenuItem) : Boolean {
        val user = intent.getStringExtra("user")

        when(item.itemId) {
            R.id.home -> {
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
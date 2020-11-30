package com.example.stompcovidrumors

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.util.Log
import android.widget.ListView
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import com.google.firebase.database.*
import java.lang.Exception


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
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> bottomNavigationListener(item) }
        bottomNavigationView.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
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
        item.isChecked = true
        when(item.itemId) {
            R.id.home -> {
                // Respond to navigation item 1 click
                return true
            }
            R.id.search -> {
                // Respond to navigation item 2 click
                val searchIntent = Intent(this, SearchActivity::class.java)
                startActivity(searchIntent)
                return true
            }
            R.id.new_post -> {
                // Respond to navigation item 1 click
                val postIntent = Intent(this, NewPostActivity::class.java)
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
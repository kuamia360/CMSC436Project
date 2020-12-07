package com.example.stompcovidrumors

import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.SearchView
import com.google.firebase.database.*
import java.lang.Exception
import java.util.ArrayList

class SearchActivity : Activity() {
    companion object {
        const val TAG = "Example-FirebaseRealtimeDatabase"
        const val AUTHOR_NAME = "course.examples.firebase.myhomelibrary.authorname"
        const val AUTHOR_ID = "course.examples.firebase.myhomelibrary.authorid"
    }

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var searchView: SearchView
    private lateinit var posts: MutableList<Post>
    private lateinit var searchResults: MutableList<Post>
    private lateinit var databasePosts: DatabaseReference
    private lateinit var listViewPosts: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search)
        databasePosts = FirebaseDatabase.getInstance().getReference("stompcovidrumors")
        posts = ArrayList()
        searchResults = ArrayList()

        searchView = findViewById<View>(R.id.simpleSearchView) as SearchView
        listViewPosts = findViewById<View>(R.id.searchList) as ListView
        bottomNavigationView = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> bottomNavigationListener(item) }
        bottomNavigationView.menu.getItem(1).isChecked = true
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
                        //getting post
                        post = postSnapshot.getValue(Post::class.java)

                    } catch (e: Exception) {
                        //catch exception where the post is not an post
                        Log.e(HomeActivity.TAG, e.toString())
                    } finally {
                        //adding author to the list
                        posts.add(post!!)
                    }
                }

                posts = posts.asReversed()
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextChange(newText: String): Boolean {
                        return false
                    }

                    override fun onQueryTextSubmit(query: String): Boolean {
                        if (searchResults.size > 0) {
                            searchResults.clear()
                            val tempAdapt = PostsList(this@SearchActivity, searchResults)
                            listViewPosts.adapter = tempAdapt
                        }
                      searchResults.clear()
                        for (i in 0 until posts.size) {
                          //  Log.i(posts[i].authorLocation," In here3")
                            if (posts[i].authorLocation.equals(query, true)) {
                               // Log.i("Yo: " + posts[i].authorLocation," In here man")
                                searchResults.add(posts[i]!!)
                            }
                        }
                        return true
                    }

                })
                //Log.i("Yo: " + searchResults.size,", should be 2")
//                searchResults = searchResults.asReversed()
                //creating adapter
                val postAdapter = PostsList(this@SearchActivity, searchResults)
                //attaching adapter to the listview
                listViewPosts.adapter = postAdapter
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })
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
package com.example.stompcovidrumors

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.toColorInt
import com.google.firebase.database.*

class PostsList(private val context: Activity, private var posts: List<Post>) :
    ArrayAdapter<Post>(context, R.layout.post_layout, posts) {
    private var user = ""
    private var databasePosts = FirebaseDatabase.getInstance().getReference("stompcovidrumors")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem =
            inflater.inflate(R.layout.post_layout, parent, false)

        val nameView = listViewItem.findViewById<TextView>(R.id.textViewName)
        val whatView = listViewItem.findViewById<TextView>(R.id.textViewWhat)
        val whereView = listViewItem.findViewById<TextView>(R.id.textViewWhere)
        val upvoteView = listViewItem.findViewById<TextView>(R.id.upvote_count)
        val upVoteImageView = listViewItem.findViewById<ImageView>(R.id.upvote_image)
        val downVoteImageView = listViewItem.findViewById<ImageView>(R.id.downvote_image)
        val approved = listViewItem.findViewById<TextView>(R.id.approved)
        val post = posts[position]

        upVoteImageView.setOnClickListener { upVote(post) }
        downVoteImageView.setOnClickListener { downVote(post) }

        whatView.text = "Rumor: " + post.content
        whereView.text = "Location: " + post.authorLocation
        nameView.text = post.authorName
        upvoteView.text = (post.upVotesArray.size - post.downVotesArray.size).toString()

        if (user.contains("umd.edu")) {
            approved.setOnClickListener { changeApproval(post) }
        }

        if (post.approved) {
            approved.text = "Approved"
            approved.setTextColor(Color.parseColor("#00ff00"))
        }
        else {
            approved.text = "Not Approved"
            approved.setTextColor(Color.parseColor("#ff0000"))
        }

        return listViewItem
    }

    public fun setCurrentUser(currUser : String) {
        user = currUser
    }

    private fun changeApproval(post : Post) {
        post.approved = !post.approved
        databasePosts.child(post.postId).setValue(post)
    }

    private fun upVote(post : Post) {
        if (!post.upVotesArray.contains(user)) {
            post.upVotesArray.add(user)
            post.upVotes = post.upVotesArray.size
            if (post.downVotesArray.contains(user)) {
                post.downVotesArray.remove(user)
                post.downVotes = post.downVotesArray.size
            }
            databasePosts.child(post.postId).setValue(post)
        }
        else {
            post.upVotesArray.remove(user)
            post.upVotes = post.upVotesArray.size
            databasePosts.child(post.postId).setValue(post)
        }

    }

    private fun downVote(post : Post) {
        if (!post.downVotesArray.contains(user)) {
            post.downVotesArray.add(user)
            post.downVotes = post.downVotesArray.size
            if (post.upVotesArray.contains(user)) {
                post.upVotesArray.remove(user)
                post.upVotes = post.upVotesArray.size
            }
            databasePosts.child(post.postId).setValue(post)
        }
        else {
            post.downVotesArray.remove(user)
            post.downVotes = post.downVotesArray.size
            databasePosts.child(post.postId).setValue(post)
        }

    }
}
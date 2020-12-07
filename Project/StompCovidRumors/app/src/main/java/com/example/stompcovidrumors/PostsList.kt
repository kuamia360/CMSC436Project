package com.example.stompcovidrumors

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class PostsList(private val context: Activity, private var posts: List<Post>) :
    ArrayAdapter<Post>(context, R.layout.post_layout, posts) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val listViewItem =
            inflater.inflate(R.layout.post_layout, parent, false)

        val nameView = listViewItem.findViewById<TextView>(R.id.textViewName)
        val whatView = listViewItem.findViewById<TextView>(R.id.textViewWhat)
        val whereView = listViewItem.findViewById<TextView>(R.id.textViewWhere)
        val upvoteView = listViewItem.findViewById<TextView>(R.id.upvote_count)
        val downvoteView = listViewItem.findViewById<TextView>(R.id.downvote_count)
//        val textViewContent = listViewItem.findViewById<TextView>(R.id.textViewContent)

        val post = posts[position]
//        whatView.text = post.authorName
        whatView.text = "Rumor: " + post.content
        whereView.text = "Location: " + post.authorLocation
        nameView.text = post.authorName
        upvoteView.text = post.upVotesArray.count().toString()
        downvoteView.text = post.downVotesArray.count().toString()

        return listViewItem
    }
}
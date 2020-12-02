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

        val whatView = listViewItem.findViewById<TextView>(R.id.textViewWhat)
        val whereView = listViewItem.findViewById<TextView>(R.id.textViewWhere)
//        val textViewContent = listViewItem.findViewById<TextView>(R.id.textViewContent)

        val post = posts[position]
//        whatView.text = post.authorName
        whatView.text = post.authorLocation
        whereView.text = post.content

        return listViewItem
    }
}
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

        val textViewName = listViewItem.findViewById<TextView>(R.id.textViewName)
        val textViewCountry = listViewItem.findViewById<TextView>(R.id.textViewLocation)
        val textViewContent = listViewItem.findViewById<TextView>(R.id.textViewContent)

        val post = posts[position]
        textViewName.text = post.authorName
        textViewCountry.text = post.authorLocation
        textViewContent.text = post.content

        return listViewItem
    }
}
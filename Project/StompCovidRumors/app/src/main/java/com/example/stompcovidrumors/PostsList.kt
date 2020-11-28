package com.example.stompcovidrumors

import android.app.Activity
import android.widget.ArrayAdapter

class PostsList(private val context: Activity, private var authors: List<Post>) :
    ArrayAdapter<Post>(context, R.layout.post_layout, authors) {
}
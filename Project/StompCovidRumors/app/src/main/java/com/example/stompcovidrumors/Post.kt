package com.example.stompcovidrumors

data class Post (val authorName: String = "", val authorLocation: String = "", val content: String = "", val upVotes: Int = 0, val downVotes:  Int = 0)
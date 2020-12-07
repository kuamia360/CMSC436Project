package com.example.stompcovidrumors

data class Post (val postId : String = "", val authorName: String = "", val authorLocation: String = "", val content: String = "",
                 val upVotesArray: MutableList<String> = ArrayList(), val downVotesArray: MutableList<String> = ArrayList(),
                 var upVotes: Int = 0, var downVotes:  Int = 0)
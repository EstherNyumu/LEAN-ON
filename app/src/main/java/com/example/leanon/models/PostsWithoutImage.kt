package com.example.leanon.models

class PostsWithoutImage {
    var userName:String=""
        var postText:String = ""
        var postId:String = ""

        constructor(userName:String,postText: String,postId:String) {
            this.userName = userName
            this.postText = postText
            this.postId = postId

        }
    constructor()
}
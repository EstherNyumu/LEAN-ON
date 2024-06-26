package com.example.leanon.models

class PostsWithImage {
    var userName:String=""
    var postText:String = ""
    var imageUrl:String =""
    var postId:String = ""

    constructor(userName:String,postText: String,imageUrl:String,postId:String) {
        this.userName = userName
        this.postText = postText
        this.imageUrl = imageUrl
        this.postId = postId

    }
    constructor()
}
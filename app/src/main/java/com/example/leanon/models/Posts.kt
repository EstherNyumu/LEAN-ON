package com.example.leanon.models

class Posts {
    var anonymousName:String=""
    var postText:String = ""
    var imageUrl:String =""
    var postId:String = ""

    constructor(anonymousName:String,postText: String,imageUrl:String,postId:String) {
        this.anonymousName = anonymousName
        this.postText = postText
        this.imageUrl = imageUrl
        this.postId = postId

    }
    constructor()
}
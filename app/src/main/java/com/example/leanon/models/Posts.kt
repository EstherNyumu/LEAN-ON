package com.example.leanon.models

class Posts {
    var anonymousName:String=""
    var postText:String = ""
    var postId:String = ""

    constructor(anonymousName:String,postText: String,postId:String) {
        this.anonymousName = anonymousName
        this.postText = postText
        this.postId = postId

    }
    constructor()
}
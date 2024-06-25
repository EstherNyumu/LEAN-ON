package com.example.leanon.models

class User {
    var email:String = ""
    var password:String = ""
    var userId:String = ""
    var username:String = ""


    constructor(email: String, password: String, userId: String,username: String,) {
        this.email = email
        this.password = password
        this.userId = userId
        this.username = username

    }
    constructor()
}
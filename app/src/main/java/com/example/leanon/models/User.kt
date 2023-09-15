package com.example.leanon.models

class User {
    var username:String = ""
    var email:String = ""
    var password:String = ""
    var userId:String = ""

    constructor(username: String, email: String, password: String, userId: String) {
        this.username = username
        this.email = email
        this.password = password
        this.userId = userId
    }
    constructor()
}
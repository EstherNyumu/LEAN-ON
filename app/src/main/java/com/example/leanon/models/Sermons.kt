package com.example.leanon.models

class Sermons {
    var sermonDate:String = ""
    var preacher:String = ""
    var sermonScripture:String = ""
    var sermonTopic:String = ""
    var sermonNotes:String = ""
    var sermonId :String = ""

    constructor(
        sermonDate: String,
        preacher: String,
        sermonScripture: String,
        sermonTopic: String,
        sermonNotes: String,
        sermonId: String
    ) {
        this.sermonDate = sermonDate
        this.preacher = preacher
        this.sermonScripture = sermonScripture
        this.sermonTopic = sermonTopic
        this.sermonNotes = sermonNotes
        this.sermonId = sermonId
    }
    constructor()
}
package com.example.leanon.models

class BibleStudy {
    var studyDate:String = ""
    var studyScripture:String = ""
    var observation:String = ""
    var application:String = ""
    var studyPrayer:String = ""
    var studyId :String = ""

    constructor(
        studyDate: String,
        studyScripture: String,
        observation: String,
        application: String,
        studyPrayer: String,
        studyId:String
    ) {
        this.studyDate = studyDate
        this.studyScripture = studyScripture
        this.observation = observation
        this.application = application
        this.studyPrayer = studyPrayer
        this.studyId = studyId
    }
    constructor()
}
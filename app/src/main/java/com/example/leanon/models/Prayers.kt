package com.example.leanon.models

class Prayers {
    var prayerDate:String = ""
    var prayerText:String = ""
    var prayerId :String = ""

    constructor(prayerDate: String, prayerText: String,prayerId:String) {
        this.prayerDate = prayerDate
        this.prayerText = prayerText
        this.prayerId = prayerId
    }
    constructor()
}
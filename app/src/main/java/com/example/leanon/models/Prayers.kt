package com.example.leanon.models

class Prayers {
    var prayerDate:String = ""
    var prayerTime:String = ""
    var prayerText:String = ""
    var prayerId :String = ""

    constructor(prayerDate: String,prayerTime:String, prayerText: String,prayerId:String) {
        this.prayerDate = prayerDate
        this.prayerTime = prayerTime
        this.prayerText = prayerText
        this.prayerId = prayerId
    }
    constructor()
}
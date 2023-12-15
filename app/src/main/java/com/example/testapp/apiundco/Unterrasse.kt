package com.example.testapp.apiundco

import com.squareup.moshi.Json

data class Unterrasse(

    val id: Int ,
    @Json(name = "Rassen")
    var raceName: String,
    @Json(name = "Stärke")
    var strenght: String ,
    @Json(name = "Geschicklichkeit")
    val dexteriey : String,
    @Json(name = "Intelligenz")
    val intelligence : String ,
    @Json(name = "Konstitution")
    val constituion : String ,
    @Json(name = "Weisheit")
    val wisdom : String,
    @Json(name = "Charisma")
    val charisma : String,
    @Json(name = "Glück")
    val luck : String
)
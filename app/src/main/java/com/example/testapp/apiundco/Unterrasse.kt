package com.example.testapp.apiundco

import com.squareup.moshi.Json

data class Unterrasse(

    val id: Int = 0,
    @Json(name = "Rassen")
    var raceName: String = "",
    @Json(name = "Stärke")
    var strenght: String = "5",
    @Json(name = "Geschicklichkeit")
    val dexteriey : String = "5",
    @Json(name = "Intelligenz")
    val intelligence : String = "5",
    @Json(name = "Konstitution")
    val constituion : String = "5",
    @Json(name = "Weisheit")
    val wisdom : String = "5",
    @Json(name = "Charisma")
    val charisma : String = "5",
    @Json(name = "Glück")
    val luck : String = "5"
)
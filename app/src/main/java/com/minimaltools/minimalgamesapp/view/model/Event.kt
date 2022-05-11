package com.minimaltools.minimalgamesapp.view.model

import java.io.Serializable

class Event: Serializable {
    var Time:String=""
    var Location:String=""
    var Category:String=""
    var Address:String=""
    var Coordinates:Array<Double> = arrayOf(0.0,0.0) // Latitude, Longitude
    var Contact:String=""
    var WebSite:String=""
    var ImageUrl:String=""
}

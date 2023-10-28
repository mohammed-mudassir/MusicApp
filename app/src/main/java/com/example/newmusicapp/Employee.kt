package com.example.newmusicapp

import android.os.Parcel
import android.os.Parcelable

// Employee model
data class MusicList(
    val name:String,   // name of the songs
    val type:String,   // type of the songs
    val time:String,  // time of the songs
    val uri:Int   // uri of the songs

):java.io.Serializable // serializing the model
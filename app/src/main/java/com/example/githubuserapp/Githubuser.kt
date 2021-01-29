package com.example.githubuserapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Githubuser(
    var username: String?,
    var name: String?,
    var avatar: String?,
    var company: String?,
    var location: String?,
    var repository: Int?,
    var follower: Int?,
    var following: Int?
) : Parcelable

//@Parcelize
//class Githubuser : Parcelable {
//    var username: String? = null
//    var name: String? = null
//    var avatar: String? = null
//    var company: String? = null
//    var location: String? = null
//    var repository: Int? = 0
//    var follower: Int? = 0
//    var following: Int? = 0
//}


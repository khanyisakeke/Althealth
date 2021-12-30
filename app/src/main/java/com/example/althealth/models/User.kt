package com.example.althealth.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var id: String = "",
    var firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val image: String = "",
    val mobile: Long = 0,
    val homeNumber: Long = 0,
    val workNumber: Long = 0,
    val reference: String = "",
    val profileCompleted: Int = 0
): Parcelable

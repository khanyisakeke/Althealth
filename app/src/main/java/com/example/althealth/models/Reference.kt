package com.example.althealth.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Reference(

    var reference: String = "",
    val description: String = "",

    ) : Parcelable

package com.example.althealth.models

data class User(
    val id: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val image: String = "",
    val mobile: Long = 0,
    val homeNumber: Long = 0,
    val workNumber: Long = 0,
    val reference: String = "",
    val profileCompleted: Int = 0
)

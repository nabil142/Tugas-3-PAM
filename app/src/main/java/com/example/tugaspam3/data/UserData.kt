package com.example.tugaspam3.data

data class UserData(
    val nama: String = "",
    val email: String = "",
    val tanggalLahir: String = "",
    val nim: String = ""
)

object CurrentUser {
    var data: UserData = UserData()
}
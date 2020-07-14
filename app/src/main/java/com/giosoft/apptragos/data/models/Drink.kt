package com.giosoft.apptragos.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Drink(
    val imagen:String ="",
    val nombre:String = "",
    val descripcion:String = ""):Parcelable
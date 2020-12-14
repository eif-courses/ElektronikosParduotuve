package eif.viko.lt.elektronikosparduotuve.model

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class Product(
    val id: Int = 0,
    val price: Double = 0.0,
    val title: String = "",
    val description: String = "",
    val imageURL: String = ""
):Parcelable
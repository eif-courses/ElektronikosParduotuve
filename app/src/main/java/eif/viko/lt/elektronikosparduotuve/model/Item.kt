package eif.viko.lt.elektronikosparduotuve.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val audio:String="",
    val description:String="",
    val model:String="",
    val poster:String="",
    val title: String=""
) : Parcelable
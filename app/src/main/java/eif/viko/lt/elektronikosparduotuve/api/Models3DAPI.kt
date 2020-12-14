package eif.viko.lt.elektronikosparduotuve.api

import eif.viko.lt.elektronikosparduotuve.model.Item
import retrofit2.Call
import retrofit2.http.GET

interface Models3DAPI {
    @GET("lt.json")
    fun getModels(): Call<List<Item>>
}
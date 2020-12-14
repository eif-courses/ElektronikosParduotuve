package eif.viko.lt.elektronikosparduotuve.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Models3DRetrofit {
    private const val BASE_URL = "https://muziejus-api.firebaseio.com/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val getInstance: Models3DAPI by lazy { retrofit.create(Models3DAPI::class.java) }
}

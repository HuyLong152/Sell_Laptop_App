package com.example.selllaptop.NetWork

import com.example.selllaptop.Utils.constrain.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object LaptopService {
    val api : LaptopApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LaptopApi::class.java)
    }
}
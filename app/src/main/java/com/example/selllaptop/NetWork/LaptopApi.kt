package com.example.selllaptop.NetWork

import com.example.selllaptop.Model.Brand
import com.example.selllaptop.Model.Laptop
import com.example.selllaptop.Model.LaptopItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface LaptopApi {
    @GET("api.php?table=product")
    fun getAllLaptop():Call<Laptop>

    @GET("api.php?table=brand")
    fun getBrandLaptop():Call<List<Brand>>

    @GET("product/{id}")
    fun getProductById(@Path("id") id:Int):Call<Laptop>

}
package com.example.praktek_retrofit

import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("indonesia")
    fun getMain(): Call<ArrayList<DataIndonesia>>

    @GET("indonesia/provinsi")
    fun getProv(): Call<ArrayList<attributeData>>
}
package com.example.calismaactivity.service

import com.example.calismaactivity.model.University
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityAPI {
    val countryName:String
    //http://universities.hipolabs.com/search?country=Turkey

    @GET("search?")
    fun getUniversity(@Query("country") countryName:String):Single<List<University>>

    @GET("search")
    fun getOneUniversity(@Query("name") universityName:String):Single<List<University>>
}
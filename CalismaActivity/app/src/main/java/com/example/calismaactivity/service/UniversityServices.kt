package com.example.calismaactivity.service

import com.example.calismaactivity.model.University
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UniversityServices {

    //http://universities.hipolabs.com/search?country=Turkey

    fun getallUniversity(countryName:String):Single<List<University>>{
        val BASE_URL="http://universities.hipolabs.com/"
        val api =Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(UniversityAPI::class.java)
        return api.getUniversity(countryName)
    }

    fun getOneUniversity(universityName:String):Single<List<University>>{
        val BASE_URL="http://universities.hipolabs.com/"
        val api =Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UniversityAPI::class.java)
        return api.getOneUniversity(universityName)
    }
}
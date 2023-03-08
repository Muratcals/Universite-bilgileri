package com.example.calismaactivity.service

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.calismaactivity.model.University

@Dao
interface UniversityDao {

    @Insert
    suspend fun addDataUniversity(vararg university:University):List<Long>

    @Query("SELECT * FROM university ")
    suspend fun getAllDataUniversity():List<University>

    @Query("SELECT * FROM University WHERE universiteId=:universiteId")
    suspend fun getDataUniversity(universiteId:Int):University

    @Query("DELETE FROM university")
    suspend fun deleteAllDataUniversite()

    @Query("DELETE FROM UNIVERSITY WHERE universiteId=:universiteId")
    suspend fun deleteDataUniversite(universiteId:Int)
}
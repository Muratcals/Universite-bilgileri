package com.example.calismaactivity.service

import android.provider.CalendarContract
import android.view.View
import androidx.room.*
import com.example.calismaactivity.model.University
import com.example.calismaactivity.model.converter

@Database(entities = arrayOf(University::class), version = 1)
@TypeConverters(converter::class)
abstract class DatabaseServices :RoomDatabase() {

    abstract fun getUniversityData():UniversityDao

    companion object{
        @Volatile private var instance :DatabaseServices?=null
        private var lock =Any()
        operator fun invoke(view:View)= instance?: synchronized(lock){
            instance?: database(view).also {
                instance=it
            }
        }

        fun database(view:View)=Room.databaseBuilder(view.context, DatabaseServices::class.java,"Ulkeler").build()
    }
}
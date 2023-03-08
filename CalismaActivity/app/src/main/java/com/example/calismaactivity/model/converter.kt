package com.example.calismaactivity.model

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class converter {

    @TypeConverter
    fun converterList(list:List<String>):String{
        return list.toList().toString()
    }
    @TypeConverter
    fun converterAny(any: Any):String {
        return any.toString()
    }
}
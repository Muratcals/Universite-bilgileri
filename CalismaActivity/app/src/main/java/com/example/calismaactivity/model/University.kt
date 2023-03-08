package com.example.calismaactivity.model


import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity
data class University(
    @ColumnInfo(name="universiteKodu")
    @SerializedName("alpha_two_code")
    val alphaTwoCode: String,
    @ColumnInfo(name = "universiteAdi")
    @SerializedName("country")
    val country: String,
    @SerializedName("domains")
    val domains: List<String>,
    @ColumnInfo(name = "okulAdi")
    @SerializedName("universiteName")
    val name: String,
    @SerializedName("state-province")
    val stateProvince: Any,
    @SerializedName("web_pages")
    val webPages: List<String>
){
    @PrimaryKey(autoGenerate = true)
    var universiteId:Int=0
}
package com.example.calismaactivity.viewModel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calismaactivity.model.University
import com.example.calismaactivity.service.DatabaseServices
import com.example.calismaactivity.service.UniversityDao
import com.example.calismaactivity.service.UniversityServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class MainViewModel(view:View) : BaseViewModel() {
    val university=MutableLiveData<List<University>>()
    val hataMesaji =MutableLiveData<Boolean>()
    val progresss =MutableLiveData<Boolean>()
    val toolbar=MutableLiveData<Boolean>()
    val veriler =ArrayList<University>()
    val disposable=CompositeDisposable()
    val services =UniversityServices()
    val database =DatabaseServices(view)

    fun getUniversity(countryName:String){
        progresss.value=true
        toolbar.value=false
        disposable.add(
            services.getallUniversity(countryName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<University>>(){
                    override fun onSuccess(t: List<University>) {
                        if (t.size==0){
                            hataMesaji.value=true
                        }else{
                            veriler.clear()
                            var x = 0
                            while (x <= (t.size/2)){
                                veriler.add(t[x])
                                x+=1
                            }
                            university.value=veriler
                            progresss.value=false
                            hataMesaji.value=false
                            toolbar.value=true
                        }
                    }

                    override fun onError(e: Throwable) {
                        toolbar.value=false
                        hataMesaji.value=true
                        progresss.value=false
                        println(e.localizedMessage)
                    }
                })
        )
    }

    fun addUniversityFromSql(universityList:List<University>):List<University>{
        launch {
            val data =database.getUniversityData()
            data.deleteAllDataUniversite()
            val veriler =data.addDataUniversity(*universityList.toTypedArray())
            var x =0
            while (x<universityList.size){
                universityList[x].universiteId=veriler[x].toInt()
                x+=1
            }
        }
        return universityList
    }
    fun getAllDataUniversityFromSql():List<University>{
        var universityData:List<University>?=null
        launch {
            val data =database.getUniversityData()
            universityData=data.getAllDataUniversity()
        }
        return universityData!!
    }
    fun getDataUniversityFromSql(universityId:Int):University{
        val university:University?=null
        launch {
            val data =database.getUniversityData()
            val university =data.getDataUniversity(universityId)
        }
        return university!!
    }
}
package com.example.calismaactivity.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.calismaactivity.model.University
import com.example.calismaactivity.service.UniversityServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UniversityViewModel : ViewModel() {

    val university= MutableLiveData<List<University>>()
    val disposable= CompositeDisposable()
    val services = UniversityServices()
    fun getUniversity(universityName:String){
        disposable.add(
            services.getOneUniversity(universityName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<University>>(){
                    override fun onSuccess(t: List<University>) {
                        university.value=t
                    }

                    override fun onError(e: Throwable) {
                        println(e.localizedMessage)
                    }
                })
        )
    }
}
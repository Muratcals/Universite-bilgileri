package com.example.calismaactivity.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseViewModel:ViewModel(),CoroutineScope {
    private var job=Job()
    override val coroutineContext: CoroutineContext
        get() = job+Dispatchers.Main
}
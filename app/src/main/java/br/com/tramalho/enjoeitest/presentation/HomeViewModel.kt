package br.com.tramalho.enjoeitest.presentation

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableInt
import android.view.View

class HomeViewModel() : ViewModel() {

    val initLoadVisibility: ObservableInt = ObservableInt(View.GONE)
    val loadinMoreVisibility: ObservableInt = ObservableInt(View.GONE)
    val rvVisibility: ObservableInt = ObservableInt(View.GONE)

    fun init() {
        initLoadVisibility.set(View.VISIBLE)
        loadinMoreVisibility.set(View.GONE)
        rvVisibility.set(View.GONE)
    }


    sealed class States {

    }
}

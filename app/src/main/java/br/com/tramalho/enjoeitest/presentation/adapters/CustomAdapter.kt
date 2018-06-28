package br.com.tramalho.enjoeitest.presentation.adapters

import android.databinding.ObservableArrayList

interface CustomAdapter<T> {
    fun updateItens(list : ObservableArrayList<T>)
}
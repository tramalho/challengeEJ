package br.com.tramalho.enjoeitest.presentation

import android.databinding.ObservableArrayList

interface CustomAdapter<T> {
    fun updateItens(list : ObservableArrayList<T>)
}
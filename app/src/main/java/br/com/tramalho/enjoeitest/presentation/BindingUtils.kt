package br.com.tramalho.enjoeitest.presentation

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.View


@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.setVisibility(if (value) View.VISIBLE else View.GONE)
}

@BindingAdapter("bind:itens")
fun <T> bindItens(recyclerView: RecyclerView, list: ObservableArrayList<T>) {
    val adapter = recyclerView.adapter as CustomAdapter<T>
    adapter.updateItens(list)
}
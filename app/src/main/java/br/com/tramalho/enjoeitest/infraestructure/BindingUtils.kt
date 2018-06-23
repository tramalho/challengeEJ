package br.com.tramalho.enjoeitest.infraestructure

import android.databinding.BindingAdapter
import android.view.View


@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.setVisibility(if (value) View.VISIBLE else View.GONE)
}
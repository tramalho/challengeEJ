package br.com.tramalho.enjoeitest.presentation

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.widget.ImageView
import br.com.tramalho.enjoeitest.R


@BindingAdapter("bind:itens")
fun <T> bindItens(recyclerView: RecyclerView, list: ObservableArrayList<T>) {
    val adapter = recyclerView.adapter as CustomAdapter<T>
    adapter.updateItens(list)
}

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    val context = imageView.contextd
    if (!TextUtils.isEmpty(url)) {
        Glide.with(context)
                .load(url)
                .roundedCorners(context, 5)
                .into(imageView)
    } else {
        imageView.setImageResource(R.drawable.hint_img)
    }
}
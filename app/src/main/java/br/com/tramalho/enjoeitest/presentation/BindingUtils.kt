package br.com.tramalho.enjoeitest.presentation

import android.databinding.BindingAdapter
import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.util.Log
import android.widget.ImageView
import br.com.tramalho.enjoeitest.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions


@BindingAdapter("itens")
fun <T> bindItens(recyclerView: RecyclerView, list: ObservableArrayList<T>) {
    val adapter = recyclerView.adapter as CustomAdapter<T>
    adapter.updateItens(list)
}

@BindingAdapter(value= arrayOf("imageUrl", "rounded"), requireAll = false)
fun loadImage(imageView: ImageView, url: String, roundingRadius : Int = 0) {
    val context = imageView.context
    if (!TextUtils.isEmpty(url)) {

        var requestOptions =
                RequestOptions().placeholder(R.drawable.hint_img);

        if (roundingRadius > 0) {
            requestOptions.transforms(RoundedCorners(roundingRadius));
        }

        Glide.with(context)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(requestOptions)
                .into(imageView)

        Log.d("BindingUtils:loadImage", url)

    } else {
        imageView.setImageResource(R.drawable.hint_img)
    }
}
package br.com.tramalho.enjoeitest.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import br.com.tramalho.enjoeitest.R
import br.com.tramalho.enjoeitest.data.model.Photo
import br.com.tramalho.enjoeitest.infraestructure.configImg

class ImgProductViewModel() : ViewModel() {

    val productImgUrl: ObservableField<String> = ObservableField<String>("")

    val placeHolderImg: ObservableInt = ObservableInt(0)

    fun bind(photo: Photo) {

        val productUrl = configImg(photo, 360, 428)

        placeHolderImg.set(R.drawable.placeholder_img)

        productImgUrl.set(productUrl)
    }
}

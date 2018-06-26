package br.com.tramalho.enjoeitest.presentation

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.tramalho.enjoeitest.BuildConfig
import br.com.tramalho.enjoeitest.data.model.Photo
import br.com.tramalho.enjoeitest.data.model.Product
import br.com.tramalho.enjoeitest.infraestructure.URLBuilder
import java.text.NumberFormat

class ProductItemViewModel : ViewModel() {

    val discount: ObservableField<String> = ObservableField<String>("0")

    val discountVisibility: ObservableInt = ObservableInt(GONE)

    val title: ObservableField<String> = ObservableField<String>("0")

    val price: ObservableField<String> = ObservableField<String>("0")

    val likes: ObservableField<String> = ObservableField<String>("0")

    val avatarImgUrl: ObservableField<String> = ObservableField<String>("")

    val productImgUrl: ObservableField<String> = ObservableField<String>("")


    fun bind(product: Product) {

        configDiscount(product)

        title.set(product.title)

        configPrice(product)

        likes.set("${product.likesCount}")

        val avatarUrl = configImg(product.user.avatar, 50, 50)

        avatarImgUrl.set(avatarUrl)

        val productrUrl = configImg(product.photos[0], 200, 200)

        productImgUrl.set(productrUrl)
    }

    private fun configImg(photo: Photo, with: Int, height: Int): String {

        val urlBuilder = URLBuilder().apply {
            baseURL = BuildConfig.BASE_IMG_URL

            crop = photo.crop
            gravity = photo.gravity
            publicId = photo.publicId
            width = with
            this.height = height
        }

        return urlBuilder.build()
    }

    private fun configPrice(product: Product) {
        val format = NumberFormat.getCurrencyInstance()
        price.set(format.format(product.price))
    }

    private fun configDiscount(product: Product) {
        discount.set("${product.discountPercentage}%")

        val visibilityDiscount = when (product.discountPercentage) {
            0 -> GONE
            else -> VISIBLE
        }

        discountVisibility.set(visibilityDiscount)
    }

}

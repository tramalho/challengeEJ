package br.com.tramalho.enjoeitest.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.tramalho.enjoeitest.R
import br.com.tramalho.enjoeitest.data.model.Product
import br.com.tramalho.enjoeitest.infraestructure.configImg
import br.com.tramalho.enjoeitest.infraestructure.formatCurrency
import br.com.tramalho.enjoeitest.presentation.DetailProductActivity

class ProductItemViewModel() : ViewModel() {

    val discount: ObservableField<String> = ObservableField<String>("0")

    val discountVisibility: ObservableInt = ObservableInt(GONE)

    val title: ObservableField<String> = ObservableField<String>("0")

    val price: ObservableField<String> = ObservableField<String>("0")

    val likes: ObservableField<String> = ObservableField<String>("0")

    val avatarImgUrl: ObservableField<String> = ObservableField<String>("")

    val productImgUrl: ObservableField<String> = ObservableField<String>("")

    val placeHolderImg: ObservableInt = ObservableInt(R.drawable.placeholder_img)


    lateinit var product: Product

    fun bind(product: Product) {

        this.product = product

        configDiscount(product)

        title.set(product.title)

        price.set(formatCurrency(product.price))

        likes.set("${product.likesCount}")

        val avatarUrl = configImg(product.user.avatar, 50, 50)

        avatarImgUrl.set(avatarUrl)

        val productUrl = configImg(product.photos[0], 200, 200)

        productImgUrl.set(productUrl)
    }

    private fun configDiscount(product: Product) {
        discount.set("${product.discountPercentage}%")

        val visibilityDiscount = when (product.discountPercentage) {
            0 -> GONE
            else -> VISIBLE
        }

        discountVisibility.set(visibilityDiscount)
    }

    fun onClickFriend(view: View) {
        val intent = Intent(view.context, DetailProductActivity::class.java)
        intent.putExtra(DetailProductActivity.PRODUCT_EXTRA, product)
        view.context.startActivity(intent)
    }
}

package br.com.tramalho.enjoeitest.presentation

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.tramalho.enjoeitest.data.model.Product
import java.text.NumberFormat

class ProductItemViewModel : ViewModel() {

    val discount: ObservableField<String> = ObservableField<String>("0")

    val discountVisibility: ObservableInt = ObservableInt(GONE)

    val title: ObservableField<String> = ObservableField<String>("0")

    val price: ObservableField<String> = ObservableField<String>("0")

    val likes: ObservableField<String> = ObservableField<String>("0")


    fun bind(product: Product) {

        configDiscount(product)

        title.set(product.title)

        configPrice(product)

        likes.set("${product.likesCount}")
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

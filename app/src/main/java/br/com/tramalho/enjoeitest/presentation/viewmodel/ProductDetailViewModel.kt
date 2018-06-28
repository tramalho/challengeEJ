package br.com.tramalho.enjoeitest.presentation.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.databinding.ObservableInt
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import br.com.tramalho.enjoeitest.R
import br.com.tramalho.enjoeitest.data.model.Product
import br.com.tramalho.enjoeitest.infraestructure.configImg
import br.com.tramalho.enjoeitest.infraestructure.formatCurrency

class ProductDetailViewModel() : ViewModel() {

    val price: ObservableField<String> = ObservableField<String>("")

    val originalPrice: ObservableField<String> = ObservableField<String>("")

    val title: ObservableField<String> = ObservableField<String>("")

    val publishedCommentsCount: ObservableField<String> = ObservableField<String>("0")

    val publishedCommentsVisibility: ObservableInt = ObservableInt(GONE)

    val paymentInfo: ObservableField<String> = ObservableField<String>("")

    val content: ObservableField<String> = ObservableField<String>("")

    fun bind(product: Product) {

        price.set(formatCurrency(product.price))

        originalPrice.set(formatCurrency(product.originalPrice))

        configComments(product)

        title.set(product.title)

        paymentInfo.set("${product.discountPercentage}% off em até " +
                "${product.maximumInstallment}x sem juros")

        content.set(product.content)
    }

    private fun configComments(product: Product) {

        publishedCommentsVisibility.set(GONE)

        if (product.publishedCommentsCount > 0) {
            publishedCommentsCount.set("${product.publishedCommentsCount}")
            publishedCommentsVisibility.set(VISIBLE)
        }
    }
}

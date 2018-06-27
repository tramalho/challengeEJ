package br.com.tramalho.enjoeitest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
        val id: Long,
        @SerializedName("discount_percentage")
        val discountPercentage: Int,
        val title: String,
        val price: Double,
        @SerializedName("original_price")
        val originalPrice: Double,
        val size: String?,
        @SerializedName("likes_count")
        val likesCount: Int,
        @SerializedName("maximum_installment")
        val maximumInstallment: Int,
        @SerializedName("published_comments_count")
        val publishedCommentsCount: Int,
        val user: User,
        val photos: List<Photo>
) : Parcelable
package br.com.tramalho.enjoeitest.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
        @SerializedName("public_id")
        val publicId: String,
        val crop: String,
        val gravity: String) : Parcelable
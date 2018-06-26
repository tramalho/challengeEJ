package br.com.tramalho.enjoeitest.data.model

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("public_id")
        val publicId: String,
        val crop: String,
        val gravity: String)
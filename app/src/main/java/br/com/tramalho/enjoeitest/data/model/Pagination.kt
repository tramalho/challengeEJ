package br.com.tramalho.enjoeitest.data.model

import com.google.gson.annotations.SerializedName

data class Pagination(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("total_pages")
        val totalPage: Int)
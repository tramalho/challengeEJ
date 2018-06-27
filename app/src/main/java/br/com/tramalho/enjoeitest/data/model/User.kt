package br.com.tramalho.enjoeitest.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(var name: String, var avatar: Photo) : Parcelable


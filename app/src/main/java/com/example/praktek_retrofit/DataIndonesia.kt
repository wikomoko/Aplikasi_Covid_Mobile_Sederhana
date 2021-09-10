package com.example.praktek_retrofit

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class DataIndonesia(
    val meninggal: String?,
    val name: String?,
    val positif: String?,
    val sembuh: String?
):Parcelable
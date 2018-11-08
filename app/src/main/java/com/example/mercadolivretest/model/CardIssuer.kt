package com.example.mercadolivretest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CardIssuer (

    var id: String,
    var name: String,
    @SerializedName("secure_thumbnail")
    @Expose
    var secureThumbnail: String,
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String,
    @SerializedName("processing_mode")
    @Expose
    var processingMode: String,
    @SerializedName("merchant_account_id")
    @Expose
    var merchantAccountId: Any

)
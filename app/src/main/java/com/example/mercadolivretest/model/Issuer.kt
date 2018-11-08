package com.example.mercadolivretest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Issuer (

    var id: String? = null,
    var name: String? = null,
    @SerializedName("secure_thumbnail")
    @Expose
    var secureThumbnail: String? = null,
    var thumbnail: String? = null

)
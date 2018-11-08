package com.example.mercadolivretest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CardNumber {

    @SerializedName("validation")
    @Expose
    var validation: String? = null
    @SerializedName("length")
    @Expose
    var length: Int? = null

}
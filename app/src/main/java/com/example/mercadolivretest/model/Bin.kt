package com.example.mercadolivretest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Bin (
    var pattern: String? = null,
    @SerializedName("installments_pattern")
    @Expose
    var installmentsPattern: String? = null,
    @SerializedName("exclusion_pattern")
    @Expose
    var exclusionPattern: Any? = null,
    @SerializedName("exclusion_pattern")
    @Expose
    var exclusionPatterne: Any? = null

)
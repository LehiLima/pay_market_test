package com.example.mercadolivretest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Setting {

    @SerializedName("card_number")
    @Expose
    var cardNumber: CardNumber? = null
    @SerializedName("bin")
    @Expose
    var bin: Bin? = null
    @SerializedName("security_code")
    @Expose
    var securityCode: SecurityCode? = null

}
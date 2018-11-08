package com.example.mercadolivretest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Installment (

    @SerializedName("payment_method_id")
    @Expose
    var paymentMethodId: String,
    @SerializedName("payment_type_id")
    @Expose
    var paymentTypeId: String,
    var issuer: Issuer,
    @SerializedName("processing_mode")
    @Expose
    var processingMode: String,
    @SerializedName("merchant_account_id")
    @Expose
    var merchantAccountId: Any,
    @SerializedName("payer_costs")
    @Expose
    var payerCosts: List<PayerCost>
)
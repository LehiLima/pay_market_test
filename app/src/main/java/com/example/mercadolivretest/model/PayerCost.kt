package com.example.mercadolivretest.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PayerCost (
    var installments: Int = 0,
    @SerializedName("installment_rate")
    @Expose
    var installmentRate: Double = 0.0,
    @SerializedName("discount_rate")
    @Expose
    var discountRate: Int = 0,
    var labels: List<String> = emptyList(),
    @SerializedName("installment_rate_collector")
    @Expose
    var installmentRateCollector: List<String> = emptyList(),
    @SerializedName("min_allowed_amount")
    @Expose
    var minAllowedAmount: Int = 0,
    @SerializedName("max_allowed_amount")
    @Expose
    var maxAllowedAmount: Int = 0,
    @SerializedName("recommended_message")
    @Expose
    var recommendedMessage: String = "",
    @SerializedName("installment_amount")
    @Expose
    var installmentAmount: Double = 0.0,
    @SerializedName("total_amount")
    @Expose
    var totalAmount: Double = 0.0

) : Parcelable
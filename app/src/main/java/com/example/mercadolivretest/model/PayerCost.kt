package com.example.mercadolivretest.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PayerCost (
    var installments: Int,
    @SerializedName("installment_rate")
    @Expose
    var installmentRate: Double,
    @SerializedName("discount_rate")
    @Expose
    var discountRate: Int,
    var labels: List<String>,
    @SerializedName("installment_rate_collector")
    @Expose
    var installmentRateCollector: List<String>,
    @SerializedName("min_allowed_amount")
    @Expose
    var minAllowedAmount: Int,
    @SerializedName("max_allowed_amount")
    @Expose
    var maxAllowedAmount: Int,
    @SerializedName("recommended_message")
    @Expose
    var recommendedMessage: String,
    @SerializedName("installment_amount")
    @Expose
    var installmentAmount: Double,
    @SerializedName("total_amount")
    @Expose
    var totalAmount: Double

) : Parcelable
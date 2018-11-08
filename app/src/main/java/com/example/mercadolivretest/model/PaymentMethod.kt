package com.example.mercadolivretest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PaymentMethod (

    var id: String,
    var name: String,
    @SerializedName("payment_type_id")
    @Expose
    var payment_type_id: String,
    var status: String,
    @SerializedName("secure_thumbnail")
    @Expose
    var secureThumbnail: String,
    var thumbnail: String,
    @SerializedName("deferred_capture")
    @Expose
    var deferredCapture: String,
    var settings: List<Setting>,
    @SerializedName("additional_info_needed")
    @Expose
    var additionalInfoNeeded: List<String>,
    @SerializedName("min_allowed_amount")
    @Expose
    var minAllowedAmount: Double,
    @SerializedName("max_allowed_amount")
    @Expose
    var maxAllowedAmount: Int,
    @SerializedName("accreditation_time")
    @Expose
    var accreditationTime: Int,
    @SerializedName("financial_institutions")
    @Expose
    var financialInstitutions: List<Any>,
    @SerializedName("processing_modes")
    @Expose
    var processingModes: List<String>

)
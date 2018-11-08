package com.example.mercadolivretest.data

import com.example.mercadolivretest.model.CardIssuer
import com.example.mercadolivretest.model.Installment
import com.example.mercadolivretest.model.PaymentMethod
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

interface PayMarketApiService {

    @GET("/v1/payment_methods")
    fun getPaymentMethod(@Query("public_key") publicKey: String): Observable<List<PaymentMethod>>

    @GET("/v1/payment_methods/card_issuers")
    fun getCardIssuers(@Query("public_key") publicKey: String,
                         @Query("payment_method_id") paymentMethodId: String): Observable<List<CardIssuer>>

    @GET("/v1/payment_methods/installments")
    fun getInstallments(@Query("public_key") publicKey: String,
                       @Query("amount") amountvalue: String,
                       @Query("payment_method_id") paymentMethodId: String,
                       @Query("issuer.id") issuerId: String): Observable<List<Installment>>

    companion object {
        fun create(): PayMarketApiService {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api.mercadopago.com/")
                    .build()

            return retrofit.create(PayMarketApiService::class.java)
        }
    }

}
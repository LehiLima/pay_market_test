/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.mercadolivretest.data

import com.example.mercadolivretest.model.CardIssuer
import com.example.mercadolivretest.model.PayerCost
import com.example.mercadolivretest.model.PaymentMethod

import javax.inject.Singleton

@Singleton
class PayMarketRepository// Prevent direct instantiation.
private constructor(private val payMarketDataSource: PayMarketDataSource) : PayMarketDataSource {

    override fun getPaymentMethod(callback: PayMarketDataSource.PaymentMethodCallback) {
        payMarketDataSource.getPaymentMethod(object : PayMarketDataSource.PaymentMethodCallback {
            override fun onPaymentMethodLoaded(paymentMethods: List<PaymentMethod>) {
                callback.onPaymentMethodLoaded(paymentMethods)
            }

            override fun onPaymentMethodError(error: String) {
                callback.onPaymentMethodError(error)
            }
        })
    }

    override fun getCardIssuers(callback: PayMarketDataSource.CardIssuersCallback, paymentMethodId: String) {
        payMarketDataSource.getCardIssuers(object : PayMarketDataSource.CardIssuersCallback {
            override fun onCardIssuersLoaded(cardIssuers: List<CardIssuer>) {
                callback.onCardIssuersLoaded(cardIssuers)
            }

            override fun onCardIssuersError(error: String) {
                callback.onCardIssuersError(error)
            }
        }, paymentMethodId)
    }

    override fun getInstallments(callback: PayMarketDataSource.InstallmentsCallback, amountValue: String, paymentMethodId: String, cardIssuersId: String) {
        payMarketDataSource.getInstallments(object : PayMarketDataSource.InstallmentsCallback {
            override fun onInstallmentsLoaded(installments: List<PayerCost>) {
                callback.onInstallmentsLoaded(installments)
            }

            override fun onInstallmentsError(error: String) {
                callback.onInstallmentsError(error)
            }
        }, amountValue, paymentMethodId, cardIssuersId)
    }

    companion object {

        @Volatile
        private var INSTANCE: PayMarketRepository? = null


        fun getInstance(payMarketDataSource: PayMarketDataSource): PayMarketRepository? {
            if (INSTANCE == null) {
                synchronized(PayMarketRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = PayMarketRepository(payMarketDataSource)
                    }
                }
            }
            return INSTANCE
        }


        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
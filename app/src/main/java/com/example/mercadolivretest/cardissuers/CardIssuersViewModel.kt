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

package com.example.mercadolivretest.cardissuers

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.content.Context

import com.example.mercadolivretest.data.PayMarketDataSource
import com.example.mercadolivretest.data.PayMarketRepository
import com.example.mercadolivretest.model.CardIssuer
import com.example.mercadolivretest.model.PaymentMethod

import java.util.*

import timber.log.Timber

class CardIssuersViewModel(context: Application, private val payMarketRepository: PayMarketRepository) : AndroidViewModel(context) {

    private val mContext: Context

    init {
        mContext = context.applicationContext
    }

    fun loadPaymentMethod(callback: PayMarketDataSource.CardIssuersCallback,  paymentMethodId : String ) {
        payMarketRepository.getCardIssuers(object : PayMarketDataSource.CardIssuersCallback {
            override fun onCardIssuersLoaded(cardIssuers: MutableList<CardIssuer>?) {
                callback.onCardIssuersLoaded(cardIssuers)
            }

            override fun onCardIssuersError(error: String?) {
                callback.onCardIssuersError(error)
            }
        },paymentMethodId)
    }


}

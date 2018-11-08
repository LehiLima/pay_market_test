package com.example.mercadolivretest.paymentmethod

import android.app.Application
import android.content.Context
import com.example.mercadolivretest.data.PayMarketRepository
import com.example.mercadolivretest.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PaymentMethodProviders {
    @Provides
    internal fun providePaymentMethodViewModelFactory(context: Context, repository: PayMarketRepository): ViewModelFactory {
        return ViewModelFactory(context as Application, repository)
    }

}
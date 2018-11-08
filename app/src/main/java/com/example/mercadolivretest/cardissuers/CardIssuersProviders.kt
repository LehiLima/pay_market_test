package com.example.mercadolivretest.cardissuers

import android.app.Application
import android.content.Context
import com.example.mercadolivretest.data.PayMarketRepository
import com.example.mercadolivretest.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CardIssuersProviders {
    @Provides
    internal fun provideCardIssuersViewModelFactory(context: Context, repository: PayMarketRepository): ViewModelFactory {
        return ViewModelFactory(context as Application, repository)
    }

}
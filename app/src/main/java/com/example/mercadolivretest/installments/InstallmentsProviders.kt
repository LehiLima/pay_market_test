package com.example.mercadolivretest.installments

import android.app.Application
import android.content.Context
import com.example.mercadolivretest.data.PayMarketRepository
import com.example.mercadolivretest.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class InstallmentsProviders {
    @Provides
    internal fun provideInstallmentsProviderssViewModelFactory(context: Context, repository: PayMarketRepository): ViewModelFactory {
        return ViewModelFactory(context as Application, repository)
    }

}
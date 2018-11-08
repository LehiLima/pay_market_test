package com.example.mercadolivretest.di

import com.example.mercadolivretest.cardissuers.CardIssuersFragment
import com.example.mercadolivretest.cardissuers.CardIssuersProviders
import com.example.mercadolivretest.installments.InstallmentsFragment
import com.example.mercadolivretest.installments.InstallmentsProviders
import com.example.mercadolivretest.paymentmethod.PaymentMethodFragment
import com.example.mercadolivretest.paymentmethod.PaymentMethodProviders
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by lehi.teixeira on 7/30/18.
 */

@Module
abstract class PayMarketFragmentProvider {

    @ContributesAndroidInjector(modules = arrayOf(PaymentMethodProviders::class))
    internal abstract fun providePaymentMethodFragment(): PaymentMethodFragment

    @ContributesAndroidInjector(modules = arrayOf(CardIssuersProviders::class))
    internal abstract fun provideCardIssuersFragment(): CardIssuersFragment

    @ContributesAndroidInjector(modules = arrayOf(InstallmentsProviders::class))
    internal abstract fun provideInstallmentsFragment(): InstallmentsFragment

}
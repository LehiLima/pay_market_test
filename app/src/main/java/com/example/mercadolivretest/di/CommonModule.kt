package com.example.mercadolivretest.di

import android.app.ActivityManager
import android.content.Context
import com.example.mercadolivretest.data.PayMarketRepository
import com.example.mercadolivretest.data.PayMarketremoteDataSource
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class CommonModule {

    @Provides
    internal fun provideListingRepository(): PayMarketRepository {
        return PayMarketRepository.getInstance(PayMarketremoteDataSource.instance)!!
    }

    @Provides
    @Singleton
    internal fun provideActivityManager(context: Context): ActivityManager {
        return context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    }
}
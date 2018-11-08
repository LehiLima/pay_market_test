package com.example.mercadolivretest.di
import com.example.mercadolivretest.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [PayMarketFragmentProvider::class])
    internal abstract fun bindMainActivity(): MainActivity


}
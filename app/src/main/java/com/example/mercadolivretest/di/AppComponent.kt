package com.example.mercadolivretest.di

import android.app.Application
import android.content.Context
import com.example.mercadolivretest.PayMarketApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, CommonModule::class, BuildersModule::class])
interface AppComponent {

    fun inject(app: PayMarketApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: Context): Builder
        fun build(): AppComponent
    }
}
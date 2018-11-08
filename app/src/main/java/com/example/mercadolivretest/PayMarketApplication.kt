package com.example.mercadolivretest

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import com.example.mercadolivretest.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class PayMarketApplication: Application(), HasActivityInjector, HasSupportFragmentInjector {

    var activityInjector: DispatchingAndroidInjector<Activity>? = null

    @Inject lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder().app(this).build().inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector

    override fun supportFragmentInjector() = fragmentInjector

}
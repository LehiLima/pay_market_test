package com.example.mercadolivretest

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.mercadolivretest.cardissuers.CardIssuersViewModel
import com.example.mercadolivretest.data.PayMarketRepository
import com.example.mercadolivretest.installments.InstallmentsViewModel
import com.example.mercadolivretest.paymentmethod.PaymentMethodtViewModel


class ViewModelFactory(private val application: Application, private val repository: PayMarketRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PaymentMethodtViewModel::class.java)) {
            return PaymentMethodtViewModel(application, repository) as T
       }else if(modelClass.isAssignableFrom(CardIssuersViewModel::class.java)){
            return CardIssuersViewModel(application, repository) as T
        }else if(modelClass.isAssignableFrom(InstallmentsViewModel::class.java)){
            return InstallmentsViewModel(application, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
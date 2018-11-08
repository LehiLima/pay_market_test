package com.example.mercadolivretest.paymentmethod


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.example.mercadolivretest.R
import com.example.mercadolivretest.data.PayMarketDataSource
import com.example.mercadolivretest.ViewModelFactory
import com.example.mercadolivretest.model.PaymentMethod
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_payment_method.*
import timber.log.Timber
import javax.inject.Inject

class PaymentMethodFragment :  DaggerFragment()  {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var payMarketViewModel : PaymentMethodtViewModel ?= null

    private lateinit var amountValue : String

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.w("onCreate")
        super.onCreate(savedInstanceState)
        payMarketViewModel = ViewModelProviders.of(this, viewModelFactory).get(PaymentMethodtViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.w("onCreateView")
        amountValue = arguments?.getString("amountValue")!!
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment_method, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.w("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.toolbar_title_payment_methos)
        getPaymentMethos()
    }

    private fun getPaymentMethos() {
        Timber.w("getPaymentMethos")
        payMarketViewModel?.loadPaymentMethod(object : PayMarketDataSource.PaymentMethodCallback {
            override fun onPaymentMethodLoaded(paymentMethods: List<PaymentMethod>) {
                Timber.w("onPaymentMethodLoaded")
                progressBar.visibility = View.GONE
                val recyclerView = payment_method_list_rv
                recyclerView.layoutManager = GridLayoutManager(context, 1)
                recyclerView.adapter = context?.let { PaymentMethodAdapter(paymentMethods, it, amountValue) }
            }

            override fun onPaymentMethodError(error: String) {
                Timber.w("onPaymentMethodError")
                Timber.w("Error" + error)
                progressBar.visibility = View.GONE
            }
        })
    }

}

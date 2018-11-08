package com.example.mercadolivretest.installments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mercadolivretest.R
import com.example.mercadolivretest.ViewModelFactory
import com.example.mercadolivretest.data.PayMarketDataSource
import com.example.mercadolivretest.model.PayerCost
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_installments.*
import kotlinx.android.synthetic.main.fragment_payment_method.*
import timber.log.Timber
import javax.inject.Inject


class InstallmentsFragment :  DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var installmentsViewModel : InstallmentsViewModel ?= null

    private lateinit var amountValue : String
    private lateinit var paymentMethodId : String
    private lateinit var cardIssuersId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.w("onCreate")
        super.onCreate(savedInstanceState)
        installmentsViewModel = ViewModelProviders.of(this, viewModelFactory).get(InstallmentsViewModel::class.java)
    }
        override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.w("onCreateView")
        amountValue = arguments?.getString("amountValue")!!
        paymentMethodId = arguments?.getString("paymentMethodId").toString()
        cardIssuersId = arguments?.getString("cardIssuerId").toString()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_installments, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.w("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.toolbar_title_card_issuers)

        getInstallments()

    }

    private fun getInstallments() {
        installmentsViewModel?.loadInstallments(object : PayMarketDataSource.InstallmentsCallback {
            override fun onInstallmentsLoaded(installments: List<PayerCost>) {
                Timber.w("onInstallmentsLoaded")
                progressBarInstallment.visibility = View.GONE
                val recyclerView = installments_list_rv
                recyclerView.layoutManager = GridLayoutManager(context, 1)
                recyclerView.adapter = context?.let { InstallmentsAdapter(installments, it, paymentMethodId, cardIssuersId, amountValue) }
            }

            override fun onInstallmentsError(error: String) {
                Timber.w("onInstallmentsError:Error" + error)
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
                progressBarInstallment.visibility = View.GONE
            }
        }, amountValue, paymentMethodId, cardIssuersId)
    }

}

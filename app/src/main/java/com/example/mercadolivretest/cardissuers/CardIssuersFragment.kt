package com.example.mercadolivretest.cardissuers

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.mercadolivretest.data.PayMarketApiService
import com.example.mercadolivretest.R
import com.example.mercadolivretest.ViewModelFactory
import com.example.mercadolivretest.data.PayMarketDataSource
import com.example.mercadolivretest.model.CardIssuer
import com.example.mercadolivretest.paymentmethod.PaymentMethodtViewModel
import dagger.android.support.DaggerFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_card_issuers.*
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 *
 */
class CardIssuersFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var cardIssuersViewModel : CardIssuersViewModel ?= null

    private lateinit var amountValue : String
    private lateinit var paymentMethodId : String

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.w("onCreate")
        super.onCreate(savedInstanceState)
        cardIssuersViewModel = ViewModelProviders.of(this, viewModelFactory).get(CardIssuersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.w("onCreateView")

        amountValue = arguments?.getString("amountValue")!!
        paymentMethodId = arguments?.getString("id").toString()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_issuers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.w("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.toolbar_title_card_issuers)

        getCardIssuers()
    }

    private fun getCardIssuers() {
        Timber.w("getCardIssuers")
        cardIssuersViewModel?.loadCardIdduers(object : PayMarketDataSource.CardIssuersCallback {
            override fun onCardIssuersLoaded(cardIssuers: List<CardIssuer>) {
                Timber.w("onCardIssuersLoaded")
                progressBarCardIssuers.visibility = View.GONE
                val recyclerView = card_issuers_list_rv
                recyclerView.layoutManager = GridLayoutManager(context, 1)
                recyclerView.adapter = context?.let { CardIssuersAdapter(cardIssuers, it, paymentMethodId, amountValue) }
            }

            override fun onCardIssuersError(error: String) {
                Timber.w("onCardIssuersError")
                progressBarCardIssuers.visibility = View.GONE
                Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            }
        }, paymentMethodId)
    }

}

package com.example.mercadolivretest.amount

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.mercadolivretest.R
import com.example.mercadolivretest.model.PayerCost
import kotlinx.android.synthetic.main.fragment_amount.*
import timber.log.Timber


class AmountFragment : Fragment() {

    private lateinit var payerCost: PayerCost
    private lateinit var amountValue : String
    private lateinit var paymentMethodId : String
    private lateinit var cardIssuersId : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        Timber.w("onCreateView")

        return inflater.inflate(R.layout.fragment_amount, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.w("onViewCreated")

        (activity as AppCompatActivity).supportActionBar?.hide()

        btn_next.setOnClickListener {
            //Go to next step
            var bundle = bundleOf("amountValue" to amount_value.text.toString())
            Navigation.findNavController(view).navigate(R.id.action_next_step, bundle)
        }

        if (arguments?.getParcelable<PayerCost>("payerCost") != null) {
            payerCost = arguments?.getParcelable<PayerCost>("payerCost") as PayerCost
            amountValue = arguments?.getString("amountValue")!!
            paymentMethodId = arguments?.getString("paymentMethodId").toString()
            cardIssuersId = arguments?.getString("cardIssuerId").toString()
            val cdd = ResultDialog(this.activity,payerCost,amountValue,paymentMethodId,cardIssuersId)
            cdd.show()
        }
    }
}

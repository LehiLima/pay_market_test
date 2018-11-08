package com.example.mercadolivretest.installments
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.mercadolivretest.R
import com.example.mercadolivretest.model.PayerCost
import kotlinx.android.synthetic.main.payment_method_item.view.*

class InstallmentsAdapter(private val payerCosts: List<PayerCost>,
                          private val context: Context,
                          private val paymentMethodId: String,
                          private val cardIssuerId: String,
                          private val amountValue: String) : Adapter<InstallmentsAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val payerCost = payerCosts[position]
        holder?.let {
            it.bindView(payerCost, paymentMethodId,amountValue, cardIssuerId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.payment_method_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return payerCosts.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(payerCost: PayerCost, paymentMethodId: String, amountValue: String, cardIssuerId: String ) {
            val name = itemView.name
            name.text = payerCost.recommendedMessage

            itemView.setOnClickListener {
                var bundle = bundleOf("amountValue" to amountValue ,"paymentMethodId" to paymentMethodId, "cardIssuerId" to cardIssuerId, "payerCost" to payerCost)
                Navigation.findNavController(it).navigate(R.id.action_next_step, bundle)
            }
        }

    }

}
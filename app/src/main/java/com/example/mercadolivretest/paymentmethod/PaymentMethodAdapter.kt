package com.example.mercadolivretest.paymentmethod


import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.Adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import com.example.mercadolivretest.model.PaymentMethod
import com.example.mercadolivretest.R
import com.example.mercadolivretest.extensions.loadUrl
import kotlinx.android.synthetic.main.payment_method_item.view.*


class PaymentMethodAdapter(private val paymentMethods: List<PaymentMethod>,
                           private val context: Context,
                           private val amountValue: String) : Adapter<PaymentMethodAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val paymentMethod = paymentMethods[position]
        holder?.let {
            it.bindView(paymentMethod, amountValue)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.payment_method_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return paymentMethods.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(paymentMethod: PaymentMethod, amountValue: String) {
            val name = itemView.name
            name.text = paymentMethod.name
            itemView.credit_card_img.loadUrl(paymentMethod.thumbnail)

            itemView.setOnClickListener {
                var bundle = bundleOf("amountValue" to amountValue,"id" to paymentMethod.id )
                Navigation.findNavController(it).navigate(R.id.action_next_step, bundle)
            }

        }

    }

}
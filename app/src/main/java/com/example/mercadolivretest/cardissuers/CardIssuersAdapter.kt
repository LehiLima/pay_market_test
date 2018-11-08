package com.example.mercadolivretest.cardissuers
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
import com.example.mercadolivretest.model.CardIssuer
import kotlinx.android.synthetic.main.payment_method_item.view.*

class CardIssuersAdapter(private val cardIssuers: List<CardIssuer>,
                         private val context: Context,
                         private val paymentMethodId: String,
                         private val amountValue: String) : Adapter<CardIssuersAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardIssuer = cardIssuers[position]
        holder?.let {
            it.bindView(cardIssuer, paymentMethodId,amountValue)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.payment_method_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cardIssuers.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(cardIssuer: CardIssuer, paymentMethodId: String, amountValue: String) {
            val name = itemView.name
            name.text = cardIssuer.name
            itemView.credit_card_img.loadUrl(cardIssuer.thumbnail)

            itemView.setOnClickListener {
                var bundle = bundleOf("amountValue" to amountValue ,"paymentMethodId" to paymentMethodId, "cardIssuerId" to cardIssuer.id)
                Navigation.findNavController(it).navigate(R.id.action_next_step, bundle)
            }
        }

    }

}
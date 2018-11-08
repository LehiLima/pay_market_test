package com.example.mercadolivretest.data;

import android.support.annotation.NonNull;
import com.example.mercadolivretest.model.CardIssuer;
import com.example.mercadolivretest.model.PayerCost;
import com.example.mercadolivretest.model.PaymentMethod;

import java.util.List;

/**
 * Created by lehi.teixeira on 07/11/2018.
 */

public interface PayMarketDataSource {

    interface PaymentMethodCallback {

        void onPaymentMethodLoaded(List<PaymentMethod> paymentMethods);

        void onPaymentMethodError(String error);
    }

    interface CardIssuersCallback {

        void onCardIssuersLoaded(List<CardIssuer> cardIssuers);

        void onCardIssuersError(String error);
    }

    interface InstallmentsCallback {

        void onInstallmentsLoaded(List<PayerCost> installments);

        void onInstallmentsError(String error);
    }

    void getPaymentMethod(@NonNull PaymentMethodCallback callback);

    void getCardIssuers(@NonNull CardIssuersCallback callback, String paymentMethodId);

    void getInstallments(@NonNull InstallmentsCallback callback, String amountValue , String paymentMethodId , String cardIssuersId);

}

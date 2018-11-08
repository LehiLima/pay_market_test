package com.example.mercadolivretest.data
import com.example.mercadolivretest.BuildConfig
import io.reactivex.disposables.Disposable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PayMarketremoteDataSource : PayMarketDataSource {

    private var disposable: Disposable? = null

    private val payMarketApiServe by lazy {
        PayMarketApiService.create()
    }

    override fun getPaymentMethod(callback: PayMarketDataSource.PaymentMethodCallback) {
        disposable = payMarketApiServe.getPaymentMethod(BuildConfig.PayMarketAPIKey).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { paymentMethodResponse -> callback.onPaymentMethodLoaded(paymentMethodResponse)},
                { error -> callback.onPaymentMethodError(error.message) }
            )
    }

    override fun getCardIssuers(callback: PayMarketDataSource.CardIssuersCallback, paymentMethodId: String) {
        disposable = payMarketApiServe.getCardIssuers(BuildConfig.PayMarketAPIKey, paymentMethodId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { cardIssuersResponse -> callback.onCardIssuersLoaded(cardIssuersResponse) },
                { error -> callback.onCardIssuersError(error.message) }
            )
    }

    override fun getInstallments(callback: PayMarketDataSource.InstallmentsCallback, amountValue: String, paymentMethodId : String , cardIssuersId : String) {
        disposable = payMarketApiServe.getInstallments(BuildConfig.PayMarketAPIKey,amountValue, paymentMethodId,cardIssuersId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { installmentResponse -> callback.onInstallmentsLoaded(installmentResponse[0].payerCosts)
                },
                {
                        error -> callback.onInstallmentsError(error.message)
                }
            )
    }

    companion object {

        private var INSTANCE: PayMarketremoteDataSource? = null

        val instance: PayMarketremoteDataSource
            get() {
                if (INSTANCE == null) {
                    INSTANCE = PayMarketremoteDataSource()
                }
                return INSTANCE as PayMarketremoteDataSource
            }
    }


}
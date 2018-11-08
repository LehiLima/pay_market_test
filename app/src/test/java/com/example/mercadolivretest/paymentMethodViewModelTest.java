package com.example.mercadolivretest;

import android.app.Application;
import android.text.TextUtils;

import com.example.mercadolivretest.data.PayMarketDataSource;
import com.example.mercadolivretest.data.PayMarketRepository;
import com.example.mercadolivretest.paymentmethod.PaymentMethodtViewModel;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TextUtils.class})
public class paymentMethodViewModelTest {

    //TODO: unfinished

    @Captor
    private ArgumentCaptor<PayMarketDataSource.PaymentMethodCallback> paymentMethodCallbackArgumentCaptor;

    @Mock
    private PayMarketRepository payMarketRepository;

    @Mock
    private Application mContext;

    @Mock
    private PaymentMethodtViewModel paymentMethodtViewModel;

    @Mock
    private PayMarketDataSource.PaymentMethodCallback paymentMethodCallback;

    @Before
    public void before() {
        mockStatic(TextUtils.class);
        when(mContext.getApplicationContext()).thenReturn(mContext);
        paymentMethodtViewModel = new PaymentMethodtViewModel(mContext, payMarketRepository);
    }

    @Test
    public void paymentMethod_Error(){

        paymentMethodtViewModel.loadPaymentMethod(paymentMethodCallback);
        verify(payMarketRepository).getPaymentMethod(paymentMethodCallbackArgumentCaptor.capture());
        paymentMethodCallbackArgumentCaptor.getValue().onPaymentMethodError("error");
        Assert.assertTrue(paymentMethodCallbackArgumentCaptor.getValue().equals("error"));

    }

}
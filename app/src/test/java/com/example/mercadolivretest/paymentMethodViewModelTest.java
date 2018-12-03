package com.example.mercadolivretest;


import android.app.Application;
import android.text.TextUtils;
import com.example.mercadolivretest.data.PayMarketDataSource;
import com.example.mercadolivretest.data.PayMarketRepository;
import com.example.mercadolivretest.installments.InstallmentsViewModel;
import com.example.mercadolivretest.model.PaymentMethod;
import com.example.mercadolivretest.paymentmethod.PaymentMethodtViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({TextUtils.class})
public class paymentMethodViewModelTest {

    //https://github.com/googlesamples/android-architecture/tree/todo-mvvm-live-kotlin/todoapp

    @Mock
    private Application mContext;

    @Mock
    private PaymentMethodtViewModel paymentMethodtViewMode;


    @Mock
    private PayMarketRepository payMarketRepository;

    @Mock
    private PayMarketDataSource.PaymentMethodCallback paymentMethodCallbacky;



    @Captor
    private ArgumentCaptor<PayMarketDataSource.PaymentMethodCallback> paymentMethodCallbackArgumentCaptor;

    @Before
    public void before() {
        mockStatic(TextUtils.class);
        when(mContext.getApplicationContext()).thenReturn(mContext);
        paymentMethodtViewMode = new PaymentMethodtViewModel(mContext, payMarketRepository);
    }

    private static List<PaymentMethod> PAYMENTMETHODS = Arrays.asList(new PaymentMethod(), new PaymentMethod());

    @Test
    public void paymentMethod_Sucess(){
        paymentMethodtViewMode.loadPaymentMethod(paymentMethodCallbacky);

        verify(payMarketRepository).getPaymentMethod(paymentMethodCallbackArgumentCaptor.capture());

        paymentMethodCallbackArgumentCaptor.getValue().onPaymentMethodLoaded(PAYMENTMETHODS);

        verify(paymentMethodCallbacky).onPaymentMethodLoaded(PAYMENTMETHODS);
    }

    @Test
    public void paymentMethod_Error(){
        paymentMethodtViewMode.loadPaymentMethod(paymentMethodCallbacky);

        verify(payMarketRepository).getPaymentMethod(paymentMethodCallbackArgumentCaptor.capture());

        paymentMethodCallbackArgumentCaptor.getValue().onPaymentMethodError("error");

        verify(paymentMethodCallbacky).onPaymentMethodError("error");
    }



}

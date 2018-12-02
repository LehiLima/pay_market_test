package com.example.mercadolivretest;


import android.app.Application;
import com.example.mercadolivretest.data.PayMarketDataSource;
import com.example.mercadolivretest.data.PayMarketRepository;
import com.example.mercadolivretest.installments.InstallmentsViewModel;
import com.example.mercadolivretest.model.PayerCost;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InstallmentMethodViewModelTest {

    @Mock
    private Application mContext;

    @Mock
    private InstallmentsViewModel installmentsViewModel;

    @Mock
    private PayMarketRepository payMarketRepository;

    @Mock
    private PayMarketDataSource.InstallmentsCallback installmentsCallback;

    @Captor
    private ArgumentCaptor<PayMarketDataSource.InstallmentsCallback> installmentsCallbackArgumentCaptor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(mContext.getApplicationContext()).thenReturn(mContext);
        installmentsViewModel = new InstallmentsViewModel(mContext, payMarketRepository);
    }

    private static List<PayerCost> INSTALLMENTS = Arrays.asList(new PayerCost(), new PayerCost());

    @Test
    public void installmentMethod_Sucess(){
        installmentsViewModel.loadInstallments(installmentsCallback,"123","123","123");

        verify(payMarketRepository).getInstallments(installmentsCallbackArgumentCaptor.capture(),eq("123"),eq("123"),eq("123"));

        installmentsCallbackArgumentCaptor.getValue().onInstallmentsLoaded(INSTALLMENTS);

        verify(installmentsCallback).onInstallmentsLoaded(INSTALLMENTS);
    }

    @Test
    public void installmentMethod_Error(){
        installmentsViewModel.loadInstallments(installmentsCallback,"123","123","123");

        verify(payMarketRepository).getInstallments(installmentsCallbackArgumentCaptor.capture(),eq("123"),eq("123"),eq("123"));

        installmentsCallbackArgumentCaptor.getValue().onInstallmentsError("error");

        verify(installmentsCallback).onInstallmentsError("error");
    }


}

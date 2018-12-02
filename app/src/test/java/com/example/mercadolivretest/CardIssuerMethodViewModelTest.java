package com.example.mercadolivretest;


import android.app.Application;
import com.example.mercadolivretest.cardissuers.CardIssuersViewModel;
import com.example.mercadolivretest.data.PayMarketDataSource;
import com.example.mercadolivretest.data.PayMarketRepository;
import com.example.mercadolivretest.installments.InstallmentsViewModel;
import com.example.mercadolivretest.model.CardIssuer;
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

public class CardIssuerMethodViewModelTest {

    @Mock
    private Application mContext;

    @Mock
    private CardIssuersViewModel cardIssuersViewModel;

    @Mock
    private PayMarketRepository payMarketRepository;

    @Mock
    private PayMarketDataSource.CardIssuersCallback cardIssuersCallback;

    @Captor
    private ArgumentCaptor<PayMarketDataSource.CardIssuersCallback> installmentsCallbackArgumentCaptor;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        when(mContext.getApplicationContext()).thenReturn(mContext);
        cardIssuersViewModel = new CardIssuersViewModel(mContext, payMarketRepository);
    }

    private static List<CardIssuer> CARDISSUERS = Arrays.asList(new CardIssuer(), new CardIssuer());

    @Test
    public void installmentMethod_Sucess(){
        cardIssuersViewModel.loadCardIdduers(cardIssuersCallback,"123");

        verify(payMarketRepository).getCardIssuers(installmentsCallbackArgumentCaptor.capture(),eq("123"));

        installmentsCallbackArgumentCaptor.getValue().onCardIssuersLoaded(CARDISSUERS);

        verify(cardIssuersCallback).onCardIssuersLoaded(CARDISSUERS);
    }

    @Test
    public void installmentMethod_Error(){
        cardIssuersViewModel.loadCardIdduers(cardIssuersCallback,"123");

        verify(payMarketRepository).getCardIssuers(installmentsCallbackArgumentCaptor.capture(),eq("123"));

        installmentsCallbackArgumentCaptor.getValue().onCardIssuersError("error");

        verify(cardIssuersCallback).onCardIssuersError("error");
    }


}

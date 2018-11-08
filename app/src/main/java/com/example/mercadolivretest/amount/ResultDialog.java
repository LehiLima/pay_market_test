package com.example.mercadolivretest.amount;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import com.example.mercadolivretest.R;
import com.example.mercadolivretest.model.PayerCost;

public class ResultDialog extends Dialog implements
        android.view.View.OnClickListener {

    private Activity c;
    public Dialog d;
    private Button yes, no;
    private TextView totalAmount,amountValue, paymentMethodtv, cardIssuertv, installmenttv;
    private PayerCost  payerCost;
    private String amountValuetxt, paymentMethod, cardIssuers;


    public ResultDialog(Activity a, PayerCost payerCost, String amountValue, String paymentMethod, String cardIssuers) {
        super(a);
        this.c = a;
        this.payerCost = payerCost;
        amountValuetxt = amountValue;
        this.paymentMethod = paymentMethod;
        this.cardIssuers = cardIssuers;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.result_dialog);
        yes =  findViewById(R.id.btn_yes);
        no =   findViewById(R.id.btn_no);
        setFieldValues();
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    private void setFieldValues() {
        totalAmount = findViewById(R.id.txt_total_amount);
        amountValue = findViewById(R.id.txt_total_pay_amount);
        paymentMethodtv = findViewById(R.id.txt_paument_method);
        cardIssuertv = findViewById(R.id.txt_cardIssuers);
        installmenttv = findViewById(R.id.txt_installment);
        totalAmount.setText(String.valueOf(payerCost.getTotalAmount()));
        amountValue.setText(amountValuetxt);
        paymentMethodtv.setText(paymentMethod);
        cardIssuertv.setText(cardIssuers);
        installmenttv.setText(String.valueOf(payerCost.getInstallments()));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                c.finish();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
}
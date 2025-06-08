package com.lnmt.k22411csampleproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lnmt.adapters.PaymentMethodAdapter;
import com.lnmt.connectors.PaymentMethodConnector;
import com.lnmt.connectors.SQLiteConnector;
import com.lnmt.models.ListPaymentMethod;
import com.lnmt.models.PaymentMethod;

public class PaymentMethodActivity extends AppCompatActivity {

    ListView lvPaymentMethod;
    ArrayAdapter<PaymentMethod> adapter;
    ListPaymentMethod lpm;
    PaymentMethodConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvPaymentMethod=findViewById(R.id.lvPaymentMethod);
        adapter=new PaymentMethodAdapter(PaymentMethodActivity.this,R.layout.item_paymentmethod);
//        lvPaymentMethod.setAdapter(adapter);
//        lpm=new ListPaymentMethod();
//        lpm.gen_payments_method();
//        adapter.addAll(lpm.getPaymentMethods());

        connector = new PaymentMethodConnector();
        ListPaymentMethod lpm = connector.getAllPaymentMethods(new SQLiteConnector(this).openDatabase());

        adapter.addAll(lpm.getPaymentMethods());
        lvPaymentMethod.setAdapter(adapter);

    }
}
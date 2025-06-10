package com.lnmt.k22411csampleproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lnmt.adapters.OrderDetailsAdapter;
import com.lnmt.connectors.OrdersViewerConnector;
import com.lnmt.connectors.SQLiteConnector;
import com.lnmt.models.OrderProductDetails;
import com.lnmt.models.OrdersViewer;

import java.util.Collections;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {

    EditText edtOrderId;
    EditText edtOrderCode;
    EditText edtOrderDate;
    EditText edtEmployeeName;
    EditText edtCustomerName;
    EditText edtTotalOrderValue;
    RecyclerView rvOrderProducts;
    OrderDetailsAdapter orderDetailsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        addViews();

        rvOrderProducts.setLayoutManager(new LinearLayoutManager(this));
        loadData();
        
    }

    private void loadData() {
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("orderView")) {
            OrdersViewer ov = (OrdersViewer) intent.getSerializableExtra("orderView");

            edtOrderId.setText(ov.getId());
            edtOrderCode.setText(ov.getCode());
            edtOrderDate.setText(ov.getOrderDate());
            edtEmployeeName.setText(ov.getEmployeeName());
            edtCustomerName.setText(ov.getCustomerName());
            edtTotalOrderValue.setText(ov.getTotalOrderValue() + " VNĐ");

            SQLiteConnector sqliteConnector = new SQLiteConnector(this);
            SQLiteDatabase db = sqliteConnector.openDatabase();

            OrdersViewerConnector connector = new OrdersViewerConnector();
            List<OrderProductDetails> detailsList = connector.getOrderProductDetails(db, ov.getId());

            orderDetailsAdapter = new OrderDetailsAdapter(detailsList);
            rvOrderProducts.setAdapter(orderDetailsAdapter);
        }
    }

    private void addViews() {
        edtOrderId = findViewById(R.id.edt_order_id);
        edtOrderCode = findViewById(R.id.edt_order_code);
        edtOrderDate = findViewById(R.id.edt_order_date);
        edtEmployeeName = findViewById(R.id.edt_employee_name);
        edtCustomerName = findViewById(R.id.edt_customer_name);
        edtTotalOrderValue = findViewById(R.id.edt_total_order_value);
        rvOrderProducts = findViewById(R.id.rv_order_products);
    }
}
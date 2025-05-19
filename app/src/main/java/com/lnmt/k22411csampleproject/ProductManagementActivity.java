package com.lnmt.k22411csampleproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lnmt.connectors.ProductConnector;
import com.lnmt.models.Product;

public class ProductManagementActivity extends AppCompatActivity {

    ListView listViewProducts;
    ArrayAdapter<Product> adapter;
    ProductConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }
    private void addViews() {
        listViewProducts = findViewById(R.id.listViewProducts);
        adapter = new ArrayAdapter<>(ProductManagementActivity.this, android.R.layout.simple_list_item_1);
        connector = new ProductConnector();
        adapter.addAll(connector.get_all_products());
        listViewProducts.setAdapter(adapter);
    }
}
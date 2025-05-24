package com.lnmt.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lnmt.models.Customer;
import com.lnmt.models.Product;

public class ProductDetailActivity extends AppCompatActivity {

    EditText edt_product_id;
    EditText edt_product_name;
    EditText edt_product_quantity;
    EditText edt_product_price;
    EditText edt_product_category_id;
    EditText edt_product_description;
    EditText edt_product_image_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addViews();
    }

    private void addViews() {
        edt_product_id = findViewById(R.id.edt_product_id);
        edt_product_name = findViewById(R.id.edt_product_name);
        edt_product_quantity = findViewById(R.id.edt_product_quantity);
        edt_product_price = findViewById(R.id.edt_product_price);
        edt_product_category_id = findViewById(R.id.edt_product_category_id);
        edt_product_description = findViewById(R.id.edt_product_description);
        edt_product_image_id = findViewById(R.id.edt_product_image_id);
        display_product_infor();
    }

    private void display_product_infor() {
        //Lấy Intent từ bên ProductManagementActivity gửi qua:
        Intent intent=getIntent();
        //Lấy dữ liệu Selected Product từ intent
        Product p=(Product) intent.getSerializableExtra("SELECTED_PRODUCT");
        if(p==null)
            return;

        edt_product_id.setText(String.valueOf(p.getId()));
        edt_product_name.setText(p.getName());
        edt_product_quantity.setText(String.valueOf(p.getQuantity()));
        edt_product_price.setText(String.valueOf(p.getPrice()));
        edt_product_category_id.setText(String.valueOf(p.getCate_id()));
        edt_product_description.setText(p.getDescription());
        edt_product_image_id.setText(String.valueOf(p.getImage_id()));
    }

}
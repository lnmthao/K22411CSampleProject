package com.lnmt.k22411csampleproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.lnmt.connectors.CategoryConnector;
import com.lnmt.models.Category;
import com.lnmt.models.ListCategory;

public class CategoryManagementActivity extends AppCompatActivity {

    ListView listViewCategories;
    ListCategory listCategory;
    ArrayAdapter<Category> adapter;
    CategoryConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_category_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        listViewCategories = findViewById(R.id.listViewCategories);
        adapter = new ArrayAdapter<>(CategoryManagementActivity.this, android.R.layout.simple_list_item_1);
        connector = new CategoryConnector();
        adapter.addAll(connector.get_all_categories());
        listViewCategories.setAdapter(adapter);
    }
}
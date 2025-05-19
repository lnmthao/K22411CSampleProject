package com.lnmt.connectors;

import com.lnmt.models.ListProduct;
import com.lnmt.models.Product;

import java.util.ArrayList;

public class ProductConnector {
    ListProduct listProduct;
    public ProductConnector()
    {
        listProduct = new ListProduct();
        listProduct.generate_sample_dataset();
    }
    public ArrayList<Product> get_all_products()
    {
        if ( listProduct==null)
        {
            listProduct = new ListProduct();
            listProduct.generate_sample_dataset();
        }
        return listProduct.getProducts();
    }
}

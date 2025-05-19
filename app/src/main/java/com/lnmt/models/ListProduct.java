package com.lnmt.models;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

public class ListProduct {
    private ArrayList<Product> products;

    public ListProduct() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void generate_sample_dataset() {
        Random random = new Random();
        products.clear();

        IntStream.rangeClosed(1, 100).forEach(i -> {
            int id = i;
            String name = "Product " + i;
            int quantity = random.nextInt(50) + 1;
            double price = 10000 + random.nextInt(90000); // 10k - 100k
            int cate_id = random.nextInt(6) + 1; // có 6 danh mục
            String description = "Mô tả sản phẩm số " + i;

            Product p = new Product(id, name, quantity, price, cate_id, description);
            addProduct(p);
        });

    }

    private void addProduct(Product p) {
    }


}

package com.lnmt.models;

public class OrderProductDetails  extends OrderDetails {
    private String productName;

    public OrderProductDetails() {
        super();
    }
    public OrderProductDetails(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

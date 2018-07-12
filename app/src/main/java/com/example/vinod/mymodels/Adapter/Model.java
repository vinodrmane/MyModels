package com.example.vinod.mymodels.Adapter;

/**
 * Created by Vinod on 7/11/2018.
 */

public class Model {

    public static final int PRODUCT_TYPE=0;
    public static final int ORDER_TYPE=1;


    public int type;
private Order order;
private Product product;

    public Model(int type, Product product) {
        this.type = type;
        this.product = product;
    }

    public Model(int type, Order order) {
        this.type = type;
        this.order = order;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

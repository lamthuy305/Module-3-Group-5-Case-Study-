package com.codegym.model;

import java.util.List;

public class OrderDetail extends Order {
    private int id;
    private int quantity;
    private double orderPrice;

    public OrderDetail() {
    }

    public OrderDetail(int id, int quantity, double orderPrice){
        this.id = id;
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    public OrderDetail(int quantity, double orderPrice){
        this.quantity = quantity;
        this.orderPrice = orderPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(double orderPrice) {
        this.orderPrice = orderPrice;
    }
}


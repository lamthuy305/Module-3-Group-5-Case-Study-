package com.codegym.model;

import java.util.List;

public class OrderDetail {
    private int id;
    private int order_id;
    private int stone_id;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int order_id, int stone_id, int quantity) {
        this.order_id = order_id;
        this.stone_id = stone_id;
        this.quantity = quantity;
    }

    public OrderDetail(int id, int order_id, int stone_id, int quantity) {
        this.id = id;
        this.order_id = order_id;
        this.stone_id = stone_id;
        this.quantity = quantity;
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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getStone_id() {
        return stone_id;
    }

    public void setStone_id(int stone_id) {
        this.stone_id = stone_id;
    }

}


package com.codegym.model;

import com.sun.imageio.plugins.jpeg.JPEGImageReaderResources;

public class ViewOrderDetail {
    private int order_id;
    private  int order_detail_id;
    private String stone_name;
    private int quantity;
    private double stone_price;
    private String order_create_date;
    private double totalPrice;

    public ViewOrderDetail(int order_id, int order_detail_id, String stone_name, int quantity, double stone_price, String order_create_date) {
        this.order_id = order_id;
        this.order_detail_id = order_detail_id;
        this.stone_name = stone_name;
        this.quantity = quantity;
        this.stone_price = stone_price;
        this.order_create_date = order_create_date;
    }

    public ViewOrderDetail(int order_id, int order_detail_id, String stone_name, int quantity, double stone_price, String order_create_date, double totalPrice) {
        this.order_id = order_id;
        this.order_detail_id = order_detail_id;
        this.stone_name = stone_name;
        this.quantity = quantity;
        this.stone_price = stone_price;
        this.order_create_date = order_create_date;
        this.totalPrice = totalPrice;
    }

    public ViewOrderDetail() {
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public String getStone_name() {
        return stone_name;
    }

    public void setStone_name(String stone_name) {
        this.stone_name = stone_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getStone_price() {
        return stone_price;
    }

    public void setStone_price(double stone_price) {
        this.stone_price = stone_price;
    }

    public String getOrder_create_date() {
        return order_create_date;
    }

    public void setOrder_create_date(String order_create_date) {
        this.order_create_date = order_create_date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

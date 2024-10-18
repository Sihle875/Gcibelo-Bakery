package com.example.WebApp.entity;

import java.util.Date;

public class OrderDetailsDTO {
    private String customerName;
    private Long orderId;
    private String productName;
    private String productSize;
    private Date orderDate;
    private Date deliveryDate;

    public OrderDetailsDTO() {
        // Default constructor
    }

    public OrderDetailsDTO(String customerName, Long orderId, String productName, String productSize, Date orderDate, Date deliveryDate) {
        this.customerName = customerName;
        this.orderId = orderId;
        this.productName = productName;
        this.productSize = productSize;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
    }

    // Getters and Setters

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
}
}
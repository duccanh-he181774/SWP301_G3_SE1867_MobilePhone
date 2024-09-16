/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author ADMIN
 */

public class Product {
    private int productID;
    private String productName;
    private String productDetails;
    private String productImage;
    private int categoryID;
    private double price;
    private int stockQuantity;
    private Timestamp createdDate; 
    private Timestamp updatedDate; 
    private String status;

    // Default constructor
    public Product() {
    }

    // Constructor for inserting a new product (without ID, CreatedDate, UpdatedDate)
    public Product(String productName, String productDetails, String productImage, int categoryID, double price, int stockQuantity, String status) {
        this.productName = productName;
        this.productDetails = productDetails;
        this.productImage = productImage;
        this.categoryID = categoryID;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = status;
    }

    // Constructor for updating a product (with ID)
    public Product(int productID, String productName, String productDetails, String productImage, int categoryID, double price, int stockQuantity, String status) {
        this.productID = productID;
        this.productName = productName;
        this.productDetails = productDetails;
        this.productImage = productImage;
        this.categoryID = categoryID;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = status;
    }

    // Getters and setters for all fields
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = (Timestamp) createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = (Timestamp) updatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


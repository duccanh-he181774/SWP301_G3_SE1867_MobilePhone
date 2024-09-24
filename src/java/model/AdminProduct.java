/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */

public class AdminProduct {

    private int productId;        
    private String productName;    
    private String productDetails; 
    private String productImage;     
    private Integer categoryID;    
    private BigDecimal price;        
    private int stockQuantity;       
    private Timestamp createdDate;    
    private Timestamp updatedDate;   
    private String status;          

    // Default constructor
    public AdminProduct() {
    }

    // Constructor for creating a new product (without ID, createdDate, updatedDate)
    public AdminProduct(String productName, String productDetails, String productImage, Integer categoryID, BigDecimal price, int stockQuantity, String status) {
        this.productName = productName;
        this.productDetails = productDetails;
        this.productImage = productImage;
        this.categoryID = categoryID;  // This field now supports null
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = status;
    }

    // Constructor for updating an existing product (with ID)
    public AdminProduct(int productId, String productName, String productDetails, String productImage, Integer categoryID, BigDecimal price, int stockQuantity, String status) {
        this.productId = productId;
        this.productName = productName;
        this.productDetails = productDetails;
        this.productImage = productImage;
        this.categoryID = categoryID;  // This field now supports null
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.status = status;
    }

    // Getters and setters for all fields
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public Integer getCategoryID() {  // This field now supports null
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {  // This field now supports null
        this.categoryID = categoryID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

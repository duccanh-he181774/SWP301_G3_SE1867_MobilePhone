/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    private String imageUrl;
    private int categoryId;
    private int stock;
    private Date dateAdded;
    private Date dateUpdated;
    private String status;

    // Default Constructor
    public Product() {}

    // Constructor without ID (for insert operations)
    public Product(String name, String description, String imageUrl, int categoryId, double price, int stock, Date dateAdded, Date dateUpdated, String status) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.price = price;
        this.stock = stock;
        this.dateAdded = dateAdded;
        this.dateUpdated = dateUpdated;
        this.status = status;
    }

    // Constructor with ID (for update operations)
    public Product(int id, String name, double price, String description, String imageUrl, int categoryId, int stock, Date dateAdded, Date dateUpdated, String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
        this.categoryId = categoryId;
        this.stock = stock;
        this.dateAdded = dateAdded;
        this.dateUpdated = dateUpdated;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

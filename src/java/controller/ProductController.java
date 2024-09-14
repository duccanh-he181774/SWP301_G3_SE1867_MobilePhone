/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author ADMIN
 */

import dao.ProductDao;
import model.Product;
import java.util.Date;

public class ProductController {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        Product product = new Product("Phone Model X", "Latest model with high performance", "/images/phone.jpg", 1, 599.99, 50, new Date(), new Date(), "Available");

        try {
            boolean result = productDao.addProduct(product);
            if (result) {
                System.out.println("Product added successfully!");
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

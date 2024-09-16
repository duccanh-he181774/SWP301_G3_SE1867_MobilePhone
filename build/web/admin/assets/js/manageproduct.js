/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
    // Load products when the page loads
    loadProducts();

    // Handle form submission for adding or updating a product
    $('#productForm').on('submit', function (event) {
        event.preventDefault();
        const productId = $('#productId').val();

        if (productId) {
            updateProduct();
        } else {
            addProduct();
        }
    });

    // Cancel edit mode
    $('#cancelEdit').on('click', function () {
        resetForm();
    });

    // Function to load products from the server
    function loadProducts() {
        $.ajax({
            url: '/MobilePhone/manage-product?action=list',
            method: 'GET',
            success: function (data) {
                const productTableBody = $('#productTableBody');
                productTableBody.empty();

                if (Array.isArray(data) && data.length > 0) {
                    // Populate table with product data
                    data.forEach(function (product) {
                        const row = `
                        <tr>
                            <td>${product.productID}</td>
                            <td>${product.productName}</td>
                            <td>${product.productDetails}</td>
                            <td>${product.price}</td>
                            <td>${product.categoryID}</td>
                            <td>${product.stockQuantity}</td>
                            <td>${product.status}</td>
                            <td>
                                <button class="btn btn-primary btn-sm" onclick="editProduct(${product.productID})">Edit</button>
                                <button class="btn btn-danger btn-sm" onclick="deleteProduct(${product.productID})">Delete</button>
                            </td>
                        </tr>
                    `;
                        productTableBody.append(row);
                    });
                } else {
                    // Display a message when the list is empty
                    productTableBody.append('<tr><td colspan="8" class="text-center">No products available</td></tr>');
                }
            },
            error: function (xhr, status, error) {
                console.error('Error loading products:', error);
                // Don't show an alert, just log the error
                $('#productTableBody').append('<tr><td colspan="8" class="text-center">Unable to load products</td></tr>');
            }
        });
    }

    // Function to add a new product
    function addProduct() {
        if (!validateForm()) {
            return;
        }
        const productData = $('#productForm').serialize();

        $.ajax({
            url: '/MobilePhone/manage-product?action=insert',
            method: 'POST',
            data: productData,
            dataType: 'json',
            success: function (response) {
                if (response.success) {
                    alert(response.message);
                    resetForm();
                    loadProducts();
                } else {
                    alert('Error: ' + response.message);
                }
            },
            error: function (xhr, status, error) {
                console.error('Error adding product:', error);
                let errorMessage = 'Error adding product';
                try {
                    const response = JSON.parse(xhr.responseText);
                    errorMessage = response.message || errorMessage;
                } catch (e) {
                    console.error('Error parsing error response:', e);
                }
                alert(errorMessage);
            }
        });
    }

    // Function to update an existing product
    function updateProduct() {
        const productData = $('#productForm').serialize();

        $.ajax({
            url: '/MobilePhone/manage-product?action=update',
            method: 'POST',
            data: productData,
            success: function () {
                alert('Product updated successfully');
                resetForm();
                loadProducts();
            },
            error: function (xhr, status, error) {
                console.error('Error loading products:', xhr.responseText);
                alert('Error loading products: ' + xhr.status + ' ' + xhr.statusText);
            }
        });
    }

    // Function to edit a product (load the product data into the form)
    window.editProduct = function (productId) {
        $.ajax({
            url: `/MobilePhone/manage-product?action=getProductById&id=${productId}`,
            method: 'GET',
            success: function (product) {
                // Populate form with product data
                $('#productId').val(product.productID);
                $('#name').val(product.productName);
                $('#description').val(product.productDetails);
                $('#imageUrl').val(product.productImage);
                $('#categoryId').val(product.categoryID);
                $('#price').val(product.price);
                $('#stockQuantity').val(product.stockQuantity);
                $('#status').val(product.status);

                // Update form title and button state
                $('#formTitle').text('Edit Product');
                $('#cancelEdit').show();
            },
            error: function (xhr, status, error) {
                console.error('Error loading products:', xhr.responseText);
                alert('Error loading products: ' + xhr.status + ' ' + xhr.statusText);
            }
        });
    };

    // Function to delete a product
    window.deleteProduct = function (productId) {
        if (confirm('Are you sure you want to delete this product?')) {
            $.ajax({
                url: '/MobilePhone/manage-product?action=delete',
                method: 'POST',
                data: {id: productId},
                success: function () {
                    alert('Product deleted successfully');
                    loadProducts();
                },
                error: function (xhr, status, error) {
                    console.error('Error loading products:', xhr.responseText);
                    alert('Error loading products: ' + xhr.status + ' ' + xhr.statusText);
                }
            });
        }
    };

    // Function to reset the form
    function resetForm() {
        $('#productForm')[0].reset();
        $('#productId').val('');
        $('#formTitle').text('Add Product');
        $('#cancelEdit').hide();
        $('#status').val('');
    }
});

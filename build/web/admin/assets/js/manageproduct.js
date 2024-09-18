/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
    loadProducts();
});

// Function to load products
function loadProducts() {
    $.ajax({
        url: '/MobilePhone/manage-product?action=viewList',
        type: 'GET',
        success: function (response) {
            $('#productTableBody').html(response);
            $('#noDataMessage').hide();
        },
        error: function () {
            $('#noDataMessage').show();
        }
    });
}
function showAddProductModal() {
    $('#addProductModal').modal('show');
}
// Submit the Add Product Form via AJAX
$('#addProductForm').on('submit', function (event) {
    event.preventDefault();

    var formData = $(this).serialize();

    $.ajax({
        url: '/MobilePhone/manage-product?action=add',
        type: 'POST',
        data: formData,
        success: function (response) {
            if (response.includes("Error")) {
                alert(response); 
            } else {
                alert(response);
                loadProducts(); 
            }
        },
        error: function () {
            alert('Error adding product');
        }
    });
});


// Show the Edit Product Modal
function showEditProductModal(productId) {
    $.ajax({
        url: '/MobilePhone/manage-product?action=edit&productId=' + productId,
        type: 'GET',
        success: function (response) {
            $('#editProductModalBody').html(response);
            $('#editProductModal').modal('show');
        },
        error: function () {
            alert('Error loading product details');
        }
    });
}

// Submit the Edit Product Form via AJAX
$('#editProductForm').on('submit', function (event) {
    event.preventDefault();
    var formData = $(this).serialize();

    $.ajax({
        url: '/MobilePhone/manage-product?action=edit',
        type: 'POST',
        data: formData,
        success: function () {
            $('#editProductModal').modal('hide');
            loadProducts();
        },
        error: function () {
            alert('Error updating product');
        }
    });
});

// Delete Product
function deleteProduct(productId) {
    if (confirm('Are you sure you want to delete this product?')) {
        $.ajax({
            url: '/MobilePhone/manage-product?action=delete&productId=' + productId,
            type: 'POST',
            success: function () {
                loadProducts();
            },
            error: function () {
                alert('Error deleting product');
            }
        });
    }
}

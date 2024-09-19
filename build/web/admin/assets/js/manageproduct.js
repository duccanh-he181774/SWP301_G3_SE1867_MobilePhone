/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(document).ready(function () {
    loadProducts();
});

// Function to load products
// Function to load products
function loadProducts() {
    $.ajax({
        url: '/MobilePhone/manage-product?action=viewList',
        type: 'GET',
        success: function (response) {
            console.log("Response:", response); // Log response to debug
            var parser = new DOMParser();
            var doc = parser.parseFromString(response, 'text/html');
            var rows = doc.querySelectorAll('tr');

            var tableBody = $('#productTableBody');
            tableBody.empty();

            rows.forEach(function (row) {
                var product = {
                    id: row.cells[0].textContent,
                    name: row.cells[1].textContent,
                    description: row.cells[2].textContent,
                    image: row.cells[3].querySelector('img').src,
                    price: row.cells[4].textContent,
                    categoryId: row.cells[5].textContent,
                    stock: row.cells[6].textContent,
                    status: row.cells[7].textContent
                };

                tableBody.append(createProductRow(product));
            });

            // Add event listeners to edit and delete buttons
            $('.edit-product').on('click', function () {
                var productId = $(this).closest('tr').data('product-id');
                editProduct(productId);
            });

            $('.delete-product').on('click', function () {
                var productId = $(this).closest('tr').data('product-id');
                deleteProduct(productId);
            });

            $('#noDataMessage').hide();
            $('#productTable').show();
        },
        error: function (xhr, status, error) {
            console.error("Error loading products:", error); // Log error to debug
            $('#noDataMessage').show();
            $('#productTable').hide();
        }
    });
}

function showAddProductModal() {
    $('#addProductModal').modal('show');
}

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

// Edit Product
function editProduct(productId) {
    $.ajax({
        url: '/MobilePhone/manage-product?action=getProduct&productId=' + productId,
        type: 'GET',
        dataType: 'json',
        success: function (product) {
            $('#editProductId').val(product.productId);
            $('#editProductName').val(product.productName);
            $('#editProductDescription').val(product.productDetails);
            $('#editImageUrl').val(product.productImage);
            $('#editProductPrice').val(product.price);
            $('#editCategoryId').val(product.categoryID);
            $('#editProductStock').val(product.stockQuantity);
            $('#editProductStatus').val(product.status);
            $('#editProductModal').modal('show');
        },
        error: function (xhr, status, error) {
            alert('Error loading product details: ' + error);
        }
    });
}

$('#editProductForm').on('submit', function (event) {
    event.preventDefault();
    var formData = $(this).serialize();
    $.ajax({
        url: '/MobilePhone/manage-product?action=update',
        type: 'POST',
        data: formData,
        success: function (response) {
            alert(response);
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
            success: function (response) {
                alert(response);
                loadProducts();
            },
            error: function () {
                alert('Error deleting product');
            }
        });
    }
}

function createProductRow(product) {
    // Ensure price is parsed as a float
    const price = parseFloat(product.price);

    return `
        <tr data-product-id="${product.id}">
            <td class="id-column">${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td><img src="${product.image}" alt="${product.name}" width="50"></td>
            <td>${price.toFixed(2)}</td> <!-- Use toFixed() on a parsed float -->
            <td>${product.categoryId}</td>
            <td>${product.stock}</td>
            <td>${product.status}</td>
            <td>
                <div class="action-buttons">
                    <button class="btn btn-sm btn-warning edit-product">
                        <i class="fas fa-edit"></i>
                    </button>
                    <button class="btn btn-sm btn-danger delete-product">
                        <i class="fas fa-trash"></i>
                    </button>
                </div>
            </td>
        </tr>
    `;
}



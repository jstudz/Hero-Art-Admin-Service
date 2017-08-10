(function ($, window, document) {
    $(function () {

        var productUrl = "https://localhost:8181/heroArtAdminService/api/v1/products/";
        var distributorUrl = "https://localhost:8181/heroArtAdminService/api/v1/distributors";


        $.ajax({
            type: 'GET',
            url: productUrl,
            success: function (products)
            {
                listProducts(products._embedded.products);
            },
            error: function (jqXHR, textStatus, errorThrown)
            {
                alert("There was an error finding the products" + errorThrown.toString());
            }
        });


        function listProducts(products) {
            $.each(products, function (index, product) {
                var productId = product._links.self.href.toString();
                var row = '<tr>' +
                        '<td><input type="checkbox" name="id" value="' +
                        productId.replace("https://localhost:8181/heroArtAdminService/api/v1/products/", "") + '" /></td>' +
                        '<td><img src="images/' + product.productImg + '" style="width:75px; height: 75px;"</td>' +
                        '<td>' + product.productName + '</td>' +
                        '<td>' + product.productDescription + '</td>' +
                        '<td>' + product.productPrice + '</td>' +
                        'td>' + product.distributorId + '<td>' +
                        '<td><button class="delete" value="' +
                        productId.replace(productUrl, "") +
                        '">Delete</button><td>' +
                        '</tr>';
                $('#listProducts').append(row);
            });
        }

//        $('.delete').click(function()
//        {
//           deleteProduct(); 
//        });
//
//        function deleteProduct() {
//            console.log($('id').val());
//             $.ajax({
//            type: 'DELETE',
//            URL: productUrl +  $('id').val(),
//            success: {
//              
//            },
//            error: function (jqXHR, textStatus, errorThrown)
//            {
//                alert("There was an error while deleting product" + errorThrown.toString());
//            }
//        });
//        }

        $('tbody').on('click', '.delete', function () {
            console.log($(this).val());
            $.ajax({
                url: productUrl + $(this).val(),
                type: 'DELETE',
                success: function () {
                   
                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    alert("There was an error while deleting product " + errorThrown.toString());
                }
            });
        });
    });
}(window.jQuery, window, document));



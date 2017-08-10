(function ($, window, document) {
    $(function () {
        
        var distributorUrl = "https://localhost:8181/heroArtAdminService/api/v1/distributors/";


        $.ajax({
            type: 'GET',
            url: distributorUrl,
            success: function (distributors)
            {
                listDistributors(distributors._embedded.distributors);
            },
            error: function (jqXHR, textStatus, errorThrown)
            {
                alert("There was an error finding the distributors" + errorThrown.toString());
            }
        });


        function listDistributors(distributors) {
            $.each(distributors, function (index, distributor) {
                var distributorId = distributor._links.self.href.toString();
                var row = '<tr>' +
                        '<td><input type="checkbox" name="id" value="' +
                        distributorId.replace(distributorUrl, "") + '" /></td>' +
                        '<td>' + distributor.distributorName + '</td>' +
                        '<td>' + distributor.city + '</td>' +
                        '<td>' + distributor.state + '</td>' +
                        '<td><button class="delete" value="' +
                        distributorId.replace(distributorUrl, "") +
                        '">Delete</button><td>' +
                        '</tr>';
                $('#listDistributors').append(row);
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
                url: distributorUrl + $(this).val(),
                type: 'DELETE',
                success: {
                },
                error: function (jqXHR, textStatus, errorThrown)
                {
//                    alert("There was an error while deleting product " + errorThrown.toString());
                }
            });
        });
    });
}(window.jQuery, window, document));


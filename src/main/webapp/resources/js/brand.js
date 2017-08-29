$('#saveBrand').click(function () {

    loadCategoryNames();


    function loadCategoryNames() {

        var names = [];

        var category = {
            name: $('#categoryName').val()
        };

        $('#categoryName').val('');

        $.ajax({

            url: 'brand?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
            method: 'GET',
            success: function (res) {
                for (var i in res) {
                    names.push(res[i].name);
                }

                for(var j in names)
                {
                    if(category.name == names[j])
                    {
                        alert("Category already exists");
                        return false;
                    }
                }

                if(category.name == "")
                {
                    alert("Empty category field");
                    return false;
                }

                else{
                    $.ajax({

                        url: 'brand?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
                        method: 'POST',
                        dataType: 'json',
                        contentType: 'application/json; charset=UTF-8',
                        data: JSON.stringify(category),
                        success: function (res) {
                            parseResultFromDb(res);
                        },
                        error: function (err) {
                            console.log(err)
                        }
                    });

                    return true;
                }


            },
            error: function (err) {
                console.log(err)
            }
        })

    }
});


loadCategories();

function deleteCategory(categoryId) {

    $.ajax({

        url: 'brand?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'DELETE',
        data: JSON.stringify(categoryId),
        success: function (res) {
            parseResultFromDb(res);
        },
        error: function (err) {
            console.log(err)
        }
    })

}

function loadCategories() {
    $.ajax({

        url: 'brand?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'GET',
        success: function (res) {
            parseResultFromDb(res);
        },
        error: function (err) {
            console.log(err)
        }
    })

}


function updateCategory(categoryId) {

    $.ajax({

        url: 'brand?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'GET',
        success: function (res) {
            var categoriesFromDb = '';
            for (var i in res) {

                if (res[i].id == categoryId) {
                    categoriesFromDb += '<tr><td><input type="text" class="form-control" value="' + res[i].name + '" id="newCategoryName"></td><td><button class="btn btn-default save" onclick="saveBrandUpdates(' + res[i].id + ')">Save</button></td></tr>';
                } else {
                    categoriesFromDb += '<tr><td id=' + res[i].id + "category" + '>' + res[i].name + '</td><td></td></tr>';
                }

            }
            document.getElementById('result').innerHTML = categoriesFromDb;
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function saveBrandUpdates(categoryId) {

    var newName = $('#newCategoryName').val();

    $.ajax({

        url: 'brand?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'PUT',
        data: newName + '_' + categoryId,
        success: function (res) {
            parseResultFromDb(res);
        },
        error: function (err) {
            console.log(err)
        }
    })

}

function parseResultFromDb(res) {
    var categoriesFromDb = '';

    for (var i in res) {
        categoriesFromDb += '<tr><td id=' + res[i].id + "category" + '>' + res[i].name + '</td><td><button class="btn btn-default updateCategory ' + res[i].id + '" onclick="updateCategory(' + res[i].id + ')">Update</button></td><td><button class="btn btn-default" id="deleteCategory" onclick="deleteCategory(' + res[i].id + ')">Delete</button></td></tr>';
    }

    document.getElementById('result').innerHTML = categoriesFromDb;

}
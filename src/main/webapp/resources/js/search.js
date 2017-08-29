function searchInTable() {
    var names = [];
    $.ajax({
        url: 'carSearch?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'GET',
        success: function (res) {
            for (var i in res) {
                names.push(res[i].name);
            }
            var tr = $("table tbody tr");
            var input, filter;
            input = document.getElementById("searchIn");
            filter = input.value;
            tr.each(function (i) {
                if (names[i].match(new RegExp(filter, 'gi'))) {
                    tr[i].style.display = "";
                }
                else {
                    tr[i].style.display = "none";
                }
            });
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function searchItems() {
    var names = [];
    $.ajax({
        url: 'carSearch?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'GET',
        success: function (res) {
            for (var i in res) {
                names.push(res[i].name);
            }
            var input, filter, div, div_2;
            input = document.getElementById("searchIn");
            filter = input.value;
            div = document.getElementById("searchDiv");
            div_2 = div.getElementsByTagName("div");

            for (i = 0; i < div_2.length; i++) {
                for (var j = 0; j < names.length; j++) {
                    if (names[j].match(new RegExp(filter, 'gi'))) {
                        div_2[j].style.display = "";
                    } else {
                        div_2[j].style.display = "none";
                    }
                }
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function searchPrices() {
    var prices = [];
    $.ajax({
        url: 'carSearch?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'GET',
        success: function (res) {
            for (var i in res) {
                prices.push(res[i].price);
            }
            var div, div_2, range_min, range_max;
            range_min = document.getElementById("price-min").value;
            range_max = document.getElementById("price-max").value;
            div = document.getElementById("searchDiv");
            div_2 = div.getElementsByTagName("div");

            for (i = 0; i < div_2.length; i++) {
                for (var j = 0; j < prices.length; j++) {
                    if (prices[j] >= range_min && prices[j] <= range_max) {
                        div_2[j].style.display = "";
                    } else {
                        div_2[j].style.display = "none";
                    }
                }
            }
        },
        error: function (err) {
            console.log(err)
        }
    })
}


function searchCategories() {
    var categories = [];

    var url = 'carSearch?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val();
    $.ajax({
        url: 'carSearch?' + $('input[name=csrf_name]').val() + "=" + $('input[name=csrf_value]').val(),
        method: 'GET',
        success: function (res) {
            for (var i in res) {
                categories.push(res[i].brand.name);
            }
            var div, div_2, spanText;

            $("span.mySpan").click(function () {
                spanText = $(this).text();
                div = document.getElementById("searchDiv");
                div_2 = div.getElementsByTagName("div");

                for (i = 0; i < div_2.length; i++) {
                    for (var j = 0; j < categories.length; j++) {
                        if (categories[j] == spanText || spanText == "All") {
                            div_2[j].style.display = "";
                        } else {
                            div_2[j].style.display = "none";
                        }
                    }
                }
            });
        },
        error: function (err) {
            console.log(err)
        }
    })
}

function changeLabel() {
    $("#amount").val("$" + document.getElementById("price-min").value + " - $" + document.getElementById("price-max").value);
}
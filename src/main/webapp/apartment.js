/**
 * Created by cgac_315 on 6/16/2017.
 */
$(function () {
    var apartments = {};
    var checkPagination = true;
    var currentPage = 1;
    var id = 0;
    var cmd = "Add";

    var _ctx = ($("meta[name='ctx']").attr("content") === undefined) ? "" : $("meta[name='ctx']").attr("content");

    // Prepend context path to all jQuery AJAX requests
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        if (!options.crossDomain) {
            options.url = _ctx + options.url;
        }
    });

    function clearTextbox() {
        $("#txtApartId").val("");
        $("#txtApartName").val("");
        $("#txtdongid").val("");
        $("#txtGuId").val("");
        $("#txtCityId").val("");
        $("#file").val("");
    }

    $("#btnEdit").click(function () {
        var data = {
            "apart_id": $("#txtApartId").val(),
            "apart_name": $("#txtApartName").val(),
            "dong_id": $("#txtdongid").val(),
            "gu_id": $("#txtGuId").val(),
            "city_id": $("#txtCityId").val(),
            "url_image": $("#file").val()
        };
            apartments.upldateApartmentInfo(data, function (response) {
                if (response.STATUS == "0000") {
                    checkPagination = true;
                    apartments.getAllApartments();
                } else {
                    alert("There are a problem with edition!");
                }
            });
        clearTextbox();
        cmd = "Edit";
        $("#btnEdit").html(cmd);
    });

    $(document).on('click', '#btnDelete', function () {
        id = $(this).parents("tr").data("id");
        apartments.deleteApartmentInfo(id, function (response) {
            if (response.STATUS == "0000") {
                checkPagination = true;
                apartments.getAllApartments();
            } else {
                alert("There are some problems with deletion!")
            }
        });
    });

    $(document).on('click', '#btnEdit', function () {
        cmd = "Save";
        $("#btnEdit").html(cmd);
        id = $(this).parents("tr").data("id");
        apartments.getAllApartments(id, function (response) {
            if (response.STATUS == "0000") {
                $("#txtApartId").val(response.DATA.apart_id);
                $("#txtApartName").val(response.DATA.fLine);
                $("#txtDongId").val(response.DATA.fMachine);
                $("#txtGuId").val(response.DATA.downTime);
                $("#txtCityId").val(response.DATA.restartTime);
                $("#file").val(response.DATA.fCode);
            } else {
                alert("There are some problems with retrieval!")
            }
        });
    });

    /* Get all apartment with limit and page to view in table */
    apartments.getAllApartments = function () {
        $.ajax({
            url: "/apartments?limit="+ 10 +"&page="+ currentPage+"&name="+"",
            type: 'GET',
            dataType: 'JSON',
            data: {},
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (response) {
                $("#Apartment").html("");
                if (response.DATA.length > 0) {
                    $("#limitPage").html(response.PAGINATION.page);
                    $("#totalPage").html(response.PAGINATION.totalPages);
                    $("#totalRecords").html(response.PAGINATION.totalCount);

                    $.each(response.DATA, function (key, value) {
                        response.DATA[key]["NO"] = (key+1)+((response.PAGINATION.page-1) * response.PAGINATION.limit);
                    });
                    $("#APARTMENT_TEMPLATE").tmpl(response.DATA).appendTo("tbody#Apartment");
                    if (checkPagination) {
                        apartments.setPagination(response.PAGINATION.totalPages);
                        checkPagination = false;
                    }
                } else {
                    $("#Apartment").html("<tr style='text-align:center;'><td colspan='8'>NO CONTENT</td></tr>");
                    $("#PAGINATION").html("");
                }
            },
            error: function (data, status, err) {
                console.log("error: " + data + " status: " + status + " err:" + err);
            }
        });
    };

    apartments.getApartmentById = function (dongId, guId, cityId, callback) {
        $.ajax({
            url: "/apartment?dongId=" + dongId + "&guId=" + guId + "&cityId=" + cityId ,
            type: 'GET',
            dataType: 'JSON',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (response) {
                if (callback) {
                    callback(response);
                }
            },
            error: function (data, status, err) {
                console.log("error: " + data + " status: " + status + " err:" + err);
            }
        });
    }

    /* List all gu by cityId */
    apartments.getGu = function (cityId, callback) {
        $.ajax({
            url: "/gu?cityId=" + cityId ,
            type: 'GET',
            dataType: 'JSON',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (response) {
                if (callback) {
                    callback(response);
                }
            },
            error: function (data, status, err) {
                console.log("error: " + data + " status: " + status + " err:" + err);
            }
        });
    }

    /* List all dong by guId and cityId */
    apartments.getDong = function (guId, cityId, callback) {
        $.ajax({
            url: "/dong?guId=" + guId + "&cityId" + cityId ,
            type: 'GET',
            dataType: 'JSON',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (response) {
                if (callback) {
                    callback(response);
                }
            },
            error: function (data, status, err) {
                console.log("error: " + data + " status: " + status + " err:" + err);
            }
        });
    }

    apartments.upldateApartmentInfo = function (data, callback) {
        $.ajax({
            url: "/all-apartments",
            type: 'PUT',
            dataType: 'JSON',
            data: JSON.stringify(data),
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (response) {
                if (callback) {
                    callback(response);
                }
            },
            error: function (data, status, err) {
                console.log("error: " + data + " status: " + status + " err:" + err);
            }
        });
    }

    apartments.deleteApartmentInfo = function (id, callback){
        $.ajax({
            url: "/all-apartments?apartId="+id,
            type: 'DELETE',
            beforeSend: function (xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
            },
            success: function (response) {
                if (callback) {
                    callback(response);
                }
            },
            error: function (data, status, err) {
                console.log("error: " + data + " status: " + status + " err:" + err);
            }
        });
    }

    apartments.setPagination = function (totalPage) {
        $('#PAGINATION').bootpag({
            total: totalPage,
            page: currentPage,
            maxVisible: 10,
            leaps: true,
            firstLastUse: true,
            first: 'First',
            last: 'Last',
            wrapClass: 'pagination',
            activeClass: 'active',
            disabledClass: 'disabled',
            nextClass: 'next',
            prevClass: 'prev',
            lastClass: 'last',
            firstClass: 'first'
        });
    };

    $("#PAGINATION").on("page", function (event, page) {
        checkPagination = false;
        currentPage = page;
        apartments.getAllApartments();
    });

    apartments.getAllApartments();
});
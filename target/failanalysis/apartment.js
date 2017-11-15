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
        $("#txtId").val("");
        $("#txtLine").val("");
        $("#txtMachine").val("");
        $("#txtDownTime").val("");
        $("#txtRestartTime").val("");
        $("#txtCode").val("");
        $("#txtSubCode").val("");
        $("#txtDetail").val("");
        $("#txtPhen").val("");
        $("#txtRepairDetail").val("");
        $("#txtWorker").val("");
        $("#txtWorkStart").val("");
        $("#txtWorkEnd").val("");
        $("#txtWorkDuration").val("");
    }

    $("#btnAdd").click(function () {
        var data = {
            "fId": $("#txtId").val(),
            "fLine": $("#txtLine").val(),
            "fMachine": $("#txtMachine").val(),
            "downTime": $("#txtDownTime").val(),
            "restartTime": $("#txtRestartTime").val(),
            "fCode": $("#txtCode").val(),
            "fSubCode": $("#txtSubCode").val(),
            "fDetail": $("#txtDetail").val(),
            "fPhen": $("#txtPhen").val(),
            "repairDetail": $("#txtRepairDetail").val(),
            "worker": $("#txtWorker").val(),
            "workStart": $("#txtWorkStart").val(),
            "workEnd": $("#txtWorkEnd").val(),
            "workDuration": $("#txtWorkDuration").val()
        };
        if (cmd == "Add") {
            apartments.addMachineFail(data, function (response) {
                if (response.STATUS == "0000") {
                    checkPagination = true;
                    apartments.getAllMachineFails();
                } else {
                    alert("There are a problem with insertion!");
                }
            });
        } else {
            apartments.updateMachineFail(data, function (response) {
                if (response.STATUS == "0000") {
                    checkPagination = true;
                    apartments.getAllMachineFails();
                } else {
                    alert("There are a problem with edition!");
                }
            });
        }
        clearTextbox();
        cmd = "Add";
        $("#btnAdd").html(cmd);
    });

    $(document).on('click', '#btnDelete', function () {
        id = $(this).parents("tr").data("id");
        apartments.deleteMachineFail(id, function (response) {
            if (response.STATUS == "0000") {
                checkPagination = true;
                apartments.getAllMachineFails();
            } else {
                alert("There are some problems with deletion!")
            }
        });
    });

    $(document).on('click', '#btnEdit', function () {
        cmd = "Edit";
        $("#btnAdd").html(cmd);
        id = $(this).parents("tr").data("id");
        apartments.getAllMachineFail(id, function (response) {
            if (response.STATUS == "0000") {
                $("#txtId").val(response.DATA.fId);
                $("#txtLine").val(response.DATA.fLine);
                $("#txtMachine").val(response.DATA.fMachine);
                $("#txtDownTime").val(response.DATA.downTime);
                $("#txtRestartTime").val(response.DATA.restartTime);
                $("#txtCode").val(response.DATA.fCode);
                $("#txtSubCode").val(response.DATA.fSubCode);
                $("#txtDetail").val(response.DATA.fDetail);
                $("#txtPhen").val(response.DATA.fPhen);
                $("#txtRepairDetail").val(response.DATA.repairDetail);
                $("#txtWorker").val(response.DATA.worker);
                $("#txtWorkStart").val(response.DATA.workStart);
                $("#txtWorkEnd").val(response.DATA.workEnd);
                $("#txtWorkDuration").val(response.DATA.workDuration);
            } else {
                alert("There are some problems with retrieval!")
            }
        });
    });



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

    apartments.getGu = function (id, callback) {
        $.ajax({
            url: "/cities?id=" + id ,
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

    apartments.deleteMachineFail = function (id, callback) {
        $.ajax({
            url: "/machine-fails?id="+id,
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
    };

    apartments.addMachineFail = function (data, callback) {
        $.ajax({
            url: "/machine-fails",
            type: 'POST',
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
    };

    apartments.updateMachineFail = function (data, callback) {
        $.ajax({
            url: "/machine-fails",
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